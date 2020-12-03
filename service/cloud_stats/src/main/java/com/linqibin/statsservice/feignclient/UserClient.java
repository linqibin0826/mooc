package com.linqibin.statsservice.feignclient;

import com.linqibin.commonutils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-usr")
public interface UserClient {

    @GetMapping("/user/getRegisterCount/{date}")
    Result getRegisterCount(@PathVariable("date") String date);
}
