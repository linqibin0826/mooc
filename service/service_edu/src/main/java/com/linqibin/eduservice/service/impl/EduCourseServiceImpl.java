package com.linqibin.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linqibin.commonutils.Result;
import com.linqibin.commonutils.ResultCode;
import com.linqibin.eduservice.entity.EduCourse;
import com.linqibin.eduservice.entity.EduCourseDescription;
import com.linqibin.eduservice.entity.dto.*;
import com.linqibin.eduservice.feignclient.VodClient;
import com.linqibin.eduservice.mapper.EduCourseMapper;
import com.linqibin.eduservice.service.EduChapterService;
import com.linqibin.eduservice.service.EduCourseDescriptionService;
import com.linqibin.eduservice.service.EduCourseService;
import com.linqibin.eduservice.service.EduVideoService;
import com.linqibin.servicebase.exceptionHandler.MyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Hugh&you
 * @since 2020-10-21
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    private VodClient vodClient;

    @Autowired
    public void setVodClient(VodClient vodClient) {
        this.vodClient = vodClient;
    }

    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    public void setEduCourseDescriptionService(EduCourseDescriptionService eduCourseDescriptionService) {
        this.eduCourseDescriptionService = eduCourseDescriptionService;
    }

    private EduChapterService eduChapterService;

    @Autowired
    public void setEduChapterService(EduChapterService eduChapterService) {
        this.eduChapterService = eduChapterService;
    }

    private EduVideoService eduVideoService;

    @Autowired
    public void setEduVideoService(EduVideoService eduVideoService) {
        this.eduVideoService = eduVideoService;
    }

    @Override
    public String saveCourseInfo(CourseInfo infoValueObject) {
        EduCourse eduCourse = new EduCourse();
        //将表单中的course信息放入course中
        BeanUtils.copyProperties(infoValueObject, eduCourse);
        //将其写入数据库中
        int insert = baseMapper.insert(eduCourse);
        if (insert <= 0) {
            throw new MyException(20001, "课程添加失败!");
        }
        //查询刚才添加的课程的id,将其作为描述表中的主键id
        String courseId = eduCourse.getId();
        EduCourseDescription description = new EduCourseDescription();
        //将课程的描述放入对应的表中
        description.setId(courseId);
        description.setDescription(infoValueObject.getDescription());
        eduCourseDescriptionService.save(description);
        return courseId;
    }

    @Override
    public CourseInfo getCourseInfoVO(String courseId) {
        if (courseId == null) {
            throw new MyException(ResultCode.ERROR, "课程ID不可为空");
        }
        //根据ID返回一个课程VO对象
        EduCourse eduCourse = baseMapper.selectById(courseId);
        QueryWrapper<EduCourse> eduCourseQueryWrapper = new QueryWrapper<>();
        eduCourseQueryWrapper.eq("id", courseId);
        Integer integer = baseMapper.selectCount(eduCourseQueryWrapper);
        CourseInfo courseInfo = new CourseInfo();
        BeanUtils.copyProperties(eduCourse, courseInfo);
        //查找简介
        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(courseId);
        courseInfo.setDescription(courseDescription.getDescription());
        return courseInfo;
    }

    @Override
    public void updateCourseInfo(CourseInfo infoValueObject) {
        //修改course表,以及对应的description表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(infoValueObject, eduCourse);
        int i = baseMapper.updateById(eduCourse);
        if (i == 0) {
            throw new MyException(ResultCode.ERROR, "课程修改失败");
        }
        //修改description
        EduCourseDescription courseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(infoValueObject, eduCourseDescriptionService);
        eduCourseDescriptionService.updateById(courseDescription);
    }

    @Override
    public PrepublishCourseInfo getPrePublishInfoById(String courseId) {
        if (courseId == null) {
            throw new MyException(ResultCode.ERROR, "课程ID不可为空");
        }
        PrepublishCourseInfo prepublishCourseInfo = baseMapper.getPrepublishCourseInfo(courseId);
        return prepublishCourseInfo;
    }

    @Override
    public List<EduCourse> getAllCourse() {
        List<EduCourse> courses = baseMapper.selectList(null);
        return courses;
    }

    @Override
    public List<CourseInfo> getCoursesByCP(CourseCondition condition, Long current, Long limit) {
        //创建分页信息对象
        Page<EduCourse> eduCoursePage = new Page<>(current, limit);
        //创建条件信息对象
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        //判断有无条件
        if (condition != null) {
            String title = condition.getTitle();
            String teacherId = condition.getTeacherId();
            String subjectId = condition.getSubjectId();
            String subjectParentId = condition.getSubjectParentId();
            if (!StringUtils.isEmpty(title)) wrapper.eq("title", title);
            if (!StringUtils.isEmpty(teacherId)) wrapper.eq("teacher_id", teacherId);
            if (!StringUtils.isEmpty(subjectId)) wrapper.eq("subject_id", subjectId);
            if (!StringUtils.isEmpty(subjectParentId)) wrapper.eq("subject_parent_id", subjectParentId);
        }
        //根据条件和分页查出的信息
        IPage<EduCourse> Page = baseMapper.selectPage(eduCoursePage, wrapper);
        List<EduCourse> records = Page.getRecords();
        long total = Page.getTotal();
        //创建用于装载最终想要的网络传输对象们的集合
        List<CourseInfo> courses = new ArrayList<>();
        //对该集合遍历, 并逐条封装进网络传输对象
        for (EduCourse course : records) {
            CourseInfo courseDTO = new CourseInfo();
            BeanUtils.copyProperties(course, courseDTO);
            courses.add(courseDTO);
        }
        if (courses.size() > 0) courses.get(0).setTotal(total);
        return courses;
    }

    @Override
    public boolean removeCourseInfoById(String id) {

        //删除该课程在阿里云上的所有视频
        List<String> ids = eduVideoService.getVideoSourceIdsByCourseId(id);
        if (ids.size() > 0) {
            Result result = vodClient.removeVideoByIds(ids);
            if (result.getCode() == Result.error().getCode())
                throw new MyException(ResultCode.ERROR, "删除失败");
        }
        boolean b = eduCourseDescriptionService.removeById(id);
        boolean video = eduVideoService.removeByCourseId(id);
        boolean chapter = eduChapterService.removeByCourseId(id);
        int deleteById = baseMapper.deleteById(id);
        return b && chapter && video && deleteById >= 0;
    }

    @Override
    @Cacheable(value = "course", key = "'getHotCourse'")
    public List<EduCourse> getHotCourse() {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("view_count");
        wrapper.last("limit 8");
        List<EduCourse> courses = baseMapper.selectList(wrapper);
        return courses;
    }

    @Override
    public HashMap<String, Object> getList(Long current, Long limit, CourseCondition courseCondition) {
        String subjectParentId = courseCondition.getSubjectParentId();
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        Page<EduCourse> page = new Page<>(current, limit);
        List<CourseInfo> courseInfos = new ArrayList<>();
        List<EduCourse> records;
        HashMap<String, Object> map = new HashMap<>();
        if(!StringUtils.isEmpty(subjectParentId)) {
            wrapper.eq("subject_parent_id", subjectParentId);
        }
        IPage<EduCourse> eduCourseIPage = baseMapper.selectPage(page, wrapper);
        records = eduCourseIPage.getRecords();
        for (EduCourse record : records) {
            CourseInfo courseInfo = new CourseInfo();
            BeanUtils.copyProperties(record, courseInfo);
            courseInfos.add(courseInfo);
        }
        map.put("items", courseInfos);
        long total = eduCourseIPage.getTotal();
        map.put("total", total);
        return map;
    }

    @Override
    public HashMap<String, Object> getFrontCourseInfoById(String id) {
        FrontCourseInfo frontCourseInfo = baseMapper.getFrontCourseInfo(id);

        List<Chapter> chapters = eduChapterService.getChapterAndVideoInfoByCourseId(id);

        HashMap<String, Object> map = new HashMap<>();
        map.put("courseInfo", frontCourseInfo);
        map.put("chapterInfo", chapters);
        return map;
    }
}
