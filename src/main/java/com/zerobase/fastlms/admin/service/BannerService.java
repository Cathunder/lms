package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.model.BannerParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BannerService {

    /**
     * 배너 목록 리턴(관리자에서만 사용 가능)
     */
    List<BannerDto> list(BannerParam parameter);
}
