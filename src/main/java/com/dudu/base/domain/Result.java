package com.dudu.base.domain;

public class Result<T> {
    private ResultStatus code;
    private String message;
    private T data;

    public Result() {
    }

    public Result(T data, ResultStatus code, String message) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public static <T> Result<T> of(final T data, final ResultStatus code, final String message) {
        return new Result<T>(data, code, message);
    }

    public static <T> Result<T> of(final ResultStatus code, final String message) {
        return new Result<T>(null, code, message);
    }

    public static <T> Result<T> valueOf(final T data, final ResultStatus code, final String message) {
        return new Result<>(data, code, message);
    }

    public static <T> Result<T> error(final ResultStatus code, final String message){
        return new Result<>(null, code, message);
    }

    public static <T> Result<T> error(final String message){
        return new Result<>(null, ResultStatus.ERROR, message);
    }

    public static <T> Result<T> valueOf(final T data, final ResultStatus code) {
        return new Result<>(data, code,"");
    }

    public static <T> Result<T> ok(){
        return new Result<>(null, ResultStatus.OK, "");
    }

    public static <T> Result<T> ok(final T data){
        return new Result<>(data, ResultStatus.OK, "");
    }

    public Result(ResultStatus s, T obj) {
        this.code = s;
        this.data = obj;
    }

    public Result(ResultStatus status) {
        this.code = status;
    }

    public ResultStatus getCode() {
        return code;
    }

    public void setCode(ResultStatus code) {
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
