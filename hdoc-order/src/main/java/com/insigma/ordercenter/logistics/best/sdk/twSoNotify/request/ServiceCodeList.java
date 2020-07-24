package com.insigma.ordercenter.logistics.best.sdk.twSoNotify.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ServiceCodeList implements Serializable {
	private List<ServiceCode> serviceCode;
}
