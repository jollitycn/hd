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
 * 仓库负责区域表
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_wharehouse_region")
@ApiModel(value = "WharehouseRegion对象", description = "仓库负责区域表")
public class WharehouseRegion implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "仓库负责区域ID")
    @TableId(value = "wharehouse_region_id", type = IdType.ID_WORKER)
    private Long wharehouseRegionId;

    @ApiModelProperty(value = "仓库ID ")
    private Long wharehouseId;

    @ApiModelProperty(value = "地区ID")
    private Long regionId;


}
