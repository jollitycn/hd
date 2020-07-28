package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author liuhao
 * @program hdoc-parent
 * @description：订单列表dto
 * @date Create in 2020/7/16
 */
@Data
@ApiModel(value = "订单列表dto")
public class OrderDTO {

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


    @ApiModelProperty(value = "订单ID ")
    private Long orderId;

    @ApiModelProperty(value = "订单状态（0：待审核，1：待出库，2：打单出库，3：已完成，4：冻结）")
    private Integer orderStatus;

    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    @ApiModelProperty(value = "发货单ID")
    private Long shippingOrderId;

    @ApiModelProperty(value = "审核开始时间")
    private LocalDateTime reviewTimeStart;

    @ApiModelProperty(value = "审核结束时间")
    private LocalDateTime reviewTimeEnd;

    @ApiModelProperty(value = "下单开始时间")
    private LocalDateTime createTimeStart;

    @ApiModelProperty(value = "下单结束时间")
    private LocalDateTime createTimeEnd;

    @ApiModelProperty(value = "发货开始时间")
    private LocalDateTime sendTimeStart;

    @ApiModelProperty(value = "发货结束时间")
    private LocalDateTime sendTimeEnd;

    @ApiModelProperty(value = "收货人姓名")
    private String receiveName;

    @ApiModelProperty(value = "联系方式")
    private String mobilePhone;

    @ApiModelProperty(value = "拆单")
    private Integer isCombined;

    @ApiModelProperty(value = "合单")
    private Integer combinedOrder;


}