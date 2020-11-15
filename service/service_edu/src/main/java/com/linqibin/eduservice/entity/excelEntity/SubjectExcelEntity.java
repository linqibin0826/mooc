package com.linqibin.eduservice.entity.excelEntity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;


/**
 * 对应excel表格中的两列
 * @param
 * @return
 * @author linqibin&youmei
 * @creed: ProjectForSDDM
 * @date 2020/10/21 12:51
 */
@Data
public class SubjectExcelEntity {
    @ExcelProperty(index = 0)
    private String primarySubjectName;
    @ExcelProperty(index = 1)
    private String secondarySubjectName;
}
