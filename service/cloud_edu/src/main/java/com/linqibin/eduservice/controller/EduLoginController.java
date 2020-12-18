package com.linqibin.eduservice.controller;

import com.linqibin.commonutils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {

    /**
     * 后台登录, 待完善
     * TODO
     *
     * @return the result
     * @author hugh &you
     * @since 2020 /12/18 12:15
     */
    @PostMapping("/login")
    public Result login() {
        return Result.ok().data("token", "admin");
    }

    /**
     * TODO
     *
     * @author hugh&you
     * @since 2020/12/18 12:20
     */
    @GetMapping("info")
    public Result info() {
        return Result.ok().data("roles", "[admin]")
                .data("name", "admin")
                .data("avatar", "https://gravatar.zeruns.tech/avatar/");
    }

}
