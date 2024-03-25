package com.example.moviedownloadbtweb.utils;

/**
 * Json格式的返回信息，前后端统一的响应结果
 */
public class Result<T> {
    private Integer code;
    private String msg;
    /**
     * 使用泛型接收data
     */
    private T data;

    public Result() {
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <E> Result<E> success(E data){
        return new Result<>(1, "Success.", data);
    }

    /**
     *  重载方法，无data传入参数
     */
    public static <E> Result<E> success(){
        return new Result<>(1, "Success.", null);
    }

    /**
     * 失败响应，只传入msg作为参数，只返回msg和code状态码
     */
    public static <E> Result<E> error(String msg){
        return new Result<>(0, msg, null);
    }
}
