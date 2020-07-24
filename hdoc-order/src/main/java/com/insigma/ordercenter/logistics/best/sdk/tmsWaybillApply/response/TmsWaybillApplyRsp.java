package com.insigma.ordercenter.logistics.best.sdk.tmsWaybillApply.response;

import com.insigma.ordercenter.logistics.best.sdk.BaseResponse;

public class TmsWaybillApplyRsp implements BaseResponse {
	private boolean result;
	private Errors errors;
	private Waybills waybills;

    public boolean getResult()
    {
        return this.result;
    }

    public void setResult(boolean value)
    {
        this.result = value;
    }

    public Errors getErrors()
    {
        return this.errors;
    }

    public void setErrors(Errors value)
    {
        this.errors = value;
    }

    public Waybills getWaybills()
    {
        return this.waybills;
    }

    public void setWaybills(Waybills value)
    {
        this.waybills = value;
    }


}
