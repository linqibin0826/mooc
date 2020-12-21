package com.linqibin.vod.controller;

import com.linqibin.commonutils.Result;
import com.linqibin.vod.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api("阿里云视频点播")
@RestController
@RequestMapping("/eduvod/video")
public class VideoController {

    private VideoService videoService;

    @Autowired
    public void setVideoService(VideoService videoService) {
        this.videoService = videoService;
    }

    /**
     * 视频上传
     *
     * @param file the file
     * @return the result
     * @author hugh &you
     * @since 2020 /12/18 13:30
     */
    @PostMapping("/upload")
    public Result uploadVideo(MultipartFile file) {
        String videoId = videoService.uploadVideo(file);
        return Result.ok().message("视频上传成功").data("videoId", videoId);
    }

    /**
     * 删除视频
     *
     * @param videoId the video id
     * @return the result
     * @author hugh &you
     * @since 2020 /12/18 13:31
     */
    @DeleteMapping("/remove/{videoId}")
    public Result removeVideo(@ApiParam(name = "videoId", value = "云端视频id", required = true)
                              @PathVariable("videoId") String videoId) {

        videoService.removeVideo(videoId);
        return Result.ok().message("视频删除成功");
    }

    /**
     * 批量删除视频
     *
     * @param Ids the ids
     * @return the result
     * @author hugh &you
     * @since 2020 /12/18 13:31
     */
    @DeleteMapping("/removeByIds/")
    public Result removeVideoByIds(@RequestParam("Ids") List<String> Ids) {
        videoService.removeVideoByIds(Ids);
        return Result.ok();
    }

    /**
     * 加密视频获取授权
     *
     * @param id the id
     * @return the play auth by id
     * @author hugh &you
     * @since 2020 /12/18 13:31
     */
    @GetMapping("/getPlayAuthById/{id}")
    public Result getPlayAuthById(@PathVariable String id) {
        String auth = videoService.getPlayAuthById(id);
        return Result.ok().data("auth", auth);
    }
}
