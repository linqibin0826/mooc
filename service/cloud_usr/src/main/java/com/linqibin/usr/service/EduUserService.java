package com.linqibin.usr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linqibin.usr.entity.EduUser;
import com.linqibin.usr.entity.dto.LoginDTO;
import com.linqibin.usr.entity.dto.LoginInfoDTO;
import com.linqibin.usr.entity.dto.RegisterDTO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Hugh&you
 * @since 2020-10-29
 */
public interface EduUserService extends IService<EduUser> {
    /**
     * 发送普通邮件
     *
     * @param to 收件人
     * @return void
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/29 23:11
     */
    void sendSimpleMail(String to);

    /**
     * 用户登录
     *
     * @param loginDTO 前端传入的用户信息
     * @return java.lang.String token
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/30 9:42
     */
    String login(LoginDTO loginDTO);

    /**
     * 用户注册
     *
     * @param registerDTO 前端传入的用户注册信息
     * @return void
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/30 9:43
     */
    void register(RegisterDTO registerDTO);

    /**
     * 根据用户ID获取用户信息
     *
     * @param id 用户id
     * @return com.linqibin.usr.entity.dto.LoginInfoDTO
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/30 10:55
     */
    LoginInfoDTO getLoginInfo(String id);

    /**
     * Gets register count.
     *
     * @param date the date
     * @return the register count
     * @author hugh &you
     * @since 2020 /12/18 13:16
     */
    Integer getRegisterCount(String date);
}
