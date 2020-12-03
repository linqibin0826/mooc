package com.linqibin.usr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linqibin.commonutils.JwtUtils;
import com.linqibin.commonutils.MD5;
import com.linqibin.commonutils.RandomUtil;
import com.linqibin.commonutils.ResultCode;
import com.linqibin.servicebase.exceptionHandler.MyException;
import com.linqibin.usr.entity.EduUser;
import com.linqibin.usr.entity.dto.LoginDTO;
import com.linqibin.usr.entity.dto.LoginInfoDTO;
import com.linqibin.usr.entity.dto.RegisterDTO;
import com.linqibin.usr.mapper.EduUserMapper;
import com.linqibin.usr.service.EduUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Hugh&you
 * @since 2020-10-29
 */
@Service
public class EduUserServiceImpl extends ServiceImpl<EduUserMapper, EduUser> implements EduUserService {
    @Value("${spring.mail.from}")
    private String from;

    private JavaMailSender javaMailSender;
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void sendSimpleMail(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        //发件人
        message.setFrom(from);
        //收件人
        message.setTo(to);
        //主题
        message.setSubject("验证码");
        //验证码
        String sixBitRandom = RandomUtil.getSixBitRandom();
        //内容
        message.setText("当前验证码为:" + sixBitRandom + ", 5分钟内有效。");
        //发送
        javaMailSender.send(message);

        redisTemplate.opsForValue().set(to, sixBitRandom, 5, TimeUnit.MINUTES);
    }

    @Override
    public String login(LoginDTO loginDTO) {
        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();

        //对用户名和密码进行简单的判断
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            throw new MyException(ResultCode.ERROR, "账户或密码不可为空");
        }

        //用户是否存在
        QueryWrapper<EduUser> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        EduUser user = baseMapper.selectOne(wrapper);

        if (null == user) {
            throw new MyException(ResultCode.ERROR, "该用户不存在");
        }

        //用户存在, 进行密码比对
        if (!MD5.encrypt(password).equals(user.getPassword())) {
            throw new MyException(ResultCode.ERROR, "请输入正确的密码");
        }

        //是否被封
        if (user.getIsDisabled()) {
            throw new MyException((ResultCode.ERROR), "该账号不可用");
        }

        //登录成功, 返回token
        String token = JwtUtils.getJwtToken(user.getId(), user.getNickname());
        return token;
    }

    @Override
    public void register(RegisterDTO registerDTO) {
        String code = registerDTO.getCode();
        String email = registerDTO.getEmail();
        String password = registerDTO.getPassword();
        String nickname = registerDTO.getNickname();

        //检验不可为空
        if (StringUtils.isEmpty(code) ||
                StringUtils.isEmpty(email) ||
                StringUtils.isEmpty(nickname) ||
                StringUtils.isEmpty(password)) {
            throw new MyException(ResultCode.ERROR, "error");
        }

        //验证码比对
        String redisCode = redisTemplate.opsForValue().get(email);
        if (!code.equals(redisCode)) {
            throw new MyException(ResultCode.ERROR, "验证码不正确");
        }

        //判断该账号是否已注册
        QueryWrapper<EduUser> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        Integer count = baseMapper.selectCount(wrapper);
        if (count.intValue() > 0) {
            throw new MyException(ResultCode.ERROR, "用户已存在");
        }

        //添加用户到数据库
        EduUser user = new EduUser();
        user.setEmail(email);
        user.setPassword(MD5.encrypt(password));
        user.setNickname(nickname);
        user.setAvatar("https://edu-hugh.oss-cn-shenzhen.aliyuncs.com/2020/default.png");
        baseMapper.insert(user);
    }

    @Override
    public LoginInfoDTO getLoginInfo(String id) {
        QueryWrapper<EduUser> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        EduUser user = baseMapper.selectOne(wrapper);
        LoginInfoDTO loginInfo = new LoginInfoDTO();
        BeanUtils.copyProperties(user, loginInfo);
        return loginInfo;
    }

    @Override
    public Integer getRegisterCount(String date) {
        Integer count = baseMapper.getRegisterCount(date);
        return count;
    }
}
