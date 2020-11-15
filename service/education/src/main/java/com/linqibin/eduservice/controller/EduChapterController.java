package com.linqibin.eduservice.controller;


import com.linqibin.commonutils.Result;
import com.linqibin.eduservice.entity.EduChapter;
import com.linqibin.eduservice.entity.dto.Chapter;
import com.linqibin.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Hugh&you
 * @since 2020-10-21
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {

    private EduChapterService chapterService;

    @Autowired
    public void setChapterService(EduChapterService chapterService) {
        this.chapterService = chapterService;
    }

    /**
     * 根据课程ID获取对应课程章节信息以及小节信息
     *
     * @param courseId
     * @return Result
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/23 9:40
     */
    @GetMapping("/getChapterAndVideoInfoByCourseId/{courseId}")
    public Result getChapterAndVideoInfoByCourseId(@PathVariable String courseId) {
        List<Chapter> chapters = chapterService.getChapterAndVideoInfoByCourseId(courseId);
        return Result.ok().data("items", chapters);
    }

    /**
     * 新增章节信息
     *
     * @param eduChapter
     * @return Result
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/23 9:46
     */
    @PostMapping("/saveChapterInfo")
    public Result saveChapterInfo(@RequestBody EduChapter eduChapter) {
        chapterService.save(eduChapter);
        return Result.ok();
    }

    @GetMapping("/getChapterInfoById/{id}")
    public Result getChapterInfoById(@PathVariable String id) {
        EduChapter eduChapter = chapterService.getById(id);
        return Result.ok().data("chapterInfo", eduChapter);
    }

    @PostMapping("/updateChapterInfoById")
    public Result updateChapterInfoById(@RequestBody EduChapter eduChapter) {
        chapterService.updateById(eduChapter);
        return Result.ok();
    }

    @DeleteMapping("/deleteChapterInfoById/{id}")
    public Result deleteChapterInfoById(@PathVariable String id) {
        chapterService.deleteChapterInfoById(id);
        return Result.ok();
    }


}

