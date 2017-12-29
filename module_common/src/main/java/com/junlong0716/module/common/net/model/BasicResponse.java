package com.junlong0716.module.common.net.model;

/**
 * @author: 巴黎没有摩天轮Li
 * @description: 基类 数据返回基本类型
 * @date: Created in 上午11:07 2017/12/29
 * @modified by:
 */
public class BasicResponse<T> {

    private int code;
    private String message;
    private T results;
    private boolean error;

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
