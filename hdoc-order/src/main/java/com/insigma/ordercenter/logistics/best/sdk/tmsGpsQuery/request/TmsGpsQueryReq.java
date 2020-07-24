package com.insigma.ordercenter.logistics.best.sdk.tmsGpsQuery.request;

import com.insigma.ordercenter.logistics.best.sdk.BaseRequest;
import com.insigma.ordercenter.logistics.best.sdk.BaseResponse;
import com.insigma.ordercenter.logistics.best.sdk.Parser;
import com.insigma.ordercenter.logistics.best.sdk.tmsGpsQuery.response.TmsGpsQueryRsp;

public class TmsGpsQueryReq implements BaseRequest {
	private String projectCode;
	private String orderCode;
	private long startTime;
	private long endTime;

    public String getProjectCode()
    {
        return this.projectCode;
    }

    public void setProjectCode(String value)
    {
        this.projectCode = value;
    }

    public String getOrderCode()
    {
        return this.orderCode;
    }

    public void setOrderCode(String value)
    {
        this.orderCode = value;
    }

    public long getStartTime()
    {
        return this.startTime;
    }

    public void setStartTime(long value)
    {
        this.startTime = value;
    }

    public long getEndTime()
    {
        return this.endTime;
    }

    public void setEndTime(long value)
    {
        this.endTime = value;
    }

    @Override
    public String obtainServiceType() {
        return "TMS_GPS_QUERY";
    }

    @Override
    public BaseResponse makeResponse(String rsp, String format) {
        if ("xml".equalsIgnoreCase(format)) {
			return Parser.coverXml2Object(rsp, TmsGpsQueryRsp.class);
		}
		return Parser.convertJson2Object(rsp, TmsGpsQueryRsp.class);

    }

}
