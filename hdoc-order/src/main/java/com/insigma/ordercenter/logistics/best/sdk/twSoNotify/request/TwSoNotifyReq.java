package com.insigma.ordercenter.logistics.best.sdk.twSoNotify.request;

import com.insigma.ordercenter.logistics.best.sdk.BaseRequest;
import com.insigma.ordercenter.logistics.best.sdk.BaseResponse;
import com.insigma.ordercenter.logistics.best.sdk.Parser;
import com.insigma.ordercenter.logistics.best.sdk.twSoNotify.response.TwSoNotifyRsp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("发货信息入参实体")
public class TwSoNotifyReq implements BaseRequest, Serializable {

	@ApiModelProperty(name = "operationFlag",value = "业务标识配送(W-仓,T-配,WT-仓+配,)")
	private String operationFlag;

	@ApiModelProperty(name = "customerCode",value = "客户编码（百世提供，唯一）")
	private String customerCode;

	@ApiModelProperty(name = "customerName",value = "客户名称（百世提供）")
	private String customerName;

	@ApiModelProperty(name = "projectCode",value = "项目编码（百世提供）项目运作地代码")
	private String projectCode;

	@ApiModelProperty(name = "transportMode",value = "运输模式，FTL-整车 LTL-零担 AIR-空运 EXP-快递（填写符号，如：EXP）")
	private String transportMode;

	@ApiModelProperty(name = "vehicleModel",value = "车型，当运输模式传入为FTL-整车，必传")
	private String vehicleModel;

	@ApiModelProperty(name = "logisticsCode",value = "承运商类型（百世快递 BEX,百世快运BTR）")
	private String logisticsCode;

	@ApiModelProperty(name = "orderDescription",value = "订单描述目前可传JY（禁运）")
	private String orderDescription;

	@ApiModelProperty(name = "shippingOrderNo",value = "外部运单号(快递单号必填)")
	private String shippingOrderNo;

	@ApiModelProperty(name = "orderCode",value = "外部单号（客户单号，下单时保证唯一）")
	private String orderCode;

	@ApiModelProperty(name = "productCode",value = "产品类型")
	private String productCode;

	@ApiModelProperty(name = "tradeMark",value = "品牌")
	private String tradeMark;

	@ApiModelProperty(name = "totalAmount",value = "货物金额")
	private double totalAmount;

	@ApiModelProperty(name = "isPaymentCollected",value = "是否代收货款")
	private boolean isPaymentCollected;

	@ApiModelProperty(name = "goodsValue",value = "货物COD服务金额时，必填")
	private double goodsValue;

	@ApiModelProperty(name = "cheapAmount",value = "优惠金额，存在COD服务时，必填。若无优惠金额，则填0")
	private double cheapAmount;

	@ApiModelProperty(name = "warehouseCode",value = "仓库代码")
	private String warehouseCode;

	@ApiModelProperty(name = "actionType",value = "操作类型：ADD-新增")
	private String actionType;

	@ApiModelProperty(name = "extTradeId",value = "卖家销售订单编号（即客户单号）")
	private String extTradeId;

	@ApiModelProperty(name = "operationTypeCode",value = "订单类型：NORMAL-普通订单/交易订单（B2C）,WDO-出库单/非交易订单（B2B）")
	private String operationTypeCode;

	@ApiModelProperty(name = "extOrderType",value = "外部订单类型：赋值：销售出库、采购退货（由前端系统定义6种上传，后续反馈百世按照6种类型推送）")
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
