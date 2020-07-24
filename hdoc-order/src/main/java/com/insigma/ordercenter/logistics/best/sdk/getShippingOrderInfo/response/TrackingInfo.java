package com.insigma.ordercenter.logistics.best.sdk.getShippingOrderInfo.response;

import lombok.Data;

@Data
public class TrackingInfo {
	private String status;
	private String timePoint;
	private String courier;
	private String courierPhone;
	private String description;
}
