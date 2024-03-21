package com.example.moviedownloadbtweb.utils;

/**
 * Json格式的返回信息，前后端统一的响应结果
 */
public class Result {
    private Integer code;
    private Object data;
    private String msg;

    public Result() {
    }

    public Result(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static Result success(Object data){
        return new Result(1, data, "Success");
    }

    /**
     *  重载方法，无data传入参数
     */
    public static Result success(){
        return new Result(1, null, "Success");
    }

    /**
     * 失败响应，只传入msg作为参数，只返回msg和code状态码
     */
    public static Result error(String msg){
        return new Result(0, null, msg);
    }
}
