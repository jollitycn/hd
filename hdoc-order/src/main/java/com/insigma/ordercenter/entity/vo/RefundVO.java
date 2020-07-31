package com.insigma.ordercenter.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: hdoc-parent
 * @description: 退货单列表返回封装类
 * @author: XuChao
 * @create: 2020-07-30 15:25
 **/
@Data
public class RefundVO {

    @ApiModelProperty(value = "退货单ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long refundId;

    @ApiModelProperty(value = "订单ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderId;

    @ApiModelProperty(value = "退货单编号")
    private String refundNo;

    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    @ApiModelProperty(value = "退换货状态（0：未完成，1：已完成）")
    private Integer refundStatus;

    @ApiModelProperty(value = "发起时间")
    private String createTime;

    @ApiModelProperty(value = "订单状态（0：待审核，1：待出库，2：打单出库，3：已完成，4：冻结）")
    private Integer orderStatus;

    @ApiModelProperty(value = "退单仓库")
    private String warehouseName;

    @ApiModelProperty(value = "承运商")
    private String companyName;

}
