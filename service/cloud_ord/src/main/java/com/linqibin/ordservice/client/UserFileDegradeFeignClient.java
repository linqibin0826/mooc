package com.linqibin.ordservice.client;

import com.linqibin.commonutils.Result;
import org.springframework.stereotype.Component;

@Component
public class UserFileDegradeFeignClient implements UserClient {
    @Override
    public Result getUserById(String userId) {
        return Result.error().message("time out");
    }
}
