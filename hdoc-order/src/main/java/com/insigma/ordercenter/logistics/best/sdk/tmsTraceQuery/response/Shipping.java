package com.insigma.ordercenter.logistics.best.sdk.tmsTraceQuery.response;

import lombok.Data;

@Data
public class Shipping {
	private String logisticsCode;
	private String logisticsName;
	private String shipmentCode;
	private String transportMode;
	private String sourceLocationAddress;
	private String destLocationAddress;
	private String operateTime;
	private String operator;
	private String driverName;
	private String driverPhone;
	private String operateDescription;
	private String shipmentStatus;
	private boolean cod;
	private double codAmount;
	private double goodsValue;
	private double cheapAmount;
	private String codStatus;
	private String codPayType;
	private Items items;
	private Traces traces;
	private String referenceShipCode;
}
