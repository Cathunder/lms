package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.model.BannerParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BannerService {

    boolean register(BannerInput bannerInput);

    boolean update(BannerInput bannerInput);

    List<BannerDto> list(BannerParam parameter);

    BannerDto detail(Long id);
}
