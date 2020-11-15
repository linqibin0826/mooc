package com.linqibin.eduservice.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class PrimarySubject {
    private String id;
    private String title;

    //一级课程对应着多个二级课程
    private List<SecondarySubject> children;
}
