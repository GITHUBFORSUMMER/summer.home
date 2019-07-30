package com.huangyingsheng.web.model.response;

public class BaseResponse<T> {

    private Boolean success;

    private Integer code;

    private String message;

    private T data;


    public BaseResponse(boolean success, int code, String msg, T t) {
        this.success = success;
        this.code = code;
        this.message = msg;
        this.data = t;
    }

    public static <T> BaseResponse<T> ofSuccess() {
        return new BaseResponse(true, 200, "", null);
    }

    public static <T> BaseResponse<T> ofSuccess(T t) {
        return new BaseResponse(true, 200, "", t);
    }

    public static <T> BaseResponse<T> ofSuccess(T t, String msg) {
        return new BaseResponse(true, 200, msg, t);
    }


    public static <T> BaseResponse<T> ofBizError(String msg) {
        return new BaseResponse(false, 500, msg, null);
    }

    public static <T> BaseResponse<T> ofParamError(String msg) {
        return new BaseResponse(false, 300, msg, null);
    }


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
