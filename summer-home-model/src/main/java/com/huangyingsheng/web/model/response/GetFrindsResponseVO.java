package com.huangyingsheng.web.model.response;

public class GetFrindsResponseVO {

    private String blogUserName;

    private String blogTitle;

    private String blogDescribe;

    private String blogUrl;

    private String blogLogoUrl;

    public String getBlogUserName() {
        return blogUserName;
    }

    public void setBlogUserName(String blogUserName) {
        this.blogUserName = blogUserName;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogDescribe() {
        return blogDescribe;
    }

    public void setBlogDescribe(String blogDescribe) {
        this.blogDescribe = blogDescribe;
    }

    public String getBlogUrl() {
        return blogUrl;
    }

    public void setBlogUrl(String blogUrl) {
        this.blogUrl = blogUrl;
    }

    public String getBlogLogoUrl() {
        return blogLogoUrl;
    }

    public void setBlogLogoUrl(String blogLogoUrl) {
        this.blogLogoUrl = blogLogoUrl;
    }
}
