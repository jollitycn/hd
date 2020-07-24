package com.insigma.ordercenter.logistics.best.sdk.twSoNotify.request;

import com.insigma.ordercenter.logistics.best.sdk.BaseRequest;
import com.insigma.ordercenter.logistics.best.sdk.BaseResponse;
import com.insigma.ordercenter.logistics.best.sdk.Parser;
import com.insigma.ordercenter.logistics.best.sdk.twSoNotify.response.TwSoNotifyRsp;
import lombok.Data;

import java.io.Serializable;

@Data
public class TwSoNotifyReq implements BaseRequest, Serializable {
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
	private String productCode;
	private String tradeMark;
	private double totalAmount;
	private boolean isPaymentCollected;
	private double goodsValue;
	private double cheapAmount;
	private String warehouseCode;
	private String actionType;
	private String extTradeId;
	private String operationTypeCode;
	private String extOrderType;
	private String orderSource;
	private String orderTime;
	private String paymentTime;
	private double shippingAmount;
	private double discountAmount;
	private double actualAmount;
	private boolean isValueDeclared;
	private double declaringValueAmount;
	private String logisticsProviderCode;
	private String tmsCompany;
	private String tmsLinkman;
	private String tmsPhone;
	private String tmsLinkmanNo;
	private String tmsShippingNo;
	private String buyerName;
	private String buyerPhone;
	private String fetchGoodsLocation;
	private String sellerName;
	private String priorityCode;
	private String remark;
	private String shipmentTime;
	private String shipmentFinishTime;
	private String deliveryTime;
	private double totalWeight;
	private double totalVolume;
	private boolean udfFlag;
	private String udf1;
	private String udf2;
	private String udf3;
	private String udf4;
	private String udf5;
	private String udf6;
	private String udf7;
	private String udf8;
	private ReferenceReqList referenceReqList;
	private Sender sender;
	private Receiver receiver;
	private ServiceList serviceList;
	private ItemList itemList;
	private String storeCode;
	private String businessScenarioCode;

    @Override
    public String obtainServiceType() {
        return "TW_SO_NOTIFY";
    }

    @Override
    public BaseResponse makeResponse(String rsp, String format) {
        if ("xml".equalsIgnoreCase(format)) {
			return Parser.coverXml2Object(rsp, TwSoNotifyRsp.class);
		}
		return Parser.convertJson2Object(rsp, TwSoNotifyRsp.class);

    }

}
