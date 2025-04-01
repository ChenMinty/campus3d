package com.chenminty.campus3d.dto.response;

import lombok.Data;

@Data
public class    Response<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> Response<T> success() {
        return success(null);
    }

    public static <T> Response<T> success(T data) {
        Response<T> result = new Response<T>();
        result.setCode(0);
        result.setMsg("success");
        result.setData(data);
        return result;
    }

    public static <T> Response<T> error(int code, String message) {
        Response<T> result = new Response<T>();
        result.setCode(code);
        result.setMsg(message);
        return result;
    }
}
