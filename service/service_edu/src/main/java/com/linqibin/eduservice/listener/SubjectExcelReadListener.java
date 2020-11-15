package com.linqibin.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linqibin.eduservice.entity.EduSubject;
import com.linqibin.eduservice.entity.excelEntity.SubjectExcelEntity;
import com.linqibin.eduservice.service.EduSubjectService;
import com.linqibin.servicebase.exceptionHandler.MyException;

/**
 * 不交给spring管理, 采用有参构造方式引入Service, 进而将读取的excel信息写入数据库中
 * @author linqibin&youmei
 * @creed: ProjectForSDDM
 * @date 2020/10/20 22:29
 */

public class SubjectExcelReadListener extends AnalysisEventListener<SubjectExcelEntity> {


    private EduSubjectService eduSubjectService;

    public SubjectExcelReadListener() {
    }

    public SubjectExcelReadListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }


    /**
     * 读取excel文件时, 判断其是否为空, 为空则抛出异常
     * @param subjectExcelEntity
     * @param analysisContext
     * @return void
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/20 22:31
     */
    @Override
    public void invoke(SubjectExcelEntity subjectExcelEntity, AnalysisContext analysisContext) {
        if(subjectExcelEntity == null){
            throw new MyException(20001, "文件中没有内容");
        }

        //先对表格的第一列进行判断, 判断其是否存在,若不存在,  则在数据库中新增一条记录
        EduSubject eduSubject = this.existPrimarySubjectName(subjectExcelEntity.getPrimarySubjectName());
        if(eduSubject == null){
            eduSubject = new EduSubject();
            eduSubject.setTitle(subjectExcelEntity.getPrimarySubjectName());
            eduSubject.setParentId("0");
            eduSubjectService.save(eduSubject);
        }


        //对表格的第二列进行判断,
        String secondarySubjectName = subjectExcelEntity.getSecondarySubjectName();
        if(secondarySubjectName != null) {
            EduSubject eduSubject1 = this.existSecondarySubjectName(secondarySubjectName, eduSubject.getId());
            if (eduSubject1 == null) {
                eduSubject1 = new EduSubject();
                eduSubject1.setTitle(subjectExcelEntity.getSecondarySubjectName());
                eduSubject1.setParentId(eduSubject.getId());
                eduSubjectService.save(eduSubject1);
            }
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    /**
     * 是否已经存在一级课程
     * @param primary
     * @return EduSubject
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/20 23:24
     */
    private EduSubject existPrimarySubjectName(String primary) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", primary);
        wrapper.eq("parent_id", "0");
        EduSubject one = eduSubjectService.getOne(wrapper);

        return one;
    }

    /**
     * 是否已经存在二级课程
     * @param secondary
     * @param pid
     * @return EduSubject
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/20 23:25
     */
    public EduSubject existSecondarySubjectName(String secondary, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", secondary);
        wrapper.eq("parent_id", pid);
        EduSubject one = eduSubjectService.getOne(wrapper);
        return one;
    }
}
