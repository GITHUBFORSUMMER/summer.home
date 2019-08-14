package com.huangyingsheng.web.controller;

import java.util.ArrayList;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import com.huangyingsheng.web.entity.BlogsDO;
import com.huangyingsheng.web.model.request.GetBlogMDUrlRequestVO;
import com.huangyingsheng.web.model.response.BaseResponse;
import com.huangyingsheng.web.model.response.GetFrindsResponseVO;
import com.huangyingsheng.web.model.response.TokenVo;
import com.huangyingsheng.web.service.BlogService;
import com.huangyingsheng.web.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class HomeController {


    private static Logger logger = LoggerFactory.getLogger(HomeController.class);


    @Autowired
    private TokenService tokenService;

    @Autowired
    private BlogService blogService;

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
        GetBlogMDUrlRequestVO dto = new GetBlogMDUrlRequestVO();
        dto.setCode(code);
        BaseResponse<BlogsDO> blogMDUrl = blogService.getBlogMDUrl(dto);
        if (blogMDUrl == null || !blogMDUrl.getSuccess() || blogMDUrl.getData() == null) {
            return "error/404";
        }
        model.addAttribute("wxjsconfig", getToken());
        model.addAttribute("blog_code", code);
        model.addAttribute("md_url", blogMDUrl.getData().getContentUrl());
        model.addAttribute("blog_title", blogMDUrl.getData().getTitle());
        return "view";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(Model model) {
        model.addAttribute("wxjsconfig", getToken());
        return "about";
    }

    @RequestMapping(value = "/mylife", method = RequestMethod.GET)
    public String myLife(Model model) {
        model.addAttribute("wxjsconfig", getToken());
        return "mylife";
    }


    @RequestMapping(value = "/links", method = RequestMethod.GET)
    public String links(Model model) {
        try {
            BaseResponse<List<GetFrindsResponseVO>> result = blogService.getFrinds();
            List<GetFrindsResponseVO> frinds = result.getData();
            model.addAttribute("links", frinds);
        } catch (Exception e) {
            logger.error("/links", e);
            model.addAttribute("links", new ArrayList<GetFrindsResponseVO>(0));
        }
        model.addAttribute("wxjsconfig", getToken());
        return "links";
    }


    private TokenVo getToken() {

        try {
            String ipAddr = getIpAddr(httpServletRequest);
            logger.info("访问ip地址:" + ipAddr);
        } catch (Exception ex) {
            logger.info("获取用户ip失败:" + ex.getMessage());
            logger.error("获取用户ip失败", ex);
        }

        String requestURL = httpServletRequest.getRequestURL().toString();
        String queryString = httpServletRequest.getQueryString();
        if (queryString != null && !queryString.isEmpty()) {
            requestURL = requestURL + "?" + httpServletRequest.getQueryString();
        }
        logger.info("延签的URL地址:" + requestURL);
        return tokenService.getWxJsConfig(requestURL, appId);

    }


    private String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        logger.error("/getIpAddr", e);
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        // ipAddress = this.getRequest().getRemoteAddr();

        return ipAddress;
    }

}
