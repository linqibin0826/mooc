package com.linqibin.eduservice.feignclient;

import com.linqibin.commonutils.Result;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public Result removeVideo(String videoId) {
        return Result.error().message("time out");
    }

    @Override
    public Result removeVideoByIds(List<String> Ids) {
        return Result.error().message("time out");
    }
}
