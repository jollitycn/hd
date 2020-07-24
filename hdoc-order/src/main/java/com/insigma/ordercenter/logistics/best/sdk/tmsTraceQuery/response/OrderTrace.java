package com.insigma.ordercenter.logistics.best.sdk.tmsTraceQuery.response;

import lombok.Data;

@Data
public class OrderTrace {
	private String traceTime;
	private String traceType;
	private String traceDesc;
	private String traceCity;
	private String operator;
	private String contacter;
	private String contactPhone;
	private String siteType;
	private String siteCode;
	private String siteName;
}
