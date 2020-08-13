package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: hdoc-parent
 * @description: 编辑补货单参数封装类
 * @author: XuChao
 * @create: 2020-07-21 17:10
 **/
@Data
public class EditShippingOrderDTO {

    @ApiModelProperty(value = "发货单号")
    private Long shippingOrderId;

    @ApiModelProperty(value = "订单ID")
    private String orderId;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "状态（0：待出库，1：待取货，2：已发货，3：冻结，4：取消 5：拒收 6:异常 7：已完成 8:新建）")
    private Integer status;

    @ApiModelProperty(value = "收货人姓名")
    private String receiveName;

    @ApiModelProperty(value = "收货人备注")
    private String receiveRemark;

    @ApiModelProperty(value = "联系方式")
    private String mobilePhone;

    @ApiModelProperty(value = "收货人地址")
    private String address;

    @ApiModelProperty(value = "仓库id")
    private Integer warehouseId;

    @ApiModelProperty(value = "物流公司id")
    private Integer expressCompanyId;

    @ApiModelProperty(value = "更改原因")
    private String changeReason;

    @ApiModelProperty(value = "商品id列表")
    private List<Long> productList;
}
