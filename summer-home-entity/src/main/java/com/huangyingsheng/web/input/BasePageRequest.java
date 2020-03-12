package com.huangyingsheng.web.input;

import java.util.Map;

public class BasePageRequest {

    private Integer pageFrom;

    private Integer pageSize;

    private Map<String, String> sorts;


    public Integer getPageFrom() {
        return pageFrom;
    }

    public void setPageFrom(Integer pageFrom) {
        this.pageFrom = pageFrom;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, String> getSorts() {
        return sorts;
    }

    public void setSorts(Map<String, String> sorts) {
        this.sorts = sorts;
    }
}
