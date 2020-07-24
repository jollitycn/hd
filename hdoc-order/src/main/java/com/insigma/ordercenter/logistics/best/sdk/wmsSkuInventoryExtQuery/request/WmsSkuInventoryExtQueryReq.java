package com.insigma.ordercenter.logistics.best.sdk.wmsSkuInventoryExtQuery.request;

import com.insigma.ordercenter.logistics.best.sdk.BaseRequest;
import com.insigma.ordercenter.logistics.best.sdk.BaseResponse;
import com.insigma.ordercenter.logistics.best.sdk.Parser;
import com.insigma.ordercenter.logistics.best.sdk.wmsSkuInventoryExtQuery.response.WmsSkuInventoryExtQueryRsp;

public class WmsSkuInventoryExtQueryReq implements BaseRequest {
	private String customerCode;
	private String warehouseCode;
	private Products products;

    public String getCustomerCode()
    {
        return this.customerCode;
    }

    public void setCustomerCode(String value)
    {
        this.customerCode = value;
    }

    public String getWarehouseCode()
    {
        return this.warehouseCode;
    }

    public void setWarehouseCode(String value)
    {
        this.warehouseCode = value;
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
        return "WMS_SKU_INVENTORY_EXT_QUERY";
    }

    @Override
    public BaseResponse makeResponse(String rsp, String format) {
        if ("xml".equalsIgnoreCase(format)) {
			return Parser.coverXml2Object(rsp, WmsSkuInventoryExtQueryRsp.class);
		}
		return Parser.convertJson2Object(rsp, WmsSkuInventoryExtQueryRsp.class);

    }

}
