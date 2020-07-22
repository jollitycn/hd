package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: hdoc-parent
 * @description: 添加商品组合请求参数封装类
 * @author: XuChao
 * @create: 2020-07-22 17:15
 **/
@Data
public class AddComboDTO {

    @ApiModelProperty(value = "主商品ID")
    private Long productId;

    @ApiModelProperty(value = "子商品id集合（多个id，分隔）")
    private String productIds;

    @ApiModelProperty(value = "数量")
    private Integer quantity;


}
