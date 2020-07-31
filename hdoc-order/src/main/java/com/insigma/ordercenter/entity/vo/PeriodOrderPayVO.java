package com.insigma.ordercenter.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Jason
 * @description 预约订单支付列表信息
 * @date Create in 2020/7/9
 */
@Data
@ApiModel(value = "预约订单支付列表信息返回对象")
@EqualsAndHashCode(callSuper = true)
public class PeriodOrderPayVO extends BaseVO{

    @ApiModelProperty(value = "订单支付id")
    private Long periodOrderPayId;

    @ApiModelProperty(value = "支付类型")
    private String periodPayType;

    @ApiModelProperty(value = "支付金额")
    private BigDecimal periodPayMoney;

    @ApiModelProperty(value = "支付时间")
    private LocalDateTime periodPayDatetime;

    @ApiModelProperty(value = "支付卡号")
    private String periodPayCardNo;

    @ApiModelProperty(value = "备用（微信支付昵称）")
    private String periodPayBack;

}
