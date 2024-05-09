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
    private String alterText;
    private String url;
    private String target;
    private String sortOrder;
    private boolean isPublic;

    //추가컬럼
    long totalCount;
}
