package com.linqibin.usr.controller;


import com.linqibin.commonutils.JwtUtils;
import com.linqibin.commonutils.Result;
import com.linqibin.commonutils.ResultCode;
import com.linqibin.servicebase.exceptionHandler.MyException;
import com.linqibin.usr.entity.dto.LoginDTO;
import com.linqibin.usr.entity.dto.LoginInfoDTO;
import com.linqibin.usr.entity.dto.RegisterDTO;
import com.linqibin.usr.service.EduUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Hugh&you
 * @since 2020-10-29
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class EduUserController {

    private EduUserService userService;
    @Autowired
    public void setUserService(EduUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getCode/{email}")
    public Result getCode( @PathVariable String email) {
        userService.sendSimpleMail(email);
        return Result.ok();
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) {
        String token = userService.login(loginDTO);
        return Result.ok().data("token", token);
    }

    @PostMapping("/register")
    public Result register(@RequestBody RegisterDTO registerDTO) {
        userService.register(registerDTO);
        return Result.ok();
    }

    @GetMapping("/getLoginInfo")
    public Result getLoginInfo(HttpServletRequest request) {
        try {
            String id = JwtUtils.getIdByJwtToken(request);
            LoginInfoDTO loginInfoDTO = userService.getLoginInfo(id);
            return Result.ok().data("item", loginInfoDTO);
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException(ResultCode.ERROR,"error");
        }
    }

    @GetMapping("/getUserById/{userId}")
    public Result  getUserById(@PathVariable("userId") String userId) {
        LoginInfoDTO user = userService.getLoginInfo(userId);
        return Result.ok().data("user", user);
    }

    @GetMapping("/getRegisterCount/{date}")
    public Result getRegisterCount(@PathVariable String date) {
        Integer count = userService.getRegisterCount(date);
        return Result.ok().data("item", count);
    }
}

