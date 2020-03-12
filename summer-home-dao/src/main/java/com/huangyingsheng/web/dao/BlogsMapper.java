package com.huangyingsheng.web.dao;

import com.huangyingsheng.web.entity.BlogsDO;
import com.huangyingsheng.web.input.GetBlogListInput;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogsMapper {

    List<BlogsDO> getBlogList(GetBlogListInput input);

    BlogsDO getByCode(@Param("code") String code);

}
