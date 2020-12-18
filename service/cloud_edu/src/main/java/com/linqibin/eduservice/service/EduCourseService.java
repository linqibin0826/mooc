package com.linqibin.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linqibin.eduservice.entity.EduCourse;
import com.linqibin.eduservice.entity.dto.CourseCondition;
import com.linqibin.eduservice.entity.dto.CourseInfo;
import com.linqibin.eduservice.entity.dto.PrepublishCourseInfo;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Hugh&you
 * @since 2020-10-21
 */
public interface EduCourseService extends IService<EduCourse> {
    /**
     * @param infoValueObject
     * @return java.lang.String
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/22 17:17
     */
    String saveCourseInfo(CourseInfo infoValueObject);

    /**
     * @param courseId
     * @return CourseInfoValueObject
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/22 17:18
     */
    CourseInfo getCourseInfoVO(String courseId);

    /**
     * @param infoValueObject
     * @return void
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/22 17:18
     */
    void updateCourseInfo(CourseInfo infoValueObject);

    /**
     * 课程提交前的信息回显
     *
     * @param courseId
     * @return PrepublicshCourseInfo
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/23 22:13
     */
    PrepublishCourseInfo getPrePublishInfoById(String courseId);

    /**
     * @param
     * @return java.util.List<EduCourse>
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/27 10:59
     */
    List<EduCourse> getAllCourse();

    /**
     * @param condition
     * @param current
     * @param limit
     * @return java.util.List<CourseInfoValueObject>
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/27 10:59
     */
    List<CourseInfo> getCoursesByCP(CourseCondition condition, Long current, Long limit);

    /**
     * @param id
     * @return boolean
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/27 10:59
     */
    boolean removeCourseInfoById(String id);

    /**
     * Gets hot course.
     *
     * @return the hot course
     * @author hugh &you
     * @since 2020 /12/18 12:31
     */
    List<EduCourse> getHotCourse();

    /**
     * Gets computer list.
     *
     * @param subjectName the subject name
     * @return the computer list
     * @author hugh &you
     * @since 2020 /12/18 12:31
     */
    List<EduCourse> getComputerList(String subjectName);

    /**
     * Gets list.
     *
     * @param current         the current
     * @param limit           the limit
     * @param courseCondition the course condition
     * @return the list
     * @author hugh &you
     * @since 2020 /12/18 12:31
     */
    HashMap<String, Object> getList(Long current, Long limit, CourseCondition courseCondition);

    /**
     * Gets front course info by id.
     *
     * @param id the id
     * @return the front course info by id
     * @author hugh &you
     * @since 2020 /12/18 12:31
     */
    HashMap<String, Object> getFrontCourseInfoById(String id);


}
