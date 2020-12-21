package com.linqibin.usr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.linqibin.usr.mapper")
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan("com.linqibin")
public class UsrApplication {
    public static void main(String[] args) {
        SpringApplication.run(UsrApplication.class, args);
    }
}
