package com.linqibin.eduservice.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class Chapter {
    private String id;
    private String title;
    private List<Video> children;
}
