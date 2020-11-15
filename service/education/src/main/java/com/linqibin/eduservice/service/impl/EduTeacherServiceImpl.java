package com.linqibin.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linqibin.eduservice.entity.EduCourse;
import com.linqibin.eduservice.entity.EduTeacher;
import com.linqibin.eduservice.mapper.EduTeacherMapper;
import com.linqibin.eduservice.service.EduCourseService;
import com.linqibin.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Hugh&you
 * @since 2020-10-13
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    private EduCourseService courseService;

    @Autowired
    public void setCourseService(EduCourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    @Cacheable(value = "teacher", key = "'getHotTeachers'")  //redis缓存
    public List<EduTeacher> getHotTeacher() {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 4");
        List<EduTeacher> teachers = baseMapper.selectList(wrapper);
        return teachers;
    }

    @Override
    public HashMap<String, Object> getTeacherAndCoursesById(String teacherId) {
        EduTeacher eduTeacher = baseMapper.selectById(teacherId);
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id", teacherId);
        List<EduCourse> courseList = courseService.list(wrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("teacher", eduTeacher);
        map.put("courseList", courseList);
        return map;
    }
}
