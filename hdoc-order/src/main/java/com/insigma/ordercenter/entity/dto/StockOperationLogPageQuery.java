package com.insigma.ordercenter.entity.dto;

import com.insigma.ordercenter.entity.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class StockOperationLogPageQuery extends BaseVO {

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

    @ApiModelProperty(value = "仓库编码")
    private String warehouseId;

    @ApiModelProperty(value = "类型")
    private String type;
    @ApiModelProperty(value = "开始区间")
    private String startValue;
    @ApiModelProperty(value = "结束区间")
    private String endValue;
    @ApiModelProperty(value = "开始时间")
    private String startTime;
    @ApiModelProperty(value = "结束时间")
    private String endTime;
    @ApiModelProperty(value = "商品")
    private String productId;

}
