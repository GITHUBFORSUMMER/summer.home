package com.huangyingsheng.web.entity;

import java.util.Date;

public class BlogFriends {

    private Long id;

    private String blogUserName;

    private String blogTitle;

    private String blogDescribe;

    private String blogUrl;

    private String blogLogoUrl;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;

    private String remark;

    private Integer display;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }
}
