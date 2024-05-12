package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.entity.Banner;
import com.zerobase.fastlms.admin.mapper.BannerMapper;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.model.BannerParam;
import com.zerobase.fastlms.admin.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {

    @Value("${uploadPath}")
    private String uploadPath;

    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;

    @Override
    public boolean register(BannerInput bannerInput) {

        String fileName = getFileName(bannerInput);
        if (fileName == null) {
            return false;
        }

        Banner banner = Banner.builder()
                .bannerName(bannerInput.getBannerName())
                .imageName(fileName)
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

    private String getFileName(BannerInput bannerInput) {
        MultipartFile multipartFile = bannerInput.getImageFile();
        if (multipartFile.isEmpty()) {
            return null;
        }

        String uploadDir = uploadPath;
        String UUIDFileName = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();    // 파일명생성
        Path path = Paths.get("src/main/resources/static/bannerImg/", UUIDFileName);    // 파일을 저장할 위치

        try {
            String fileName = uploadDir + UUIDFileName;
            Files.write(path, multipartFile.getBytes());    // path에 파일저장
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BannerDto> list(BannerParam parameter) {

        long totalCount = bannerMapper.selectListCount(parameter);

        List<BannerDto> list = bannerMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (BannerDto x : list) {
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

        deleteImg(banner.getImageName());

        String fileName = getFileName(bannerInput);
        if (fileName == null) {
            return false;
        }

        banner.setBannerName(bannerInput.getBannerName());
        banner.setImageName(fileName);
        banner.setAlterText(bannerInput.getAlterText());
        banner.setUrl(bannerInput.getUrl());
        banner.setOpenTarget(bannerInput.getOpenTarget());
        banner.setSortOrder(bannerInput.getSortOrder());
        banner.setPublic(bannerInput.isPublic());
        banner.setRegDt(LocalDateTime.now());

        bannerRepository.save(banner);

        return true;
    }

    @Override
    public void delete(List<Long> idList) {
        bannerRepository.findAllById(idList)
                .forEach(banner -> deleteImg(banner.getImageName()));

        bannerRepository.deleteAllById(idList);
    }

    private void deleteImg(String imgName) {
        Path path = Paths.get("src/main/resources/static" + imgName);
        if (Files.exists(path)) {
            if (imgName == null) {
                return;
            }

            try {
                Files.delete(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
