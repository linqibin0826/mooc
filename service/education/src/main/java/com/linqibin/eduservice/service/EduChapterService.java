package com.linqibin.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linqibin.eduservice.entity.EduChapter;
import com.linqibin.eduservice.entity.dto.Chapter;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Hugh&you
 * @since 2020-10-21
 */
public interface EduChapterService extends IService<EduChapter> {

    /**
     * @param courseId
     * @return java.util.List<ChapterDTO>
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/22 17:17
     */
    List<Chapter> getChapterAndVideoInfoByCourseId(String courseId);

    /**
     * @param id
     * @return void
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/23 9:58
     */
    void deleteChapterInfoById(String id);

    /**
     * @param CourseId
     * @return boolean
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/27 11:00
     */
    boolean removeByCourseId(String CourseId);
}
