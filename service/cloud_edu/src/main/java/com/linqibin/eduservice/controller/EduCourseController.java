package com.linqibin.eduservice.controller;


import com.linqibin.commonutils.Result;
import com.linqibin.commonutils.ResultCode;
import com.linqibin.eduservice.entity.EduCourse;
import com.linqibin.eduservice.entity.dto.CourseCondition;
import com.linqibin.eduservice.entity.dto.CourseInfo;
import com.linqibin.eduservice.entity.dto.PrepublishCourseInfo;
import com.linqibin.eduservice.service.EduCourseService;
import com.linqibin.servicebase.exceptionHandler.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.GET;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Hugh&you
 * @since 2020-10-21
 */
@SuppressWarnings("ALL")
@RestController
@RequestMapping("/eduservice/course")
public class EduCourseController {

    private EduCourseService eduCourseService;

    @Autowired
    public void setEduCourseService(EduCourseService eduCourseService) {
        this.eduCourseService = eduCourseService;
    }

    /**
     * 添加课程信息到数据库中
     *
     * @param infoValueObject
     * @return Result
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/22 16:49
     */
    @PostMapping("/addCourseInfo")
    public Result addCourseInfo(@RequestBody CourseInfo infoValueObject) {
        if (infoValueObject == null) {
            throw new MyException(ResultCode.ERROR, "课程相关信息不可为空");
        } else {
            String courseId = eduCourseService.saveCourseInfo(infoValueObject);
            return Result.ok().data("courseId", courseId);
        }
    }

    /**
     * 根据课程ID返回课程相关信息
     *
     * @param courseId
     * @return Result
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/23 9:39
     */
    @GetMapping("/getCourseInfoByCourseId/{courseId}")
    public Result getCourseInfoByCourseId(@PathVariable String courseId) {
        CourseInfo courseInfo = eduCourseService.getCourseInfoVO(courseId);
        return Result.ok().data("courseInfo", courseInfo);
    }


    /**
     * 根据课程ID修改课程相关信息
     *
     * @param infoValueObject
     * @return Result
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/23 9:39
     */
    @PostMapping("/updateCourseInfo")
    public Result updateCourseInfo(@RequestBody CourseInfo infoValueObject) {
        eduCourseService.updateCourseInfo(infoValueObject);
        return Result.ok();
    }

    /**
     * 确认发布
     *
     * @param courseId the course id
     * @return the pre publish info by id
     * @author linqb
     * @date 2020 /12/2 22:19
     */
    @GetMapping("/getPrePublishInfoById/{courseId}")
    public Result getPrePublishInfoById(@PathVariable String courseId) {
        PrepublishCourseInfo prepublishInfo = eduCourseService.getPrePublishInfoById(courseId);
        return Result.ok().data("prepublishCourseInfo", prepublishInfo).data("price", prepublishInfo.getPrice());
    }

    @GetMapping("/getAllCourse")
    public Result getAllCourse() {
        List<EduCourse> courses = eduCourseService.getAllCourse();
        return Result.ok().data("courses", courses);
    }

    @PostMapping("/getCoursesByConditionAndPageInfo/{current}/{limit}")
    public Result getCoursesByConditionAndPageInfo(@PathVariable Long current,
                                                   @PathVariable Long limit,
                                                   @RequestBody(required = false) CourseCondition condition) {
        List<CourseInfo> courses = eduCourseService.getCoursesByCP(condition, current, limit);
        return Result.ok().data("courses", courses);
    }

    @DeleteMapping("/removeCourseById/{id}")
    public Result removeCourse(@PathVariable String id) {
        boolean removeById = eduCourseService.removeCourseInfoById(id);
        if (removeById) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    @GetMapping("/getHotCourse")
    public Result getHotCourse() {
        List<EduCourse> hotCourses = eduCourseService.getHotCourse();
        return Result.ok().data("items", hotCourses);
    }

    @GetMapping("/getComputerList/{subjectName}")
    public Result getComputerList(@PathVariable(required = true) String subjectName) {
        List<EduCourse> computerList = eduCourseService.getComputerList(subjectName);
        return Result.ok().data("items", computerList);
    }

    @PostMapping("/getCoursesByFrontCondition/{current}/{limit}")
    public Result getCoursesByFrontCondition(@PathVariable Long current, @PathVariable Long limit,
                                             @RequestBody CourseCondition courseCondition) {
        HashMap<String, Object> map = eduCourseService.getList(current, limit, courseCondition);
        return Result.ok().data("data", map);
    }


    @GetMapping("/getFrontCourseInfoById/{courseId}")
    public Result getFrontCourseInfoById(@PathVariable String courseId) {
        HashMap<String, Object> info = eduCourseService.getFrontCourseInfoById(courseId);
        return Result.ok().data("data", info);
    }
}

