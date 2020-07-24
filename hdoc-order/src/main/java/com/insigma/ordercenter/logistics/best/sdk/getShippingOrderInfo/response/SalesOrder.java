package com.insigma.ordercenter.logistics.best.sdk.getShippingOrderInfo.response;

import lombok.Data;

@Data
public class SalesOrder {
	private String orderCode;
	private String note;
	private ShippingOrders shippingOrders;
}
