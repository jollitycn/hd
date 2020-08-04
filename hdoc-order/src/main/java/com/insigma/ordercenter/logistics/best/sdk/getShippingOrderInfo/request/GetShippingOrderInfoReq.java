package com.insigma.ordercenter.logistics.best.sdk.getShippingOrderInfo.request;

import com.insigma.ordercenter.logistics.best.sdk.BaseRequest;
import com.insigma.ordercenter.logistics.best.sdk.BaseResponse;
import com.insigma.ordercenter.logistics.best.sdk.Parser;
import com.insigma.ordercenter.logistics.best.sdk.getShippingOrderInfo.response.GetShippingOrderInfoRsp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("运单信息查询实体")
public class GetShippingOrderInfoReq implements BaseRequest {

    @ApiModelProperty(name = "customerCode",value = "卖家编码，保证唯一")
	private String customerCode;

    @ApiModelProperty(name = "warehouseCode",value = "仓库编码")
	private String warehouseCode;

    @ApiModelProperty(name = "shippingOrder",value = "外部单号，可以多个")
	private ShippingOrders shippingOrders;

    @Override
    public String obtainServiceType() {
        return "GetShippingOrderInfo";
    }

    @Override
    public BaseResponse makeResponse(String rsp, String format) {
        if ("xml".equalsIgnoreCase(format)) {
			return Parser.coverXml2Object(rsp, GetShippingOrderInfoRsp.class);
		}
		return Parser.convertJson2Object(rsp, GetShippingOrderInfoRsp.class);

    }

}
