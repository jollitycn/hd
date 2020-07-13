package com.insigma.ordercenter.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 仓库商品关联表
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_warehouse_product_relation")
@ApiModel(value = "WarehouseProductRelation对象", description = "仓库商品关联表")
public class WarehouseProductRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "库存表ID")
    @TableId(value = "warehouse_product_relation_id", type = IdType.AUTO)
    private Integer warehouseProductRelationId;

    @ApiModelProperty(value = "仓库ID ")
    private Integer warehouseId;

    @ApiModelProperty(value = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "库存数量")
    private Integer quantity;

    @ApiModelProperty(value = "优先级")
    private Integer priority;

    @ApiModelProperty(value = "商品sku")
    private String productSku;


}
