package com.insigma.ordercenter.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author AH
 * @program hdoc-parent
 * @description：订单支付信息
 * @date Create in 2020/7/27
 */
@Data
@ApiModel(value = "订单支付信息")
@EqualsAndHashCode(callSuper = true)
public class OrderpayVO extends BaseVO{


    @ApiModelProperty(value = "订单支付id")
    private Long orderPayId;

    @ApiModelProperty(value = "支付类型")
    private String payType;

    @ApiModelProperty(value = "支付金额")
    private BigDecimal payMoney;

    @ApiModelProperty(value = "支付时间")
    private LocalDateTime payDatetime;

    @ApiModelProperty(value = "支付卡号")
    private String payCardNo;

    @ApiModelProperty(value = "备用（微信支付昵称）")
    private String payBack;

}
