package com.insigma.ordercenter.logistics.best.sdk.tmsTraceQuery.request;

import com.insigma.ordercenter.logistics.best.sdk.BaseRequest;
import com.insigma.ordercenter.logistics.best.sdk.BaseResponse;
import com.insigma.ordercenter.logistics.best.sdk.Parser;
import com.insigma.ordercenter.logistics.best.sdk.tmsTraceQuery.response.TmsTraceQueryRsp;
import lombok.Data;

@Data
public class TmsTraceQueryReq implements BaseRequest {
    private String orderCode;
    private String shipmentCode;
    private String customerCode;
    private String createTimeFrom;
    private String createTimeTo;

    @Override
    public String obtainServiceType() {
        return "TMS_TRACE_QUERY";
    }

    @Override
    public BaseResponse makeResponse(String rsp, String format) {
        if ("xml".equalsIgnoreCase(format)) {
            return Parser.coverXml2Object(rsp, TmsTraceQueryRsp.class);
        }
        return Parser.convertJson2Object(rsp, TmsTraceQueryRsp.class);

    }

}
