package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.entity.Banner;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.model.BannerParam;
import com.zerobase.fastlms.admin.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;

    @Override
    public boolean register(BannerInput bannerInput) {

        Banner banner = Banner.builder()
                .bannerName(bannerInput.getBannerName())
                .imagePath(bannerInput.getImagePath())
                .alterText(bannerInput.getAlterText())
                .url(bannerInput.getUrl())
                .openTarget(bannerInput.getOpenTarget())
                .sortOrder(bannerInput.getSortOrder())
                .isPublic(bannerInput.isPublic())
                .build();

        bannerRepository.save(banner);

        return true;
    }

    @Override
    public List<BannerDto> list(BannerParam parameter) {
        return null;
    }
}
