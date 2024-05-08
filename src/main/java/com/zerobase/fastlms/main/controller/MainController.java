package com.zerobase.fastlms.main.controller;


import com.zerobase.fastlms.components.MailComponents;
import com.zerobase.fastlms.member.service.MemberService;
import com.zerobase.fastlms.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final MemberService memberService;

    @RequestMapping("/")
    public String index(HttpServletRequest request, Principal principal) {

        if (principal != null) {
            String username = principal.getName();
            String clientIp = RequestUtils.getClientIp(request);
            String userAgent = RequestUtils.getUserAgent(request);

            memberService.updateHistory(username, clientIp, userAgent);
        }

        return "index";
    }
    
    
    
    @RequestMapping("/error/denied")
    public String errorDenied() {
        
        return "error/denied";
    }
    
    
    
}
