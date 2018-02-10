package com.bbw.namesys.base;

public class Results {
    public static <T> Result success() {
        return new Result<T>(0);
    }

    public static <T> Result success(String detail) {
        Result result = new Result<T>(0);
        result.setDetail(detail);
        return result;

    }

    public static <T> Result of(T t) {
        return new Result<>(t, 0);
    }

    public static <T> Result error() {
        return new Result<T>(-1);
    }

    public static <T> Result error(String detail) {
        Result result = new Result<T>(-1);
        result.setDetail(detail);
        return result;
    }

    public static <T> Result exception() {
        Result result = new Result<T>(-2);
        result.setDetail("系统异常");
        return result;
    }

    public static <T> Result accessdenied() {
        Result result = new Result<T>(-3);
        result.setDetail("权限不足");
        return result;
    }
}
