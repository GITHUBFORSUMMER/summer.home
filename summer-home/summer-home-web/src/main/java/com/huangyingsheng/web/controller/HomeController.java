package com.huangyingsheng.web.controller;

import com.huangyingsheng.web.model.response.TokenVo;
import com.huangyingsheng.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

@Controller
public class HomeController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    private String appId = "wx80a7631fb6f3e4d2";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String summer(Model model) {
        model.addAttribute("wxjsconfig", getToken());
        return "summer";
    }


    @RequestMapping(value = "/blog", method = RequestMethod.GET)
    public String blog(Model model) {
        model.addAttribute("wxjsconfig", getToken());
        return "blog";
    }

    @RequestMapping(value = "/view/{code}", method = RequestMethod.GET)
    public String viewBlog(Model model, @PathVariable(value = "code") String code) {
        model.addAttribute("wxjsconfig", getToken());
        model.addAttribute("blog_code", code);
        return "view";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(Model model) {
        model.addAttribute("wxjsconfig", getToken());
        return "about";
    }


    private TokenVo getToken() {
        String requestURL = httpServletRequest.getRequestURL().toString();
        String queryString = httpServletRequest.getQueryString();
        if (queryString != null && !queryString.isEmpty()) {
            requestURL = requestURL + "?" + httpServletRequest.getQueryString();
        }
        System.out.println(requestURL);
        return tokenService.getWxJsConfig(requestURL, appId);
    }
}
