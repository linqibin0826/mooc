package com.linqibin.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linqibin.eduservice.entity.EduSubject;
import com.linqibin.eduservice.entity.dto.PrimarySubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Hugh&you
 * @since 2020-10-20
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
     * @param file
     * @param eduSubjectService
     * @return void
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/22 17:18
     */
    void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

    /**
     * @param
     * @return java.util.List<PrimarySubject>
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/22 17:18
     */
    List<PrimarySubject> getAllSubject();

    /**
     * @param pid
     * @return java.util.List<EduSubject>
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/27 10:59
     */
    List<EduSubject> getByPid(String pid);
}
