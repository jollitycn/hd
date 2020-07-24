package com.insigma.ordercenter.logistics.best.sdk.twAsnNotify.request;

public class Service {
	private String serviceDefinitionCode;
	private ServiceCodeList serviceCodeList;

    public String getServiceDefinitionCode()
    {
        return this.serviceDefinitionCode;
    }

    public void setServiceDefinitionCode(String value)
    {
        this.serviceDefinitionCode = value;
    }

    public ServiceCodeList getServiceCodeList()
    {
        return this.serviceCodeList;
    }

    public void setServiceCodeList(ServiceCodeList value)
    {
        this.serviceCodeList = value;
    }


}
