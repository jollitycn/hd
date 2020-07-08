package com.insigma.ordercenter.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 仓库表
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_wharehouse")
@ApiModel(value = "Wharehouse对象", description = "仓库表")
public class Wharehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "仓库ID ")
    @TableId(value = "wharehouse_id", type = IdType.ID_WORKER)
    private Long wharehouseId;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "区县")
    private String district;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "区域ID")
    private String regionId;

    @ApiModelProperty(value = "纬度")
    private Double latitude;

    @ApiModelProperty(value = "经度")
    private Double longitude;

    @ApiModelProperty(value = "仓库编码")
    private String wharehouseNo;

    @ApiModelProperty(value = "仓库名称")
    private String wharehouseName;

    @ApiModelProperty(value = "创建人id")
    private Long createId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改人id")
    private Long modifyId;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifyTime;

    @ApiModelProperty(value = "是否删除（0：未删除，1：已删除）")
    private Integer isDeleted;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "联系电话")
    private String mobilePhone;


}
