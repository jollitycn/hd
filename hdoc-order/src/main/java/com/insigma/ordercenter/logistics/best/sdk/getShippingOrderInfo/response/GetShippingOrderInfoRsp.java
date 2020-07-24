package com.insigma.ordercenter.logistics.best.sdk.getShippingOrderInfo.response;

import com.insigma.ordercenter.logistics.best.sdk.BaseResponse;
import lombok.Data;

@Data
public class GetShippingOrderInfoRsp implements BaseResponse {
	private String flag;
	private String note;
	private Errors errors;
	private SalesOrders salesOrders;
}
