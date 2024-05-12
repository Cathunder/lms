package com.zerobase.fastlms.admin.controller;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.model.BannerParam;
import com.zerobase.fastlms.admin.service.BannerService;
import com.zerobase.fastlms.course.controller.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminBannerController extends BaseController {

    private final BannerService bannerService;

    @GetMapping("/admin/banner/list.do")
    public String list(Model model, BannerParam parameter) {

        parameter.init();
        List<BannerDto> banners = bannerService.list(parameter);

        long totalCount = 0;
        if (!banners.isEmpty()) {
            totalCount = banners.get(0).getTotalCount();
        }

        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("banners", banners);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        return "admin/banner/list";
    }

    @GetMapping("/admin/banner/register.do")
    public String register() {

        return "admin/banner/register";
    }

    @PostMapping("/admin/banner/register.do")
    public String registerSubmit(
            Model model, BannerInput bannerInput,
            @RequestParam("bannerImage") MultipartFile file) {

        bannerInput.setImageFile(file);
        boolean result = bannerService.register(bannerInput);
        model.addAttribute("result", result);

        return "admin/banner/register_complete";
    }

    @GetMapping("/admin/banner/detail.do")
    public String detail(Model model, BannerInput bannerInput) {

        BannerDto bannerDetail = bannerService.detail(bannerInput.getId());
        model.addAttribute("bannerDetail", bannerDetail);

        return "/admin/banner/detail";
    }

    @PostMapping("/admin/banner/detail.do")
    public String modify(
            Model model,
            BannerInput bannerInput,
            @RequestParam("bannerImage") MultipartFile file) {

        bannerInput.setImageFile(file);
        boolean result = bannerService.update(bannerInput);
        model.addAttribute("result", result);

        return "admin/banner/modify_complete";
    }

    @PostMapping("/admin/banner/delete.do")
    public String delete(@RequestParam("idList") List<Long> idList) {
        bannerService.delete(idList);

        return "redirect:/admin/banner/list.do";
    }
}
