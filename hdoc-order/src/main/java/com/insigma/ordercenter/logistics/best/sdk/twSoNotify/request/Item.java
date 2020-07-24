package com.insigma.ordercenter.logistics.best.sdk.twSoNotify.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class Item implements Serializable {
	private int lineNo;
	private String itemSkuCode;
	private String itemName;
	private int itemQuantity;
	private int packageCount;
	private String uomCode;
	private double weight;
	private double volume;
	private double volumeWeight;
	private double unitPrice;
	private double declaredValue;
	private String fixStatusCode;
	private String productionDate;
	private String expiryDate;
	private String lotAtt01;
	private String lotAtt02;
	private String lotAtt03;
	private String lotAtt04;
	private String lotAtt05;
	private String lotAtt06;
	private String providerCode;
	private String providerFrom;
	private String providerName;
	private String packCode;
	private boolean udfFlag;
	private String udf1;
	private String udf2;
	private String udf3;
	private String udf4;
	private String udf5;
	private String udf6;
	private String udf7;
	private String udf8;
	private String packageStandard;
	private String remark;
	private double decimalQuantity;
	private String packageName;
}
