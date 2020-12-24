package com.linqibin.usr.controller;

import com.linqibin.commonutils.JwtUtils;
import com.linqibin.commonutils.ResultCode;
import com.linqibin.servicebase.exceptionHandler.MyException;
import com.linqibin.usr.entity.EduUser;
import com.linqibin.usr.entity.dto.AccessTokenDTO;
import com.linqibin.usr.entity.dto.GithubUser;
import com.linqibin.usr.service.EduUserService;
import com.linqibin.usr.utils.ConstantGithubProperties;
import com.linqibin.usr.utils.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class AuthorizeController {

    private GithubProvider githubProvider;
    @Autowired
    public void setGithubProvider(GithubProvider githubProvider) {
        this.githubProvider = githubProvider;
    }

    private EduUserService userService;
    @Autowired
    public void setUserService(EduUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/callback")
    public void callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request, HttpServletResponse response) throws IOException {
        //根据Oauth传回的授权码去获取令牌.
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(ConstantGithubProperties.CLIENT_ID);
        accessTokenDTO.setClient_secret(ConstantGithubProperties.CLIENT_SECRET);
        accessTokenDTO.setRedirect_uri(ConstantGithubProperties.REDIRECT_URI);

        //令牌
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if(githubUser != null) {
            //登录成功
            //将github用户信息保存到mysql.
            EduUser user = new EduUser();
            user.setOpenid(String.valueOf(githubUser.getId()));
            user.setNickname(githubUser.getName());

            user.setAvatar(githubUser.getAvatar_url());
            EduUser eduUser = userService.CreateOrUpdateGithubUser(user);
            String token = JwtUtils.getJwtToken(eduUser.getId(), eduUser.getNickname());
            //给前端响应一个token令牌.用于判断登录态
            response.sendRedirect("http://localhost:3000?token=" + token);
        } else {
            //登录失败
            throw new MyException(ResultCode.ERROR, "登陆失败");
        }
    }

    @GetMapping("/logout")
    public void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.getSession().removeAttribute("user");
        //立即删除cookie
        Cookie token = new Cookie("token", null);
        token.setMaxAge(0);
        response.addCookie(token);

        response.sendRedirect("http://localhost:3000");
    }
}
