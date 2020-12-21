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
public class EduChapterController {

    private EduChapterService chapterService;

    @Autowired
    public void setChapterService(EduChapterService chapterService) {
        this.chapterService = chapterService;
    }

    /**
     * Gets chapter and video info by course id.
     *
     * @param courseId the course id
     * @return the chapter and video info by course id
     * @author hugh &you
     * @since 2020 /12/18 12:12
     */
    @GetMapping("/getChapterAndVideoInfoByCourseId/{courseId}")
    public Result getChapterAndVideoInfoByCourseId(@PathVariable String courseId) {
        List<Chapter> chapters = chapterService.getChapterAndVideoInfoByCourseId(courseId);
        return Result.ok().data("items", chapters);
    }

    /**
     * Save chapter info result.
     *
     * @param eduChapter the edu chapter
     * @return the result
     * @author hugh &you
     * @since 2020 /12/18 12:12
     */
    @PostMapping("/saveChapterInfo")
    public Result saveChapterInfo(@RequestBody EduChapter eduChapter) {
        chapterService.save(eduChapter);
        return Result.ok();
    }

    /**
     * Gets chapter info by id.
     *
     * @param id the id
     * @return the chapter info by id
     * @author hugh &you
     * @since 2020 /12/18 12:11
     */
    @GetMapping("/getChapterInfoById/{id}")
    public Result getChapterInfoById(@PathVariable String id) {
        EduChapter eduChapter = chapterService.getById(id);
        return Result.ok().data("chapterInfo", eduChapter);
    }

    /**
     * Update chapter info by id result.
     *
     * @param eduChapter the edu chapter
     * @return the result
     * @author hugh &you
     * @since 2020 /12/18 12:11
     */
    @PostMapping("/updateChapterInfoById")
    public Result updateChapterInfoById(@RequestBody EduChapter eduChapter) {
        chapterService.updateById(eduChapter);
        return Result.ok();
    }

    /**
     * Delete chapter info by id result.
     *
     * @param id the id
     * @return the result
     * @author hugh &you
     * @since 2020 /12/18 12:11
     */
    @DeleteMapping("/deleteChapterInfoById/{id}")
    public Result deleteChapterInfoById(@PathVariable String id) {
        chapterService.deleteChapterInfoById(id);
        return Result.ok();
    }


}

