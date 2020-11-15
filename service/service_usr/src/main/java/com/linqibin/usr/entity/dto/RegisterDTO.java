package com.linqibin.usr.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value="注册对象", description="注册对象")
public class RegisterDTO {
    private String nickname;
    private String email;
    private String password;
    private String code;
}
