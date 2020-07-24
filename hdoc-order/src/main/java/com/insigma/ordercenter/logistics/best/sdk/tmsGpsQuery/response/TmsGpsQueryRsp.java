package com.insigma.ordercenter.logistics.best.sdk.tmsGpsQuery.response;

import com.insigma.ordercenter.logistics.best.sdk.BaseResponse;

public class TmsGpsQueryRsp implements BaseResponse {
	private String orderCode;
	private ObSourceConcernedGpsInfo obSourceConcernedGpsInfo;
	private ObDestConcernedGpsInfo obDestConcernedGpsInfo;
	private TraceOmGpsList traceOmGpsList;
	private boolean result;
	private String errorCode;
	private String errorDescription;

    public String getOrderCode()
    {
        return this.orderCode;
    }

    public void setOrderCode(String value)
    {
        this.orderCode = value;
    }

    public ObSourceConcernedGpsInfo getObSourceConcernedGpsInfo()
    {
        return this.obSourceConcernedGpsInfo;
    }

    public void setObSourceConcernedGpsInfo(ObSourceConcernedGpsInfo value)
    {
        this.obSourceConcernedGpsInfo = value;
    }

    public ObDestConcernedGpsInfo getObDestConcernedGpsInfo()
    {
        return this.obDestConcernedGpsInfo;
    }

    public void setObDestConcernedGpsInfo(ObDestConcernedGpsInfo value)
    {
        this.obDestConcernedGpsInfo = value;
    }

    public TraceOmGpsList getTraceOmGpsList()
    {
        return this.traceOmGpsList;
    }

    public void setTraceOmGpsList(TraceOmGpsList value)
    {
        this.traceOmGpsList = value;
    }

    public boolean getResult()
    {
        return this.result;
    }

    public void setResult(boolean value)
    {
        this.result = value;
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
