package com.linqibin.usr.controller;


import com.linqibin.commonutils.JwtUtils;
import com.linqibin.commonutils.Result;
import com.linqibin.commonutils.ResultCode;
import com.linqibin.servicebase.exceptionHandler.MyException;
import com.linqibin.usr.entity.dto.LoginDTO;
import com.linqibin.usr.entity.dto.LoginInfoDTO;
import com.linqibin.usr.entity.dto.RegisterDTO;
import com.linqibin.usr.service.EduUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
public class EduUserController {

    private EduUserService userService;

    @Autowired
    public void setUserService(EduUserService userService) {
        this.userService = userService;
    }

    /**
     * Gets code.
     *
     * @param email the email
     * @return the code
     * @author hugh &you
     * @since 2020 /12/18 13:15
     */
    @GetMapping("/getCode/{email}")
    public Result getCode(@PathVariable String email) {
        userService.sendSimpleMail(email);
        return Result.ok();
    }

    /**
     * Login result.
     *
     * @param loginDTO the login dto
     * @return the result
     * @author hugh &you
     * @since 2020 /12/18 13:15
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) {
        String token = userService.login(loginDTO);
        return Result.ok().data("token", token);
    }

    /**
     * Register result.
     *
     * @param registerDTO the register dto
     * @return the result
     * @author hugh &you
     * @since 2020 /12/18 13:15
     */
    @PostMapping("/register")
    public Result register(@RequestBody RegisterDTO registerDTO) {
        userService.register(registerDTO);
        return Result.ok();
    }

    /**
     * Gets login info.
     *
     * @param request the request
     * @return the login info
     * @author hugh &you
     * @since 2020 /12/18 13:15
     */
    @GetMapping("/getLoginInfo")
    public Result getLoginInfo(HttpServletRequest request) {
        try {
            String id = JwtUtils.getIdByJwtToken(request);
            LoginInfoDTO loginInfoDTO = userService.getLoginInfo(id);
            return Result.ok().data("item", loginInfoDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(ResultCode.ERROR, "error");
        }
    }

    /**
     * Gets user by id.
     *
     * @param userId the user id
     * @return the user by id
     * @author hugh &you
     * @since 2020 /12/18 13:15
     */
    @GetMapping("/getUserById/{userId}")
    public Result getUserById(@PathVariable("userId") String userId) {
        LoginInfoDTO user = userService.getLoginInfo(userId);
        return Result.ok().data("user", user);
    }

    /**
     * Gets register count.
     *
     * @param date the date
     * @return the register count
     * @author hugh &you
     * @since 2020 /12/18 13:16
     */
    @GetMapping("/getRegisterCount/{date}")
    public Result getRegisterCount(@PathVariable String date) {
        Integer count = userService.getRegisterCount(date);
        return Result.ok().data("item", count);
    }
}

