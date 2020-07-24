package com.insigma.ordercenter.logistics.best.sdk.tmsTraceQuery.response;


import lombok.Data;

@Data
public class Trace {
	private String opTime;
	private String opStatus;
	private String opDesc;
	private String operator;
	private String opLocation;
}
