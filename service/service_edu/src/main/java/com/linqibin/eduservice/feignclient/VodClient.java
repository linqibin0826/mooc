package com.linqibin.eduservice.feignclient;

import com.linqibin.commonutils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class)  //配置退路
public interface VodClient {
    @DeleteMapping("/eduvod/video/remove/{videoId}")
    Result removeVideo(@PathVariable("videoId") String videoId);

    @DeleteMapping("/eduvod/video/removeByIds/")
    Result removeVideoByIds(@RequestParam("Ids") List<String> Ids);
}
