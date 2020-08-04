package com.insigma.ordercenter.entity.dto;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author AH
 * @program hdoc-parent
 * @description：预约订单状态修改dto
 * @date Create in 2020/7/28
 */
@Data
@ApiModel(value = "预约订单状态修改dto")
public class PeriodOrderStatuDTO {

    @ApiModelProperty(value = "预约订单ID")
    @TableId(value = "period_order_id", type = IdType.ID_WORKER)
    private Long periodOrderId;

    @ApiModelProperty(value = "订单状态（0：待审核，1：待出库，2：打单出库，3：已完成，4：冻结）")
    private Integer orderStatus;

}
