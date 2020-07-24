package com.insigma.ordercenter.logistics.best.sdk.wmsInvTransNotify.response;

import com.insigma.ordercenter.logistics.best.sdk.BaseResponse;

public class WmsInvTransNotifyRsp implements BaseResponse {
	private boolean result;
	private String note;
	private String errorCode;
	private String errorDescription;

    public boolean getResult()
    {
        return this.result;
    }

    public void setResult(boolean value)
    {
        this.result = value;
    }

    public String getNote()
    {
        return this.note;
    }

    public void setNote(String value)
    {
        this.note = value;
    }

    public String getErrorCode()
    {
        return this.errorCode;
    }

    public void setErrorCode(String value)
    {
        this.errorCode = value;
    }

    public String getErrorDescription()
    {
        return this.errorDescription;
    }

    public void setErrorDescription(String value)
    {
        this.errorDescription = value;
    }


}
