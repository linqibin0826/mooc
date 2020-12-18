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

    /**
     * 查询所有课程
     *
     * @return the all course
     * @author hugh &you
     * @since 2020 /12/18 12:12
     */
    @GetMapping("/getAllCourse")
    public Result getAllCourse() {
        List<EduCourse> courses = eduCourseService.getAllCourse();
        return Result.ok().data("courses", courses);
    }

    /**
     * 分页查询课程
     *
     * @param current   the current
     * @param limit     the limit
     * @param condition the condition
     * @return the courses by condition and page info
     * @author hugh &you
     * @since 2020 /12/18 12:13
     */
    @PostMapping("/getCoursesByConditionAndPageInfo/{current}/{limit}")
    public Result getCoursesByConditionAndPageInfo(@PathVariable Long current,
                                                   @PathVariable Long limit,
                                                   @RequestBody(required = false) CourseCondition condition) {
        List<CourseInfo> courses = eduCourseService.getCoursesByCP(condition, current, limit);
        return Result.ok().data("courses", courses);
    }

    /**
     * 删除课程
     *
     * @param id the id
     * @return the result
     * @author hugh &you
     * @since 2020 /12/18 12:13
     */
    @DeleteMapping("/removeCourseById/{id}")
    public Result removeCourse(@PathVariable String id) {
        boolean removeById = eduCourseService.removeCourseInfoById(id);
        if (removeById) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    /**
     * 前台首页获取热门课程
     *
     * @return the hot course
     * @author hugh &you
     * @since 2020 /12/18 12:13
     */
    @GetMapping("/getHotCourse")
    public Result getHotCourse() {
        List<EduCourse> hotCourses = eduCourseService.getHotCourse();
        return Result.ok().data("items", hotCourses);
    }

    /**
     * Gets computer list.
     *
     * @param subjectName the subject name
     * @return the computer list
     * @author hugh &you
     * @since 2020 /12/18 12:14
     */
    @GetMapping("/getComputerList/{subjectName}")
    public Result getComputerList(@PathVariable(required = true) String subjectName) {
        List<EduCourse> computerList = eduCourseService.getComputerList(subjectName);
        return Result.ok().data("items", computerList);
    }

    /**
     * Gets courses by front condition.
     *
     * @param current         the current
     * @param limit           the limit
     * @param courseCondition the course condition
     * @return the courses by front condition
     * @author hugh &you
     * @since 2020 /12/18 12:14
     */
    @PostMapping("/getCoursesByFrontCondition/{current}/{limit}")
    public Result getCoursesByFrontCondition(@PathVariable Long current, @PathVariable Long limit,
                                             @RequestBody CourseCondition courseCondition) {
        HashMap<String, Object> map = eduCourseService.getList(current, limit, courseCondition);
        return Result.ok().data("data", map);
    }

    /**
     * Gets front course info by id.
     *
     * @param courseId the course id
     * @return the front course info by id
     * @author hugh &you
     * @since 2020 /12/18 12:15
     */
    @GetMapping("/getFrontCourseInfoById/{courseId}")
    public Result getFrontCourseInfoById(@PathVariable String courseId) {
        HashMap<String, Object> info = eduCourseService.getFrontCourseInfoById(courseId);
        return Result.ok().data("data", info);
    }
}

