package com.insigma.ordercenter.logistics.best.sdk.wmsInvTransNotify.request;

import com.insigma.ordercenter.logistics.best.sdk.BaseRequest;
import com.insigma.ordercenter.logistics.best.sdk.BaseResponse;
import com.insigma.ordercenter.logistics.best.sdk.Parser;
import com.insigma.ordercenter.logistics.best.sdk.wmsInvTransNotify.response.WmsInvTransNotifyRsp;

public class WmsInvTransNotifyReq implements BaseRequest {
	private String warehouseCode;
	private String customerCode;
	private String orderCode;
	private String transferTypeCode;
	private String toCustomerCode;
	private String remark;
	private String udf1;
	private String udf2;
	private String udf3;
	private String udf4;
	private String udf5;
	private String udf6;
	private String udf7;
	private String udf8;
	private Products products;

    public String getWarehouseCode()
    {
        return this.warehouseCode;
    }

    public void setWarehouseCode(String value)
    {
        this.warehouseCode = value;
    }

    public String getCustomerCode()
    {
        return this.customerCode;
    }

    public void setCustomerCode(String value)
    {
        this.customerCode = value;
    }

    public String getOrderCode()
    {
        return this.orderCode;
    }

    public void setOrderCode(String value)
    {
        this.orderCode = value;
    }

    public String getTransferTypeCode()
    {
        return this.transferTypeCode;
    }

    public void setTransferTypeCode(String value)
    {
        this.transferTypeCode = value;
    }

    public String getToCustomerCode()
    {
        return this.toCustomerCode;
    }

    public void setToCustomerCode(String value)
    {
        this.toCustomerCode = value;
    }

    public String getRemark()
    {
        return this.remark;
    }

    public void setRemark(String value)
    {
        this.remark = value;
    }

    public String getUdf1()
    {
        return this.udf1;
    }

    public void setUdf1(String value)
    {
        this.udf1 = value;
    }

    public String getUdf2()
    {
        return this.udf2;
    }

    public void setUdf2(String value)
    {
        this.udf2 = value;
    }

    public String getUdf3()
    {
        return this.udf3;
    }

    public void setUdf3(String value)
    {
        this.udf3 = value;
    }

    public String getUdf4()
    {
        return this.udf4;
    }

    public void setUdf4(String value)
    {
        this.udf4 = value;
    }

    public String getUdf5()
    {
        return this.udf5;
    }

    public void setUdf5(String value)
    {
        this.udf5 = value;
    }

    public String getUdf6()
    {
        return this.udf6;
    }

    public void setUdf6(String value)
    {
        this.udf6 = value;
    }

    public String getUdf7()
    {
        return this.udf7;
    }

    public void setUdf7(String value)
    {
        this.udf7 = value;
    }

    public String getUdf8()
    {
        return this.udf8;
    }

    public void setUdf8(String value)
    {
        this.udf8 = value;
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
        return "WMS_INV_TRANS_NOTIFY";
    }

    @Override
    public BaseResponse makeResponse(String rsp, String format) {
        if ("xml".equalsIgnoreCase(format)) {
			return Parser.coverXml2Object(rsp, WmsInvTransNotifyRsp.class);
		}
		return Parser.convertJson2Object(rsp, WmsInvTransNotifyRsp.class);

    }

}
