package com.linqibin.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {
    /**
     * Upload video string.
     *
     * @param file the file
     * @return the string
     * @author hugh &you
     * @since 2020 /12/18 13:32
     */
    String uploadVideo(MultipartFile file);

    /**
     * Remove video.
     *
     * @param videoId the video id
     * @author hugh &you
     * @since 2020 /12/18 13:32
     */
    void removeVideo(String videoId);

    /**
     * Remove video by ids.
     *
     * @param ids the ids
     * @author hugh &you
     * @since 2020 /12/18 13:32
     */
    void removeVideoByIds(List<String> ids);

    /**
     * Gets play auth by id.
     *
     * @param id the id
     * @return the play auth by id
     * @author hugh &you
     * @since 2020 /12/18 13:32
     */
    String getPlayAuthById(String id);
}
