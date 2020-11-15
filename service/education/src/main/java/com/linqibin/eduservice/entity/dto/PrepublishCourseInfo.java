package com.linqibin.eduservice.entity.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PrepublishCourseInfo {
    private String title;
    private String cover;
    private String teacherName;
    private Integer lessonNum;
    private String primarySubject;
    private String secondarySubject;
    private BigDecimal price;
}
