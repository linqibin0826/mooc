package com.linqibin.usr.entity.dto;

import lombok.Data;

/**
 * The type Github user.
 *
 * @author hugh &you
 * @since 2020 /12/22 20:04
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;
}
