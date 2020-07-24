package com.insigma.ordercenter.logistics.best.sdk.wmsSkuInventoryExtQuery.request;



public class Product {
	private String itemSkuCode;
	private String providerCode;

    public String getItemSkuCode()
    {
        return this.itemSkuCode;
    }

    public void setItemSkuCode(String value)
    {
        this.itemSkuCode = value;
    }

    public String getProviderCode()
    {
        return this.providerCode;
    }

    public void setProviderCode(String value)
    {
        this.providerCode = value;
    }


}
