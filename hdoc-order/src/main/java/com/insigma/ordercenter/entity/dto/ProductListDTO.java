package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Jason
 * @description：商品列表获取DTO
 * @date Create in 2020/7/8
 */
@Data
@ApiModel(value = "商品列表获取DTO")
public class ProductListDTO {

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

    @ApiModelProperty(value = "商品sku")
    private String productSku;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品编码")
    private String productNo;

    @ApiModelProperty(value = "是否上架（0：未上架，1：已上架）")
    private Integer isPutOn;

    @ApiModelProperty(value = "是否是组合商品（0：单品，1：组合）")
    private Integer isCombo;

    @ApiModelProperty(value = "商品品牌")
    private String brand;

}
