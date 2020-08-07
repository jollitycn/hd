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
 *
 * </p>
 *
 * @author Jason
 * @since 2020-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_shop_warehouse")
@ApiModel(value = "ShopWarehouse对象", description = "")
public class ShopWarehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "shop_warehouse_id", type = IdType.ID_WORKER)
    private Long shopWarehouseId;

    private Long shopId;

    private Integer warehouseId;


    public static final String SHOP_WAREHOUSE_ID = "shop_warehouse_id";

    public static final String SHOP_ID = "shop_id";

    public static final String WAREHOUSE_ID = "warehouse_id";

}
