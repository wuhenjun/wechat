package com.cloud.util;

import java.io.Serializable;

public class ResultInfo<T> implements Serializable {
    private static final long serialVersionUID = -3034369732746020867L;
    private Integer resultCode;
    private String resultMsg;
    private T result;

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
