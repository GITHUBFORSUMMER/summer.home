package com.huangyingsheng.web.service.impl;

import com.huangyingsheng.web.dao.BlogsMapper;
import com.huangyingsheng.web.entity.BlogsDO;
import com.huangyingsheng.web.input.GetBlogListInput;
import com.huangyingsheng.web.model.request.BlogsRequestVO;
import com.huangyingsheng.web.model.request.GetBlogMDUrlRequestVO;
import com.huangyingsheng.web.model.response.BaseResponse;
import com.huangyingsheng.web.model.response.BlogsResponseVO;
import com.huangyingsheng.web.service.BlogService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogsMapper blogsMapper;


    @Override
    public BaseResponse<List<BlogsResponseVO>> getBlogs(BlogsRequestVO request) {
        if (request == null ||
                request.getCurrentPage() == null || request.getCurrentPage() <= 0 ||
                request.getPageSize() == null || request.getPageSize() > 50) {
            return BaseResponse.ofParamError("参数异常");
        }
        Integer pageFrom = (request.getCurrentPage() - 1) * request.getPageSize();
        Integer pageSize = request.getPageSize();
        List<BlogsDO> blogList = getBlogList(request.getTitle(),
                request.getShowText(),
                request.getTags(),
                pageFrom,
                pageSize);

        if (blogList == null || blogList.size() <= 0) {
            return BaseResponse.ofSuccess();
        }
        List<BlogsResponseVO> blogs = new ArrayList<>();
        for (BlogsDO blogsDO : blogList) {
            BlogsResponseVO blog = new BlogsResponseVO();
            blog.setCode(blogsDO.getCode());
            blog.setTitle(blogsDO.getTitle());
            blog.setShowText(blogsDO.getShowText());
            if (!StringUtils.isEmpty(blogsDO.getTags())) {
                blog.setTags(Arrays.asList(blogsDO.getTags().split(",")));
            }
            blog.setReadingVolume(blogsDO.getReadingVolume());
            blog.setLikeNo(blogsDO.getLikeNo());
            blog.setContentUrl(blogsDO.getContentUrl());
            blog.setCreateTime(blogsDO.getCreateTime());
            blogs.add(blog);
        }
        return BaseResponse.ofSuccess(blogs);
    }

    @Override
    public BaseResponse<String> getBlogMDUrl(GetBlogMDUrlRequestVO request) {
        if (request == null || StringUtils.isEmpty(request.getCode())) {
            return BaseResponse.ofParamError("参数异常");
        }

        BlogsDO byCode = blogsMapper.getByCode(request.getCode());

        return BaseResponse.ofSuccess(byCode.getContentUrl());
    }


    private List<BlogsDO> getBlogList(String title, String showText, String tags, Integer pageFrom, Integer pageSize) {
        GetBlogListInput input = new GetBlogListInput();
        input.setTitle(title);
        input.setShowText(showText);
        input.setTags(tags);
        input.setPageFrom(pageFrom);
        input.setPageSize(pageSize);
        return blogsMapper.getBlogList(input);
    }
}
