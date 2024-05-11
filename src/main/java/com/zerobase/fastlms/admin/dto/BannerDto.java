package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.admin.entity.Banner;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BannerDto {

    private Long id;
    private String imageName;
    private String bannerName;
    private String alterText;
    private String url;
    private String openTarget;
    private int sortOrder;
    private boolean isPublic;
    private LocalDateTime regDt;

    long totalCount;
    long seq;

    public static BannerDto fromEntity(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .imageName(banner.getImageName())
                .bannerName(banner.getBannerName())
                .alterText(banner.getAlterText())
                .url(banner.getUrl())
                .openTarget(banner.getOpenTarget())
                .sortOrder(banner.getSortOrder())
                .isPublic(banner.isPublic())
                .regDt(banner.getRegDt())
                .build();
    }

    public String getRegDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        return regDt != null ? regDt.format(formatter) : "";
    }
}
