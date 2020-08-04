package com.insigma.ordercenter.entity.dto;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author AH
 * @program hdoc-parent
 * @description：预约订单停止、启用状态修改dto
 * @date Create in 2020/7/28
 */
@Data
@ApiModel(value = "预约订单停止、启用状态修改dto")
public class PeriodStatuDTO {

    @ApiModelProperty(value = "预约订单ID")
    @TableId(value = "period_order_id", type = IdType.ID_WORKER)
    private Long periodOrderId;

    @ApiModelProperty(value = "是否停用（0、未停用  1、已停用）")
    private Integer isStop;

    @ApiModelProperty(value = "订单状态（0：进行中，1：已停止，2：已结束）")
    private Integer orderStatus;

}
