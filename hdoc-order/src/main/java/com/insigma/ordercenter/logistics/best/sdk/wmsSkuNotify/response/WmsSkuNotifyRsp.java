package com.insigma.ordercenter.logistics.best.sdk.wmsSkuNotify.response;

import com.insigma.ordercenter.logistics.best.sdk.BaseResponse;
import lombok.Data;

@Data
public class WmsSkuNotifyRsp implements BaseResponse {
	private boolean result;
	private String note;
	private Errors errors;
}
