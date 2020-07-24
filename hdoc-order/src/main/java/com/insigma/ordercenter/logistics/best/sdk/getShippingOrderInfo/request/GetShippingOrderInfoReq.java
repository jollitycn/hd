package com.insigma.ordercenter.logistics.best.sdk.getShippingOrderInfo.request;

import com.insigma.ordercenter.logistics.best.sdk.BaseRequest;
import com.insigma.ordercenter.logistics.best.sdk.BaseResponse;
import com.insigma.ordercenter.logistics.best.sdk.Parser;
import com.insigma.ordercenter.logistics.best.sdk.getShippingOrderInfo.response.GetShippingOrderInfoRsp;
import lombok.Data;

@Data
public class GetShippingOrderInfoReq implements BaseRequest {
	private String customerCode;
	private String warehouseCode;
	private ShippingOrders shippingOrders;

    @Override
    public String obtainServiceType() {
        return "GetShippingOrderInfo";
    }

    @Override
    public BaseResponse makeResponse(String rsp, String format) {
        if ("xml".equalsIgnoreCase(format)) {
			return Parser.coverXml2Object(rsp, GetShippingOrderInfoRsp.class);
		}
		return Parser.convertJson2Object(rsp, GetShippingOrderInfoRsp.class);

    }

}
