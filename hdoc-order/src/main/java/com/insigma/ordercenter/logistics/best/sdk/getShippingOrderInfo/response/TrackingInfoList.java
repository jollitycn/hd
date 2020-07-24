package com.insigma.ordercenter.logistics.best.sdk.getShippingOrderInfo.response;

import lombok.Data;

import java.util.List;

@Data
public class TrackingInfoList {
	private List<TrackingInfo> trackingInfo;
}
