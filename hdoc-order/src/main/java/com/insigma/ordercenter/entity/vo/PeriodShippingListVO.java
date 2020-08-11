package com.insigma.ordercenter.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Jason
 * @description 预约订单发货历史信息
 * @date Create in 2020/7/29
 */
@Data
@ApiModel(value = "预约订单发货历史信息返回对象")
@EqualsAndHashCode(callSuper = true)
public class PeriodShippingListVO extends BaseVO{


    @ApiModelProperty(value = "发货单ID")
    private Long shippingOrderId;

    @ApiModelProperty(value = "仓库ID ")
    private Long warehouseId;

    @ApiModelProperty(value = "物流公司ID")
    private Long expressCompanyId;

    @ApiModelProperty(value = "物流单号")
    private String expressNo;

    @ApiModelProperty(value = "是否删除（0：未删除，1：已删除）")
    private Integer isDeleted;

    @ApiModelProperty(value = "状态（0：待出库，1：待取货，2：已发货，3：冻结，4：冻结）")
    private Integer status;

    @ApiModelProperty(value = "发货单编号")
    private String shippingOrderNo;

    @ApiModelProperty(value = "发货时间")
    private String sendTime;


}
