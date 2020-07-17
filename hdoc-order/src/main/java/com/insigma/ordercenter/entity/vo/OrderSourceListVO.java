package com.insigma.ordercenter.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author liuhao
 * @program hdoc-parent
 * @description：货主信息列表
 * @date Create in 2020/7/16
 */
@Data
@ApiModel(value = "货主信息列表")
@EqualsAndHashCode(callSuper = true)
public class OrderSourceListVO extends BaseVO {

    @ApiModelProperty(value = "货主定义ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderSourceId;

    @ApiModelProperty(value = "货主编号")
    private String sourceNo;

    @ApiModelProperty(value = "货主名称")
    private String sourceName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "说明")
    private String remark;

    @ApiModelProperty(value = "是否停用（0：未停用，1：已停用）")
    private Integer isStop;

    @ApiModelProperty(value = "创建人ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createId;

    @ApiModelProperty(value = "创建人名称")
    private String createName;
}
