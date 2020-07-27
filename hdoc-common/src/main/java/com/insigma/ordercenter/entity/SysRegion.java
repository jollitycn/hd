package com.insigma.ordercenter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 系统地区表
 * </p>
 *
 * @author Jason
 * @since 2020-01-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysRegion对象", description="系统地区表")
public class SysRegion implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "region_id", type = IdType.ID_WORKER)
    @ApiModelProperty(value = "地区ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long regionId;

    @ApiModelProperty(value = "父ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long parentId;

    @ApiModelProperty(value = "简称")
    private String shortName;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "全称")
    private String mergerName;

    @ApiModelProperty(value = "层级（1：省，2：市，3：区县）")
    private Integer level;

    @ApiModelProperty(value = "拼音")
    private String pinyin;

    @ApiModelProperty(value = "长途区号")
    private String code;

    @ApiModelProperty(value = "邮编")
    private String zipCode;


}
