package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author liuhao
 * @program hdoc-parent
 * @description：承运商列表DTO
 * @date Create in 2020/7/17
 */
@Data
@ApiModel(value = "承运商列表DTO")
public class ExpressCompanyListDTO {

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", name = "pageNum", required = true, example = "1")
    @NotNull(message = "当前页不能为空")
    @Min(value = 1L, message = "查询页码最小为1")
    @Max(value = Integer.MAX_VALUE, message = "查询页码超出最大限制")
    protected Integer pageNum;

    /**
     * 每页大小
     */
    @ApiModelProperty(value = "每页大小", name = "pageSize", required = true, example = "10")
    @NotNull(message = "每页大小不能为空")
    @Min(value = 1L, message = "每页最少查询一条数据")
    @Max(value = 100L, message = "查询数量超出限制")
    protected Integer pageSize;

    @ApiModelProperty(value = "承运商名称")
    private String companyName;

    @ApiModelProperty(value = "承运商编码")
    private String companyNo;
}
