package com.insigma.ordercenter.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 仓库发货分类
 * </p>
 *
 * @author Youwk
 * @since 2020-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_warehouse_type")
@ApiModel(value = "WarehouseType对象", description = "仓库发货分类")
public class WarehouseType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "仓库ID ")
    private Integer warehouseId;

    @ApiModelProperty(value = "商品类型")
    private Integer productType;

    public static final String WAREHOUSE_ID = "warehouse_id";

    public static final String PRODUCT_TYPE = "product_type";

}
