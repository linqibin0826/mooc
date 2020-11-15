package com.linqibin.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linqibin.commonutils.ResultCode;
import com.linqibin.eduservice.entity.EduChapter;
import com.linqibin.eduservice.entity.EduVideo;
import com.linqibin.eduservice.entity.dto.Chapter;
import com.linqibin.eduservice.entity.dto.Video;
import com.linqibin.eduservice.mapper.EduChapterMapper;
import com.linqibin.eduservice.service.EduChapterService;
import com.linqibin.eduservice.service.EduVideoService;
import com.linqibin.servicebase.exceptionHandler.MyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    private EduVideoService eduVideoService;
    @Autowired
    public void setEduVideoService(EduVideoService eduVideoService) {
        this.eduVideoService = eduVideoService;
    }

    @Override
    public List<Chapter> getChapterAndVideoInfoByCourseId(String courseId) {
        //根据courseId查询所有的对应的章节信息.
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        //返回对应课程的每一个章节信息
        List<EduChapter> eduChapters = baseMapper.selectList(wrapper);
        //做一份想要的ChapterDTO
        List<Chapter> chapters = new ArrayList<>();
        for (EduChapter eduChapter : eduChapters) {
            //做一份想要的ChapterDTO
            Chapter chapter = new Chapter();
            BeanUtils.copyProperties(eduChapter, chapter);
            chapters.add(chapter);
        }

        //此时, DTO中还缺少小结的内容,继续查询小节Video的信息
        //根据chapterId和courseId查询表edu_video,获取video信息
        for (Chapter chapter : chapters) {
            String chapterId = chapter.getId();
            List<EduVideo> eduVideos = eduVideoService.getByChapterIdAnCourseId(chapterId, courseId);
            ArrayList<Video> videos = new ArrayList<>();
            //将所有eduVideos转为需要的DTO
            for (EduVideo eduVideo : eduVideos) {
                Video video = new Video();
                BeanUtils.copyProperties(eduVideo, video);
                videos.add(video);
            }
            //将小节信息放入对应的章节中
            chapter.setChildren(videos);
        }
        return chapters;
    }

    //更绝章节ID删除章节信息, 但章节中有小节时,不删除.
    @Override
    public void deleteChapterInfoById(String id) {
        //查询该章节ID在小节表中是否有信息, 如果有小节信息,则无法删除, 必须先把小节信息全部删除完成之后,才能删除章节
        Integer totalCount = eduVideoService.getVideoCountByChapterId(id);
        if(totalCount > 0) {
            throw new MyException(ResultCode.ERROR, "请先删除小节信息");
        }else{
            baseMapper.deleteById(id);
        }
    }

    @Override
    public boolean removeByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        int delete = baseMapper.delete(wrapper);
        return delete >= 0;
    }
}
