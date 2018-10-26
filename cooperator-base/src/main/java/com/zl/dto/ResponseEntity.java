package com.zl.dto;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @author zhuolin
 * @program: cooperator
 * @date 2018/10/25
 * @description: 返回参数
 **/
public class ResponseEntity<T> implements Serializable {

    public final static int SUCCESS = 0;

    public final static int FAILD = 1;

    public static boolean isSuccess(ResponseEntity result) {
        return result.getRc() == SUCCESS;
    }

    public static <T> ResponseEntity<T> newFaild() {
        return newResponseEntity(FAILD, null, null);
    }

    public static <T> ResponseEntity<T> newFaild(String err) {
        return newResponseEntity(FAILD, null, err);
    }

    public static <T> ResponseEntity<T> newFaild(int rc, String err) {
        return newResponseEntity(rc, null, err);
    }

    public static <T> ResponseEntity<T> newSuccess() {
        return newResponseEntity(SUCCESS, null, null);
    }

    public static <T> ResponseEntity<T> newSuccess(T t) {
        return newResponseEntity(SUCCESS, t, null);
    }

    private static <T> ResponseEntity<T> newResponseEntity(int rc, T t, String err) {
        return new ResponseEntity<T>(rc, t, err);
    }

    /**
     * 返回码
     */
    private int rc;

    /**
     * 正确时的返回结果
     */
    private T ret;

    /**
     * 失败时的异常信息
     */
    private String err;


    private ResponseEntity() {
    }

    private ResponseEntity(int rc, String err) {
        this(rc, null, err);
    }

    private ResponseEntity(int rc, T ret) {
        this(rc, ret, null);
    }

    private ResponseEntity(int rc, T ret, String err) {
        setRc(rc);
        setRet(ret);
        setErr(err);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public int getRc() {
        return rc;
    }

    public void setRc(int rc) {
        this.rc = rc;
    }

    public String getErr() {
        return this.err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public T getRet() {
        return ret;
    }

    public void setRet(T ret) {
        this.ret = ret;
    }

}
