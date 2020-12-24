package com.linqibin.usr.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * The type Constant github utils.
 *
 * @author hugh &you
 * @since 2020 /12/22 10:15
 */
@Service
public class ConstantGithubProperties implements InitializingBean {

    public static String CLIENT_ID;
    public static String CLIENT_SECRET;
    public static String REDIRECT_URI;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.client.uri}")
    private String redirectUri;

    /**
     * 初始化容器时全局注入
     */
    @Override
    public void afterPropertiesSet() {
        CLIENT_ID = clientId;
        CLIENT_SECRET = clientSecret;
        REDIRECT_URI = redirectUri;
    }
}
