package com.insigma.ordercenter.logistics.best.sdk.wmsSkuInventoryExtQuery.response;

import com.insigma.ordercenter.logistics.best.sdk.BaseResponse;

public class WmsSkuInventoryExtQueryRsp implements BaseResponse {
	private boolean result;
	private String note;
	private Errors errors;
	private Products products;

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

    public Errors getErrors()
    {
        return this.errors;
    }

    public void setErrors(Errors value)
    {
        this.errors = value;
    }

    public Products getProducts()
    {
        return this.products;
    }

    public void setProducts(Products value)
    {
        this.products = value;
    }


}
