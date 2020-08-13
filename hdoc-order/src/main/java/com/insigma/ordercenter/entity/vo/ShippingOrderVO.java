package com.insigma.ordercenter.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @program: hdoc-parent
 * @description: 发货单列表返回封装类
 * @author: XuChao
 * @create: 2020-07-21 10:50
 **/
@Data
public class ShippingOrderVO {

    @ApiModelProperty(value = "发货单ID ")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long shippingOrderId;

    @ApiModelProperty(value = "发货单号 ")
    private String shippingOrderNo;

    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;

    @ApiModelProperty(value = "状态（0：待出库，1：待取货，2：已发货，3：冻结，4：取消 5：拒收 6:异常 7：已完成）")
    private Integer status;

    @ApiModelProperty(value = "物流公司(承运商)")
    private String companyName;

    @ApiModelProperty(value = "分配日期")
    private String createTime;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "下单日期")
    private String orderNoCreateTime;

    @ApiModelProperty(value = "物流单号")
    private String expressNo;

    @ApiModelProperty(value = "运费")
    private String freight;

    @ApiModelProperty(value = "异常原因")
    private String exceptionReason;
    private Long createId;

    private Long warehouseId;

    private Long expressCompanyId;

    private Long modifyId;

    private LocalDateTime modifyTime;

    private Integer isDeleted;

    private String receiveName;

    private String mobilePhone;

    private LocalDateTime requestTime;

    private String address;

    private String receiveRemark;

    private Integer isCombined;

    private Integer isRefuse;

    private LocalDateTime sendTime;

    private float expressFee;

    private Long orderDetailId;

    private String userName;

    private List<OrderDetailVO> orderDetailVOS;

}
