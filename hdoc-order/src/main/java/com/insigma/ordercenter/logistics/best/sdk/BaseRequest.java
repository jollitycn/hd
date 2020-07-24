package com.insigma.ordercenter.logistics.best.sdk;

public interface BaseRequest {
    public String obtainServiceType();

    BaseResponse makeResponse(String rsp, String format);
}
