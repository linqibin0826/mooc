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

    /**
     * 添加课程中的小节信息
     *
     * @param eduVideo the edu video
     * @return the result
     * @author hugh &you
     * @since 2020 /12/18 12:26
     */
    @PostMapping("/addVideo")
    public Result addVideo(@RequestBody EduVideo eduVideo) {
        eduVideoService.save(eduVideo);
        return Result.ok();
    }

    /**
     * 删除小节信息
     *
     * @param id the id
     * @return the result
     * @author hugh &you
     * @since 2020 /12/18 12:28
     */
    @DeleteMapping("/deleteVideoById/{id}")
    public Result deleteVideoById(@PathVariable String id) {
        eduVideoService.removeInfoById(id);
        return Result.ok();
    }

    /**
     * 修改小节信息
     *
     * @param eduVideo the edu video
     * @return the result
     * @author hugh &you
     * @since 2020 /12/18 12:29
     */
    @PostMapping("/updateVideoById")
    public Result updateVideoById(@RequestBody EduVideo eduVideo) {
        eduVideoService.updateById(eduVideo);
        return Result.ok();
    }

    /**
     * 获取小节信息
     *
     * @param id the id
     * @return the video by id
     * @author hugh &you
     * @since 2020 /12/18 12:29
     */
    @GetMapping("/getVideoById/{id}")
    public Result getVideoById(@PathVariable String id) {
        EduVideo video = eduVideoService.getById(id);
        return Result.ok().data("video", video);
    }


}

