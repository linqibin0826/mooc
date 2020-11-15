package com.linqibin.usr.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value="登录对象", description="登录对象")
public class LoginDTO {

    private String email;

    private String password;
}
