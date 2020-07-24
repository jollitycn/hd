package com.insigma.ordercenter.logistics.best.sdk.tmsTraceQuery.response;

import lombok.Data;

@Data
public class OrderInfo {
	private String customerCode;
	private String customerName;
	private String projectCode;
	private String orderCode;
	private String status;
	private String portalUrl;
	private String currentStatusDatetime;
	private String currentStatusLocation;
	private String currentStatusDescription;
	private String currentStatusUpdator;
	private Shippings shippings;
	private OrderTraces orderTraces;
	private String tmsCode;
	private Items items;
}
