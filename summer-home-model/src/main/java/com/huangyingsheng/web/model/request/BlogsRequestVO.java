package com.huangyingsheng.web.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BlogsRequestVO extends BaseRequestVO {

    private String title;

    @JsonProperty("show_text")
    private String showText;

    private String tags;


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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
