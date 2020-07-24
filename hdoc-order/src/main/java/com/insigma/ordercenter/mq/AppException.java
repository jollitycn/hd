package com.insigma.ordercenter.mq;

public class AppException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    protected ErrorCode errCode;
    protected String errMsg;

    public AppException() {
    }

    public AppException(Throwable e) {
        super(e);
    }


    public AppException(ErrorCode errCode, String... errMsg) {
        super(errCode.getMsg());
        this.errCode = errCode;
        setErrMsg(errMsg, Boolean.valueOf(true));
    }

    public AppException(ErrorCode errCode, String errMsg, Boolean isTransfer) {
        super(errMsg);
        this.errCode = errCode;
        setErrMsg(new String[]{errMsg}, isTransfer);
    }


    public AppException(ErrorCode errCode, Throwable cause, String... errMsg) {
        super(errCode.getCode() + errCode.getMsg(), cause);
        this.errCode = errCode;
        setErrMsg(errMsg, Boolean.valueOf(true));
    }


    public ErrorCode getErrCode() {
        return this.errCode;
    }


    public void setErrCode(ErrorCode errCode) {
        this.errCode = errCode;
    }


    public String getErrMsg() {
        return this.errMsg;
    }


    public void setErrMsg(String[] errMsg, Boolean isTransfer) {
        if (null != errMsg && errMsg.length > 0) {
            if (this.errCode.getMsg().contains("%s") && isTransfer.booleanValue()) {
                this.errMsg = String.format(this.errCode.getMsg(), (Object[]) errMsg);
            } else {
                StringBuffer sf = new StringBuffer();
                for (String msg : errMsg) {
                    sf.append(msg + ";");
                }
                this.errMsg = sf.toString();
            }
        } else {
            this.errMsg = this.errCode.getMsg();
        }
    }
}
