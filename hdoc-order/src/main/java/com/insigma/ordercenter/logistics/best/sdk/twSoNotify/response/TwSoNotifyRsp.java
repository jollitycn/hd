package com.insigma.ordercenter.logistics.best.sdk.twSoNotify.response;

import com.insigma.ordercenter.logistics.best.sdk.BaseResponse;
import lombok.Data;

@Data
public class TwSoNotifyRsp implements BaseResponse {
	private boolean result;
	private String note;
	private String errorCode;
	private String errorDescription;
}
