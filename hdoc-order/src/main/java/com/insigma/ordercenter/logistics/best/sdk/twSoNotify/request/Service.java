package com.insigma.ordercenter.logistics.best.sdk.twSoNotify.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class Service implements Serializable {
	private String serviceDefinitionCode;
	private String remark;
	private ServiceCodeList serviceCodeList;
}
