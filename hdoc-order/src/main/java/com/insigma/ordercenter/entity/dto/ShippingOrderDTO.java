package com.insigma.ordercenter.entity.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @program: hdoc-parent
 * @description: 发货单列表请求参数封装类
 * @author: XuChao
 * @create: 2020-07-21 10:45
 **/
@Data
public class ShippingOrderDTO {

    @ApiModelProperty(value = "当前页", name = "pageNum", required = true, example = "1")
    @NotNull(message = "当前页不能为空")
    @Min(value = 1L, message = "查询页码最小为1")
    @Max(value = Integer.MAX_VALUE, message = "查询页码超出最大限制")
    protected Integer pageNum;

    @ApiModelProperty(value = "每页大小", name = "pageSize", required = true, example = "10")
    @NotNull(message = "每页大小不能为空")
    @Min(value = 1L, message = "每页最少查询一条数据")
    @Max(value = 100L, message = "查询数量超出限制")
    protected Integer pageSize;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "发货单号")
    private String shippingNo;

    @ApiModelProperty(value = "物流单号")
    private String expressNo;

    @ApiModelProperty(value = "承运商ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long expressCompanyId;

    @ApiModelProperty(value = "仓库ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long warehouseId;

    @ApiModelProperty(value = "状态（0：待出库，1：待取货，2：已发货，3：冻结，4：冻结）")
    private Integer status;

}
