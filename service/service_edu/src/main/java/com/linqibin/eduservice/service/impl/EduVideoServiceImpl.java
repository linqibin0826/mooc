package com.linqibin.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linqibin.eduservice.entity.EduVideo;
import com.linqibin.eduservice.feignclient.VodClient;
import com.linqibin.eduservice.mapper.EduVideoMapper;
import com.linqibin.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author Hugh&you
 * @since 2020-10-21
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    //微服务调用
    private VodClient vodClient;
    @Autowired
    public void setVodClient(VodClient vodClient) {
        this.vodClient = vodClient;
    }

    @Override
    public List<EduVideo> getByChapterIdAnCourseId(String chapterId, String courseId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        wrapper.eq("course_id", courseId);
        List<EduVideo> eduVideos = baseMapper.selectList(wrapper);
        return eduVideos;
    }

    @Override
    public Integer getVideoCountByChapterId(String chapterId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        Integer count = baseMapper.selectCount(wrapper);
        return count;
    }

    @Override
    public boolean removeByCourseId(String courseId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        int delete = baseMapper.delete(wrapper);
        return delete >= 0;
    }

    @Override
    public void removeInfoById(String id) {
        EduVideo video = this.getById(id);
        String videoSourceId = video.getVideoSourceId();
        if(!StringUtils.isEmpty(videoSourceId)){
            vodClient.removeVideo(videoSourceId);
        }
        baseMapper.deleteById(id);
    }

    @Override
    public List<String> getVideoSourceIdsByCourseId(String courseId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        wrapper.select("video_source_id");
        List<EduVideo> videos = baseMapper.selectList(wrapper);
        //转换
        List<String> ids = new ArrayList<>();
        for (EduVideo video : videos) {
            ids.add(video.getVideoSourceId());
        }
        return ids;
    }
}
