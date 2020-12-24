package com.linqibin.usr.entity.dto;

import lombok.Data;

/**
 * The type Access token dto.
 *
 * @author hugh &you
 * @since 2020 /12/22 11:20
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
