package com.linqibin.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//排除数据库, 不需要访问数据库
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("com.linqibin")
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }
}
