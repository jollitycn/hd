package com.insigma.ordercenter.logistics.best.sdk.tmsTraceQuery.response;

import com.insigma.ordercenter.logistics.best.sdk.BaseResponse;
import lombok.Data;

@Data
public class TmsTraceQueryRsp implements BaseResponse {
	private boolean result;
	private Errors errors;
	private OrderInfos orderInfos;

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

    public OrderInfos getOrderInfos()
    {
        return this.orderInfos;
    }

    public void setOrderInfos(OrderInfos value)
    {
        this.orderInfos = value;
    }


}
