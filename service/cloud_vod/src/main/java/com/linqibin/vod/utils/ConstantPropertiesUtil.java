package com.linqibin.vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantPropertiesUtil implements InitializingBean {

    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    @Value("${aliyun.vod.file.keyId}")
    private String keyId;
    @Value("${aliyun.vod.file.keySecret}")
    private String keySecret;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
    }
}
