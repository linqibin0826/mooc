package com.linqibin.ordservice.client;

import com.linqibin.commonutils.Result;
import org.springframework.stereotype.Component;

@Component
public class CourseFileDegradeFeignClient implements CourseClient{
    @Override
    public Result getFrontCourseInfoById(String courseId) {
        return Result.error().message("time out");
    }
}
