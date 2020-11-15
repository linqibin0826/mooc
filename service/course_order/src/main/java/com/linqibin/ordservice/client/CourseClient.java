package com.linqibin.ordservice.client;


import com.linqibin.commonutils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "service-edu", fallback = CourseFileDegradeFeignClient.class)
public interface CourseClient {

    @GetMapping("/eduservice/course/getFrontCourseInfoById/{courseId}")
    Result getFrontCourseInfoById(@PathVariable("courseId") String courseId);
}
