package com.zerobase.fastlms.admin.dto;

import lombok.*;

import java.io.File;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BannerDto {

    private File imageFile;
    private String bannerName;
    private String alterText;
    private String url;
    private String openTarget;
    private String sortOrder;
    private boolean isPublic;

    //추가컬럼
    long totalCount;
}
