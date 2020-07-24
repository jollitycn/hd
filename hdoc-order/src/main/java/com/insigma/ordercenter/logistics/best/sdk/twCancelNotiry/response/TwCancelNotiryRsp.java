package com.insigma.ordercenter.logistics.best.sdk.twCancelNotiry.response;

import com.insigma.ordercenter.logistics.best.sdk.BaseResponse;
import lombok.Data;

@Data
public class TwCancelNotiryRsp implements BaseResponse {
	private boolean result;
	private String note;
	private String errorDescription;
}
