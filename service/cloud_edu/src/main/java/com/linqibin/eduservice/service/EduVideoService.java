package com.linqibin.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linqibin.eduservice.entity.EduVideo;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author Hugh&you
 * @since 2020-10-21
 */
public interface EduVideoService extends IService<EduVideo> {
    /**
     * 根据课程ID和章节ID查找对应的小节信息
     *
     * @param chapterId
     * @param courseId
     * @return java.util.List<EduVideo>
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/22 13:01
     */
    List<EduVideo> getByChapterIdAnCourseId(String chapterId, String courseId);

    /**
     * @param chapterId
     * @return java.lang.Integer
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/27 10:59
     */
    Integer getVideoCountByChapterId(String chapterId);

    /**
     * @param courseId
     * @return boolean
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/27 10:59
     */
    boolean removeByCourseId(String courseId);

    /**
     * @param id
     * @return void
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/27 11:00
     */
    void removeInfoById(String id);

    /**
     * Gets video source ids by course id.
     *
     * @param courseId the course id
     * @return the video source ids by course id
     * @author hugh &you
     * @since 2020 /12/18 13:12
     */
    List<String> getVideoSourceIdsByCourseId(String courseId);
}
