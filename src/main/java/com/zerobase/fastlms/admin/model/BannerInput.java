package com.zerobase.fastlms.admin.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class BannerInput {

    private Long id;
    private String bannerName;
    private String alterText;
    private MultipartFile imageFile;
    private String url;
    private String openTarget;
    private int sortOrder;
    private boolean isPublic;
    private LocalDateTime regDt;
}
