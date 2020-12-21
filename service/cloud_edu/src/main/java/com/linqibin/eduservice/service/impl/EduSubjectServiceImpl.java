package com.linqibin.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linqibin.eduservice.entity.EduSubject;
import com.linqibin.eduservice.entity.dto.PrimarySubject;
import com.linqibin.eduservice.entity.dto.SecondarySubject;
import com.linqibin.eduservice.entity.excelEntity.SubjectExcelEntity;
import com.linqibin.eduservice.listener.SubjectExcelReadListener;
import com.linqibin.eduservice.mapper.EduSubjectMapper;
import com.linqibin.eduservice.service.EduSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Hugh&you
 * @since 2020-10-20
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            InputStream inputStream = file.getInputStream();  //将文件转换为输入流
            //读取excel中的数据, 并在课程的监听器中将其写入数据库.
            EasyExcel.read(inputStream, SubjectExcelEntity.class, new SubjectExcelReadListener(eduSubjectService))
                    .sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //树形展示
    @Override
    public List<PrimarySubject> getAllSubject() {

        //要返回的数据
        List<PrimarySubject> primarySubjects = new ArrayList<>();

        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        //一级
        wrapper.eq("parent_id", "0");
        List<EduSubject> primarySubjectList = baseMapper.selectList(wrapper); //继承自ServiceImpl, 中自动注入了一个baseMapper

        //二级
        QueryWrapper<EduSubject> wrapper1 = new QueryWrapper<>();
        wrapper1.ne("parent_id", "0");
        List<EduSubject> secondarySubjectList = baseMapper.selectList(wrapper1); //继承自ServiceImpl, 中自动注入了一个baseMapper

        //封装成dto对象, 使前端可以直接使用
        for (EduSubject eduSubject : primarySubjectList) {
            PrimarySubject primarySubject = new PrimarySubject();
            primarySubject.setId(eduSubject.getId());  //封装
            primarySubject.setTitle(eduSubject.getTitle());
            //多个属性的情况下可以使用BeanUtils包下面的copyProperties直接导入

            //新建一个列表, 用于存放一级课程的children
            ArrayList<SecondarySubject> children = new ArrayList<>();
            //把每个二级课程放到对应的一级课程下面
            for (EduSubject subject : secondarySubjectList) {
                //二级课程的父ID是否等于一级课程的id,如果是, 则将此二级课程放入一级课程的children中
                if (subject.getParentId().equals(primarySubject.getId())) {
                    SecondarySubject secondarySubject = new SecondarySubject();
                    BeanUtils.copyProperties(subject, secondarySubject);
                    children.add(secondarySubject);
                }
            }
            primarySubject.setChildren(children);

            primarySubjects.add(primarySubject);
        }

        return primarySubjects;
    }


    @Override
    public List<EduSubject> getByPid(String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", pid);
        List<EduSubject> subjects = baseMapper.selectList(wrapper);
        return subjects;
    }
}
