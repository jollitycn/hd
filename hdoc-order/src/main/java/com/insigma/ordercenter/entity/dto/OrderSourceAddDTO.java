package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author liuhao
 * @program hdoc-parent
 * @description：新增货主
 * @date Create in 2020/7/17
 */
@Data
@ApiModel(value = "新增货主DTO")
public class OrderSourceAddDTO {

    @ApiModelProperty(value = "货主编号")
    private String sourceNo;

    @ApiModelProperty(value = "货主名称")
    private String sourceName;

    @ApiModelProperty(value = "说明")
    private String remark;

}
