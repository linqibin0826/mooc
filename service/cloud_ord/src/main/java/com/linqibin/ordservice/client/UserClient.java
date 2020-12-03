package com.linqibin.ordservice.client;

import com.linqibin.commonutils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "service-usr", fallback = UserFileDegradeFeignClient.class)
public interface UserClient {

    @GetMapping("/user/getUserById/{userId}")
    Result getUserById(@PathVariable("userId") String userId);
}
