package com.linqibin.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {
    String uploadVideo(MultipartFile file);

    void removeVideo(String videoId);

    void removeVideoByIds(List<String> ids);

    String getPlayAuthById(String id);
}
