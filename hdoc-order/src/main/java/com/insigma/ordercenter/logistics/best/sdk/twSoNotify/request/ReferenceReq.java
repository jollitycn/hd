package com.insigma.ordercenter.logistics.best.sdk.twSoNotify.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReferenceReq implements Serializable {
	private String outorderType;
	private String outorderValue;
}
