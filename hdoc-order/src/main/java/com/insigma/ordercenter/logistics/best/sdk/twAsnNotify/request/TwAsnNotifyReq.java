package com.insigma.ordercenter.logistics.best.sdk.twAsnNotify.request;

import com.insigma.ordercenter.logistics.best.sdk.BaseRequest;
import com.insigma.ordercenter.logistics.best.sdk.BaseResponse;
import com.insigma.ordercenter.logistics.best.sdk.Parser;
import com.insigma.ordercenter.logistics.best.sdk.twAsnNotify.response.TwAsnNotifyRsp;

public class TwAsnNotifyReq implements BaseRequest {
	private String operationFlag;
	private String customerCode;
	private String customerName;
	private String projectCode;
	private String transportMode;
	private String vehicleModel;
	private String logisticsCode;
	private String orderDescription;
	private String shippingOrderNo;
	private String orderCode;
	private String asnOrders;
	private String productCode;
	private String tradeMark;
	private double totalAmount;
	private String warehouseCode;
	private String actionType;
	private String refOrderCode;
	private String extTradeId;
	private String operationTypeCode;
	private String extOrderType;
	private String orderTime;
	private String earliestArrivalTime;
	private String latestArrivalTime;
	private String tmsCompany;
	private String tmsLinkman;
	private String tmsPhone;
	private String tmsLinkmanNo;
	private String tmsShippingNo;
	private String remark;
	private boolean udfFlag;
	private String udf1;
	private String udf2;
	private String udf3;
	private String udf4;
	private String udf5;
	private String udf6;
	private String udf7;
	private String udf8;
	private Sender sender;
	private Receiver receiver;
	private ServiceList serviceList;
	private ItemList itemList;
	private ReferenceReqList referenceReqList;
	private double goodsValue;
	private double cheapAmount;
	private String businessScenarioCode;

    public String getOperationFlag()
    {
        return this.operationFlag;
    }

    public void setOperationFlag(String value)
    {
        this.operationFlag = value;
    }

    public String getCustomerCode()
    {
        return this.customerCode;
    }

    public void setCustomerCode(String value)
    {
        this.customerCode = value;
    }

    public String getCustomerName()
    {
        return this.customerName;
    }

    public void setCustomerName(String value)
    {
        this.customerName = value;
    }

    public String getProjectCode()
    {
        return this.projectCode;
    }

    public void setProjectCode(String value)
    {
        this.projectCode = value;
    }

    public String getTransportMode()
    {
        return this.transportMode;
    }

    public void setTransportMode(String value)
    {
        this.transportMode = value;
    }

    public String getVehicleModel()
    {
        return this.vehicleModel;
    }

    public void setVehicleModel(String value)
    {
        this.vehicleModel = value;
    }

    public String getLogisticsCode()
    {
        return this.logisticsCode;
    }

    public void setLogisticsCode(String value)
    {
        this.logisticsCode = value;
    }

    public String getOrderDescription()
    {
        return this.orderDescription;
    }

    public void setOrderDescription(String value)
    {
        this.orderDescription = value;
    }

    public String getShippingOrderNo()
    {
        return this.shippingOrderNo;
    }

    public void setShippingOrderNo(String value)
    {
        this.shippingOrderNo = value;
    }

    public String getOrderCode()
    {
        return this.orderCode;
    }

    public void setOrderCode(String value)
    {
        this.orderCode = value;
    }

    public String getAsnOrders()
    {
        return this.asnOrders;
    }

    public void setAsnOrders(String value)
    {
        this.asnOrders = value;
    }

    public String getProductCode()
    {
        return this.productCode;
    }

    public void setProductCode(String value)
    {
        this.productCode = value;
    }

    public String getTradeMark()
    {
        return this.tradeMark;
    }

    public void setTradeMark(String value)
    {
        this.tradeMark = value;
    }

    public double getTotalAmount()
    {
        return this.totalAmount;
    }

    public void setTotalAmount(double value)
    {
        this.totalAmount = value;
    }

    public String getWarehouseCode()
    {
        return this.warehouseCode;
    }

    public void setWarehouseCode(String value)
    {
        this.warehouseCode = value;
    }

    public String getActionType()
    {
        return this.actionType;
    }

    public void setActionType(String value)
    {
        this.actionType = value;
    }

    public String getRefOrderCode()
    {
        return this.refOrderCode;
    }

    public void setRefOrderCode(String value)
    {
        this.refOrderCode = value;
    }

    public String getExtTradeId()
    {
        return this.extTradeId;
    }

    public void setExtTradeId(String value)
    {
        this.extTradeId = value;
    }

    public String getOperationTypeCode()
    {
        return this.operationTypeCode;
    }

    public void setOperationTypeCode(String value)
    {
        this.operationTypeCode = value;
    }

    public String getExtOrderType()
    {
        return this.extOrderType;
    }

    public void setExtOrderType(String value)
    {
        this.extOrderType = value;
    }

    public String getOrderTime()
    {
        return this.orderTime;
    }

    public void setOrderTime(String value)
    {
        this.orderTime = value;
    }

    public String getEarliestArrivalTime()
    {
        return this.earliestArrivalTime;
    }

    public void setEarliestArrivalTime(String value)
    {
        this.earliestArrivalTime = value;
    }

    public String getLatestArrivalTime()
    {
        return this.latestArrivalTime;
    }

    public void setLatestArrivalTime(String value)
    {
        this.latestArrivalTime = value;
    }

    public String getTmsCompany()
    {
        return this.tmsCompany;
    }

    public void setTmsCompany(String value)
    {
        this.tmsCompany = value;
    }

    public String getTmsLinkman()
    {
        return this.tmsLinkman;
    }

    public void setTmsLinkman(String value)
    {
        this.tmsLinkman = value;
    }

    public String getTmsPhone()
    {
        return this.tmsPhone;
    }

    public void setTmsPhone(String value)
    {
        this.tmsPhone = value;
    }

    public String getTmsLinkmanNo()
    {
        return this.tmsLinkmanNo;
    }

    public void setTmsLinkmanNo(String value)
    {
        this.tmsLinkmanNo = value;
    }

    public String getTmsShippingNo()
    {
        return this.tmsShippingNo;
    }

    public void setTmsShippingNo(String value)
    {
        this.tmsShippingNo = value;
    }

    public String getRemark()
    {
        return this.remark;
    }

    public void setRemark(String value)
    {
        this.remark = value;
    }

    public boolean getUdfFlag()
    {
        return this.udfFlag;
    }

    public void setUdfFlag(boolean value)
    {
        this.udfFlag = value;
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

    public Sender getSender()
    {
        return this.sender;
    }

    public void setSender(Sender value)
    {
        this.sender = value;
    }

    public Receiver getReceiver()
    {
        return this.receiver;
    }

    public void setReceiver(Receiver value)
    {
        this.receiver = value;
    }

    public ServiceList getServiceList()
    {
        return this.serviceList;
    }

    public void setServiceList(ServiceList value)
    {
        this.serviceList = value;
    }

    public ItemList getItemList()
    {
        return this.itemList;
    }

    public void setItemList(ItemList value)
    {
        this.itemList = value;
    }

    public ReferenceReqList getReferenceReqList()
    {
        return this.referenceReqList;
    }

    public void setReferenceReqList(ReferenceReqList value)
    {
        this.referenceReqList = value;
    }

    public double getGoodsValue()
    {
        return this.goodsValue;
    }

    public void setGoodsValue(double value)
    {
        this.goodsValue = value;
    }

    public double getCheapAmount()
    {
        return this.cheapAmount;
    }

    public void setCheapAmount(double value)
    {
        this.cheapAmount = value;
    }

    public String getBusinessScenarioCode()
    {
        return this.businessScenarioCode;
    }

    public void setBusinessScenarioCode(String value)
    {
        this.businessScenarioCode = value;
    }

    @Override
    public String obtainServiceType() {
        return "TW_ASN_NOTIFY";
    }

    @Override
    public BaseResponse makeResponse(String rsp, String format) {
        if ("xml".equalsIgnoreCase(format)) {
			return Parser.coverXml2Object(rsp, TwAsnNotifyRsp.class);
		}
		return Parser.convertJson2Object(rsp, TwAsnNotifyRsp.class);

    }

}
