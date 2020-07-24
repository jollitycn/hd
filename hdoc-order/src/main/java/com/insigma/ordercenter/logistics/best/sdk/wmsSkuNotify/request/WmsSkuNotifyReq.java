package com.insigma.ordercenter.logistics.best.sdk.wmsSkuNotify.request;

import com.insigma.ordercenter.logistics.best.sdk.BaseRequest;
import com.insigma.ordercenter.logistics.best.sdk.BaseResponse;
import com.insigma.ordercenter.logistics.best.sdk.Parser;
import com.insigma.ordercenter.logistics.best.sdk.wmsSkuNotify.response.WmsSkuNotifyRsp;

public class WmsSkuNotifyReq implements BaseRequest {
	private String providerCode;
	private Products products;

    public String getProviderCode()
    {
        return this.providerCode;
    }

    public void setProviderCode(String value)
    {
        this.providerCode = value;
    }

    public Products getProducts()
    {
        return this.products;
    }

    public void setProducts(Products value)
    {
        this.products = value;
    }

    @Override
    public String obtainServiceType() {
        return "WMS_SKU_NOTIFY";
    }

    @Override
    public BaseResponse makeResponse(String rsp, String format) {
        if ("xml".equalsIgnoreCase(format)) {
			return Parser.coverXml2Object(rsp, WmsSkuNotifyRsp.class);
		}
		return Parser.convertJson2Object(rsp, WmsSkuNotifyRsp.class);

    }

}