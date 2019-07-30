package com.huangyingsheng.web.controller;


import com.huangyingsheng.web.model.request.BlogsRequestVO;
import com.huangyingsheng.web.model.request.GetBlogMDUrlRequestVO;
import com.huangyingsheng.web.model.response.BaseResponse;
import com.huangyingsheng.web.model.response.BlogsResponseVO;
import com.huangyingsheng.web.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/blog")
@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;


    @PostMapping("/get_blogs")
    public BaseResponse<List<BlogsResponseVO>> getBlogs(@RequestBody BlogsRequestVO request) {
        return blogService.getBlogs(request);
    }


    @PostMapping("/get_blog_md_url")
    public BaseResponse<String> getBlogMDUrl(@RequestBody GetBlogMDUrlRequestVO request) {
        return blogService.getBlogMDUrl(request);
    }


}
