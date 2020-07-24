package com.insigma.ordercenter.logistics.best.sdk.getShippingOrderInfo.response;

import lombok.Data;

@Data
public class ShippingOrder {
	private String status;
	private TrackingInfoList trackingInfoList;
	private String logisticsProviderCode;
	private String shippingOrderNo;
}
