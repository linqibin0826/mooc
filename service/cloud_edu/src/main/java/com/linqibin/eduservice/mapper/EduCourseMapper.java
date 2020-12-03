package com.linqibin.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linqibin.eduservice.entity.EduCourse;
import com.linqibin.eduservice.entity.dto.FrontCourseInfo;
import com.linqibin.eduservice.entity.dto.PrepublishCourseInfo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Hugh&you
 * @since 2020-10-21
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    PrepublishCourseInfo getPrepublishCourseInfo(String courseId);

    FrontCourseInfo getFrontCourseInfo(String courseId);
}
