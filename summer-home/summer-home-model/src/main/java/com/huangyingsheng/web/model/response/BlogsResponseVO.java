package com.huangyingsheng.web.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class BlogsResponseVO {

    private String code;

    private String title;

    @JsonProperty("show_text")
    private String showText;

    private List<String> tags;

    @JsonProperty("reading_volume")
    private Integer readingVolume;

    @JsonProperty("like_no")
    private Integer likeNo;

    @JsonProperty("content_url")
    private String contentUrl;

    @JsonProperty("create_time")
    private Date createTime;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShowText() {
        return showText;
    }

    public void setShowText(String showText) {
        this.showText = showText;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getReadingVolume() {
        return readingVolume;
    }

    public void setReadingVolume(Integer readingVolume) {
        this.readingVolume = readingVolume;
    }

    public Integer getLikeNo() {
        return likeNo;
    }

    public void setLikeNo(Integer likeNo) {
        this.likeNo = likeNo;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
