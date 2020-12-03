package com.linqibin.eduservice.controller;


import com.linqibin.commonutils.Result;
import com.linqibin.eduservice.entity.EduVideo;
import com.linqibin.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Hugh&you
 * @since 2020-10-21
 */
@RestController
@RequestMapping("/eduservice/video")
public class EduVideoController {

    private EduVideoService eduVideoService;

    @Autowired
    public void setEduVideoService(EduVideoService eduVideoService) {
        this.eduVideoService = eduVideoService;
    }


    @PostMapping("/addVideo")
    public Result addVideo(@RequestBody EduVideo eduVideo) {
        eduVideoService.save(eduVideo);
        return Result.ok();
    }

    @DeleteMapping("/deleteVideoById/{id}")
    public Result deleteVideoById(@PathVariable String id) {
        eduVideoService.removeInfoById(id);
        return Result.ok();
    }

    @PostMapping("/updateVideoById")
    public Result updateVideoById(@RequestBody EduVideo eduVideo) {
        eduVideoService.updateById(eduVideo);
        return Result.ok();
    }

    @GetMapping("/getVideoById/{id}")
    public Result getVideoById(@PathVariable String id) {
        EduVideo video = eduVideoService.getById(id);
        return Result.ok().data("video", video);
    }


}

