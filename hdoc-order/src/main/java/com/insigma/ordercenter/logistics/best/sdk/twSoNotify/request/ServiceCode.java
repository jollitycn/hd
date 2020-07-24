package com.insigma.ordercenter.logistics.best.sdk.twSoNotify.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServiceCode implements Serializable {
	private String serviceDefParameterCode;
	private String actualValue;
}
