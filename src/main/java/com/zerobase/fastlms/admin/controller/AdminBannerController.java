package com.zerobase.fastlms.admin.controller;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.BannerParam;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.admin.service.BannerService;
import com.zerobase.fastlms.course.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminBannerController extends BaseController {

    private final BannerService bannerService;

    @GetMapping("/admin/banner/list.do")
    public String list(Model model, BannerParam parameter) {

        parameter.init();
        List<BannerDto> banners = bannerService.list(parameter);

        long totalCount = 0;
        if (banners != null && banners.size() > 0) {
            totalCount = banners.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("pager", pagerHtml);

        return "admin/banner/list";
    }

    @GetMapping("/admin/banner/register.do")
    public String register() {

        return "admin/banner/register";
    }
}
