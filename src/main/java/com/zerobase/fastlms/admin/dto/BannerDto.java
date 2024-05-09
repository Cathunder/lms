package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.admin.entity.Banner;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BannerDto {

    private String imagePath;
    private String bannerName;
    private String alterText;
    private String url;
    private String openTarget;
    private int sortOrder;
    private boolean isPublic;

    //추가컬럼
    long totalCount;

    public static BannerDto fromEntity(Banner banner) {
        return BannerDto.builder()
                .imagePath(banner.getImagePath())
                .bannerName(banner.getBannerName())
                .alterText(banner.getAlterText())
                .url(banner.getUrl())
                .openTarget(banner.getOpenTarget())
                .sortOrder(banner.getSortOrder())
                .isPublic(banner.isPublic())
                .build();
    }
}
