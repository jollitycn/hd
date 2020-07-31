package com.insigma.ordercenter.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: hdoc-parent
 * @description: 发货单明细返回封装类
 * @author: XuChao
 * @create: 2020-07-30 10:14
 **/
@Data
public class ShippingOrderDetailVO {

    @ApiModelProperty(value = "订单ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderId;

    @ApiModelProperty(value = "原始订单号")
    private String orderNo;

    @ApiModelProperty(value = "来源")
    private String source;

    @ApiModelProperty(value = "收货人姓名")
    private String receiveName;

    @ApiModelProperty(value = "联系方式")
    private String mobilePhone;

    @ApiModelProperty(value = "邮编")
    private String postcode;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "状态（0：待出库，1：待取货，2：已发货，3：冻结，4：取消）")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "发货单商品列表")
    private List<ShippingProductVO> productList;
}
