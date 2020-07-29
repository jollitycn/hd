package com.insigma.ordercenter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 策略关联商品分类表
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_product_type_strategy")
@ApiModel(value = "ProductTypeStrategy对象", description = "策略关联商品分类表")
public class ProductTypeStrategy implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "策略商品分类id")
    @TableId(value = "strategy_product_type_id", type = IdType.ID_WORKER)
    private Long strategyProductTypeId;

    @ApiModelProperty(value = "策略id")
    private Long strategyId;

    @ApiModelProperty(value = "商品分类id（对应字典表的商品分类）")
    private Long productTypeId;


    public static final String STRATEGY_PRODUCT_TYPE_ID = "strategy_product_type_id";

    public static final String STRATEGY_ID = "strategy_id";

    public static final String PRODUCT_TYPE_ID = "product_type_id";

}
