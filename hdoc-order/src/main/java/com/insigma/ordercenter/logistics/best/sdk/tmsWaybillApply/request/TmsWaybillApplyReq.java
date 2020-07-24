package com.insigma.ordercenter.logistics.best.sdk.tmsWaybillApply.request;

import com.insigma.ordercenter.logistics.best.sdk.BaseRequest;
import com.insigma.ordercenter.logistics.best.sdk.BaseResponse;
import com.insigma.ordercenter.logistics.best.sdk.Parser;
import com.insigma.ordercenter.logistics.best.sdk.tmsWaybillApply.response.TmsWaybillApplyRsp;

public class TmsWaybillApplyReq implements BaseRequest {
	private String customerCode;
	private String projectCode;
	private String siteFlag;
	private String requestID;
	private Orders orders;

    public String getCustomerCode()
    {
        return this.customerCode;
    }

    public void setCustomerCode(String value)
    {
        this.customerCode = value;
    }

    public String getProjectCode()
    {
        return this.projectCode;
    }

    public void setProjectCode(String value)
    {
        this.projectCode = value;
    }

    public String getSiteFlag()
    {
        return this.siteFlag;
    }

    public void setSiteFlag(String value)
    {
        this.siteFlag = value;
    }

    public String getRequestID()
    {
        return this.requestID;
    }

    public void setRequestID(String value)
    {
        this.requestID = value;
    }

    public Orders getOrders()
    {
        return this.orders;
    }

    public void setOrders(Orders value)
    {
        this.orders = value;
    }

    @Override
    public String obtainServiceType() {
        return "TMS_WAYBILL_APPLY";
    }

    @Override
    public BaseResponse makeResponse(String rsp, String format) {
        if ("xml".equalsIgnoreCase(format)) {
			return Parser.coverXml2Object(rsp, TmsWaybillApplyRsp.class);
		}
		return Parser.convertJson2Object(rsp, TmsWaybillApplyRsp.class);

    }

}
