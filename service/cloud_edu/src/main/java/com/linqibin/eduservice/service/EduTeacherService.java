package com.linqibin.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linqibin.eduservice.entity.EduTeacher;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Hugh&you
 * @since 2020-10-13
 */
public interface EduTeacherService extends IService<EduTeacher> {

    /**
     * Gets hot teacher.
     *
     * @return the hot teacher
     * @author hugh &you
     * @since 2020 /12/18 13:12
     */
    List<EduTeacher> getHotTeacher();

    /**
     * Gets teacher and courses by id.
     *
     * @param teacherId the teacher id
     * @return the teacher and courses by id
     * @author hugh &you
     * @since 2020 /12/18 13:12
     */
    HashMap<String, Object> getTeacherAndCoursesById(String teacherId);
}
