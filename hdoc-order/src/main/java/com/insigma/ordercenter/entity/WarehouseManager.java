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
 * 仓库管理员表
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_warehouse_manager")
@ApiModel(value = "WarehouseManager对象", description = "仓库管理员表")
public class WarehouseManager implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "仓库管理员表ID")
    @TableId(value = "warehouse_manager_id", type = IdType.AUTO)
    private Integer warehouseManagerId;

    @ApiModelProperty(value = "仓库ID ")
    private Integer warehouseId;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "用户名称")
    private String userName;


}
