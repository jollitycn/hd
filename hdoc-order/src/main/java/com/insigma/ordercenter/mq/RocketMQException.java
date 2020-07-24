package com.insigma.ordercenter.mq;

public class RocketMQException  extends AppException {
    private static final long serialVersionUID = 1L;

    public RocketMQException() {
    }

    public RocketMQException(Throwable e) {
        super(e);
    }


    public RocketMQException(ErrorCode errorType) {
        super(errorType, new String[0]);
    }


    public RocketMQException(ErrorCode errorCode, String... errMsg) {
        super(errorCode, errMsg);
    }


    public RocketMQException(ErrorCode errorCode, String errMsg, Boolean isTransfer) {
        super(errorCode, errMsg, isTransfer);
    }


    public RocketMQException(ErrorCode errCode, Throwable cause, String... errMsg) {
        super(errCode, cause, errMsg);
    }
}
