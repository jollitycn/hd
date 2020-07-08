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
@TableName("t_wharehouse_manager")
@ApiModel(value = "WharehouseManager对象", description = "仓库管理员表")
public class WharehouseManager implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "仓库管理员表ID")
    @TableId(value = "wharehouse_manager_id", type = IdType.ID_WORKER)
    private Long wharehouseManagerId;

    @ApiModelProperty(value = "仓库ID ")
    private Long wharehouseId;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "用户名称")
    private String userName;


}
