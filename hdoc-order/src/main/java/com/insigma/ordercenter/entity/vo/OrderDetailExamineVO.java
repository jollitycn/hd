package com.insigma.ordercenter.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(value = "订单详情及仓库列表")
@EqualsAndHashCode(callSuper = true)
public class OrderDetailExamineVO extends BaseVO{

    @ApiModelProperty(value = "订单明细ID")
    private Long orderDetailId;

    @ApiModelProperty(value = "订单ID ")
    private Long orderId;

    @ApiModelProperty(value = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "商品sku")
    private String productSku;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "商品规格")
    private String productSpecs;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "数量")
    private Integer amount;

    @ApiModelProperty(value = "仓库列表")
    private List<WarehouseExamineVO> warehouseExamineVOS;

}
