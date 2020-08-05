package com.insigma.ordercenter.logistics.best.sdk.twCancelNotiry.request;

import com.insigma.ordercenter.logistics.best.sdk.BaseRequest;
import com.insigma.ordercenter.logistics.best.sdk.BaseResponse;
import com.insigma.ordercenter.logistics.best.sdk.Parser;
import com.insigma.ordercenter.logistics.best.sdk.twCancelNotiry.response.TwCancelNotiryRsp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("取消的发货单信息入参实体")
public class TwCancelNotiryReq implements BaseRequest {

    @ApiModelProperty(name = "strategyId",value = "单据类型：入库单：ASN,退货单：DI,销售B2B单：WDO,销售B2C单：NORMAL")
	private String customerCode;

    @ApiModelProperty(name = "orderCode",value = "外部单号，保证唯一")
    private String orderCode;

    @ApiModelProperty(name = "customerCode",value = "客户编码，保证唯一")
	private String operationTypeCode;

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

    public String getOperationTypeCode()
    {
        return this.operationTypeCode;
    }

    public void setOperationTypeCode(String value)
    {
        this.operationTypeCode = value;
    }

    @Override
    public String obtainServiceType() {
        return "TW_CANCEL_NOTIRY";
    }

    @Override
    public BaseResponse makeResponse(String rsp, String format) {
        if ("xml".equalsIgnoreCase(format)) {
			return Parser.coverXml2Object(rsp, TwCancelNotiryRsp.class);
		}
		return Parser.convertJson2Object(rsp, TwCancelNotiryRsp.class);

    }

}
