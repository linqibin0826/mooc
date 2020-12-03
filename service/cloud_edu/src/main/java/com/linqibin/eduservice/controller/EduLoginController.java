package com.linqibin.eduservice.controller;

import com.linqibin.commonutils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {

    @PostMapping("/login")
    public Result login() {
        return Result.ok().data("token", "admin");
    }

    @GetMapping("info")
    public Result info() {
        return Result.ok().data("roles", "[admin]")
                .data("name", "admin")
                .data("avatar", "https://gravatar.zeruns.tech/avatar/");
    }

}
