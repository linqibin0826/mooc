package com.linqibin.eduservice.controller;


import com.linqibin.commonutils.Result;
import com.linqibin.eduservice.entity.EduSubject;
import com.linqibin.eduservice.entity.dto.PrimarySubject;
import com.linqibin.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Hugh&you
 * @since 2020-10-20
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    private EduSubjectService eduSubjectService;

    @Autowired
    public void setEduSubjectService(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    /**
     * 以Excel方式添加课程信息
     *
     * @param file
     * @return Result
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/21 12:51
     */
    @PostMapping("/addSubject")
    public Result addSubject(MultipartFile file) {
        eduSubjectService.saveSubject(file, eduSubjectService);
        return Result.ok();
    }

    @GetMapping("/getAllSubject")
    public Result getAllSubject() {
        List<PrimarySubject> allSubject = eduSubjectService.getAllSubject();
        return Result.ok().data("allSubject", allSubject);
    }

    @GetMapping("/getSubjectsByPid/{pid}")
    public Result getSubjectsByPid(@PathVariable String pid) {
        List<EduSubject> subjectsByPid = eduSubjectService.getByPid(pid);
        return Result.ok().data("items", subjectsByPid);
    }


}

