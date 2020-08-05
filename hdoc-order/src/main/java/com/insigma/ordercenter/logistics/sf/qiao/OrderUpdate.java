package com.insigma.ordercenter.logistics.sf.qiao;

import com.insigma.ordercenter.entity.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderUpdate extends BaseVO {

    @ApiModelProperty(value = "客户订单号")
private String orderId;
    @ApiModelProperty(value = "客户订单操作标识")
private int dealType;
    @ApiModelProperty(value = "如dealtype=1， 必填)",required = true)
private java.util.List<WaybillNoInfo> waybillNoInfoList;
    @ApiModelProperty(value = "报关批次")
private String customsBatchs ;
    @ApiModelProperty(value = "collectEmpCode")
private String collectEmpCode;
    @ApiModelProperty(value = "inProcessWaybillNo")
private String inProcessWaybillNo;
    @ApiModelProperty(value = "sourceZoneCode")
private String sourceZoneCode;
    @ApiModelProperty(value = "destZoneCode")
private String destZoneCode;
    @ApiModelProperty(value = "totalWeight")
private double totalWeight;
}
