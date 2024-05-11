package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.entity.Banner;
import com.zerobase.fastlms.admin.mapper.BannerMapper;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.model.BannerParam;
import com.zerobase.fastlms.admin.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;

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
                .regDt(LocalDateTime.now())
                .build();

        bannerRepository.save(banner);

        return true;
    }

    @Override
    public List<BannerDto> list(BannerParam parameter) {

        long totalCount = bannerMapper.selectListCount(parameter);

        List<BannerDto> list = bannerMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for(BannerDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }

        return list;
    }

    @Override
    public BannerDto detail(Long id) {
        Banner banner = bannerRepository.findById(id).orElseThrow(RuntimeException::new);
        return BannerDto.fromEntity(banner);
    }

    @Override
    public boolean update(BannerInput bannerInput) {
        Banner banner = bannerRepository.findById(bannerInput.getId()).orElseThrow(RuntimeException::new);

        banner.setBannerName(bannerInput.getBannerName());
        banner.setImagePath(bannerInput.getImagePath());
        banner.setAlterText(bannerInput.getAlterText());
        banner.setUrl(bannerInput.getUrl());
        banner.setOpenTarget(bannerInput.getOpenTarget());
        banner.setSortOrder(bannerInput.getSortOrder());
        banner.setPublic(bannerInput.isPublic());
        banner.setRegDt(LocalDateTime.now());

        bannerRepository.save(banner);

        return true;
    }
}
