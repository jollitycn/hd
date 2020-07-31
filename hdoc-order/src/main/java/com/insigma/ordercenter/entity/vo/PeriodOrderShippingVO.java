package com.insigma.ordercenter.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Jason
 * @description 预约订单发货历史信息
 * @date Create in 2020/7/29
 */
@Data
@ApiModel(value = "预约订单发货历史信息返回对象")
@EqualsAndHashCode(callSuper = true)
public class PeriodOrderShippingVO extends BaseVO{

    @ApiModelProperty(value = "订单ID ")
    @TableId(value = "order_id", type = IdType.ID_WORKER)
    private Long orderId;

    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    @ApiModelProperty(value = "订单状态（0：待审核，1：待出库，2：打单出库，3：已完成，4：冻结）")
    private Integer orderStatus;

    @ApiModelProperty(value = "下单时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "发货单列表")
    private List<PeriodShippingListVO> periodShippingListVOS;
}
