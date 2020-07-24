package com.insigma.ordercenter.logistics.best.sdk.tmsTraceQuery.response;

import lombok.Data;

@Data
public class Item {
	private String itemCode;
	private String itemName;
	private String packageUomCode;
	private long count;
	private double weight;
	private double volume;
}
