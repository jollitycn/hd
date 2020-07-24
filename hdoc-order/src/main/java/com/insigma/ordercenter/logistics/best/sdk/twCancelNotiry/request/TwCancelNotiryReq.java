package com.insigma.ordercenter.logistics.best.sdk.twCancelNotiry.request;

import com.insigma.ordercenter.logistics.best.sdk.BaseRequest;
import com.insigma.ordercenter.logistics.best.sdk.BaseResponse;
import com.insigma.ordercenter.logistics.best.sdk.Parser;
import com.insigma.ordercenter.logistics.best.sdk.twCancelNotiry.response.TwCancelNotiryRsp;

public class TwCancelNotiryReq implements BaseRequest {
	private String customerCode;
	private String orderCode;
	private String operationTypeCode;

    public String getCustomerCode()
    {
        return this.customerCode;
    }

    public void setCustomerCode(String value)
    {
        this.customerCode = value;
    }

    public String getOrderCode()
    {
        return this.orderCode;
    }

    public void setOrderCode(String value)
    {
        this.orderCode = value;
    }

    public String getOperationTypeCode()
    {
        return this.operationTypeCode;
    }

    public void setOperationTypeCode(String value)
    {
        this.operationTypeCode = value;
    }

    @Override
    public String obtainServiceType() {
        return "TW_CANCEL_NOTIRY";
    }

    @Override
    public BaseResponse makeResponse(String rsp, String format) {
        if ("xml".equalsIgnoreCase(format)) {
			return Parser.coverXml2Object(rsp, TwCancelNotiryRsp.class);
		}
		return Parser.convertJson2Object(rsp, TwCancelNotiryRsp.class);

    }

}
