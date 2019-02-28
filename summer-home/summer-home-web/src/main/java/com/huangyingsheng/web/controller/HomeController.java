package com.huangyingsheng.web.controller;

import com.huangyingsheng.web.model.vo.TokenVo;
import com.huangyingsheng.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    private String appId = "wx80a7631fb6f3e4d2";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String summer(Model model) {
        String requestURL = httpServletRequest.getRequestURL().toString();
        String queryString = httpServletRequest.getQueryString();
        if (queryString != null && !queryString.isEmpty()) {
            requestURL = requestURL + "?" + httpServletRequest.getQueryString();
        }
        System.out.println(requestURL);
        TokenVo wxJsConfig = tokenService.getWxJsConfig(requestURL, appId);
        model.addAttribute("wxjsconfig", wxJsConfig);
        return "summer";
    }


}
