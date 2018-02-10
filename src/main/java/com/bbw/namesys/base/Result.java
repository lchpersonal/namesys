package com.bbw.namesys.base;

public class Result<T> {
    private T t;
    private int code;
    private String detail;

    public Result(int code) {
        this.code = code;
    }

    public Result(T t, int code) {
        this.t = t;
        this.code = code;
    }

    public T getT() {
        return t;
    }


    public int getCode() {
        return code;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }
}
