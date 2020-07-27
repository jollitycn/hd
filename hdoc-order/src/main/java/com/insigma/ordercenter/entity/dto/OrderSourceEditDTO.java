package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Jason
 * @program hdoc-parent
 * @description：编辑货主
 * @date Create in 2020/7/17
 */
@Data
@ApiModel(value = "编辑货主DTO")
public class OrderSourceEditDTO {

    @ApiModelProperty(value = "货主ID")
    private Long orderSourceId;

    @ApiModelProperty(value = "货主编号")
    private String sourceNo;

    @ApiModelProperty(value = "货主名称")
    private String sourceName;

    @ApiModelProperty(value = "说明")
    private String remark;
}
