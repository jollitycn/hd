package com.insigma.ordercenter.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: hdoc-parent
 * @description: 商品组合列表返回类
 * @author: XuChao
 * @create: 2020-07-29 14:27
 **/
@Data
public class ProductComboVO {

    @ApiModelProperty(value = "组合信息ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long productComboId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品编号")
    private String productNo;

    @ApiModelProperty(value = "商品规格")
    private String productSpecs;

    @ApiModelProperty(value = "商品规格")
    private Integer quantity;

}
