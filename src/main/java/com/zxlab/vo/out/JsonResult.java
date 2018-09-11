package com.zxlab.vo.out;

/**
 * @Author: Liu Yuefei
 * @Date: Created in 2018/9/11 21:02
 * @Description:
 */
public class JsonResult<T> {

    public static final int SUCCESS_CODE = 0;

    public static final int INPUT_ERROR_CODE = 200;

    public static final int CLIENT_ERROR_CODE = 400;

    public static final int SERVER_ERROR_CODE = 500;

    public Integer code;

    private String message;

    private T data;

    public JsonResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public JsonResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <K> JsonResult<K> success(String message, K data) {
        return new JsonResult<K>(JsonResult.SUCCESS_CODE, message, data);
    }

    public static <K> JsonResult<K> error(int code, String message) {
        return new JsonResult<K>(code, message);
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
