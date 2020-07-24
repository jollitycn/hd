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
 * @description：承运商信息列表vo
 * @date Create in 2020/7/17
 */
@Data
@ApiModel(value = "承运商信息列表vo")
@EqualsAndHashCode(callSuper = true)
public class ExpressCompanyListVO extends BaseVO {

    @ApiModelProperty(value = "物流公司ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long expressCompanyId;

    @ApiModelProperty(value = "物流公司名称")
    private String companyName;

    @ApiModelProperty(value = "物流公司编码")
    private String companyNo;

    @ApiModelProperty(value = "创建时间")
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "说明")
    private String remark;

    @ApiModelProperty(value = "是否停用（0：未停用，1：已停用）")
    private Integer isStop;

    @ApiModelProperty(value = "创建人ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createId;

    @ApiModelProperty("创建人姓名")
    private String createName;
}