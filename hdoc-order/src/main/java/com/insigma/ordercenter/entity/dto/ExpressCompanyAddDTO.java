package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Jason
 * @program hdoc-parent
 * @description：新增承运商
 * @date Create in 2020/7/17
 */
@Data
@ApiModel(value = "新增承运商DTO")
public class ExpressCompanyAddDTO {

    @ApiModelProperty(value = "承运商名称")
    private String companyName;

    @ApiModelProperty(value = "承运商编码")
    private String companyNo;

    @ApiModelProperty(value = "说明")
    private String remark;

}
