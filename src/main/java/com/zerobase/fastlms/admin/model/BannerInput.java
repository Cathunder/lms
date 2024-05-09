package com.zerobase.fastlms.admin.model;

import lombok.Data;

@Data
public class BannerInput {

    private String bannerName;
    private String alterText;
    private String imagePath;
    private String url;
    private String openTarget;
    private int sortOrder;
    private boolean isPublic;
}
