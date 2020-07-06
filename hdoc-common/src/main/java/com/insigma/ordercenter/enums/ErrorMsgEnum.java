package com.insigma.ordercenter.enums;

public enum ErrorMsgEnum {
    TALBE_NOT_FOUND(10001,"表单不存在")
    ;


    private int errorCode;
    private String errorMsg;

    ErrorMsgEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
