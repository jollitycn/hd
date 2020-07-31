package com.insigma.ordercenter.entity.vo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author Jason
 * @description 预约订单收发人信息
 * @date Create in 2020/7/9
 */
@Data
@ApiModel(value = "预约订单收发人信息")
@EqualsAndHashCode(callSuper = true)
public class PeriodSendReceiveInfoVO extends BaseVO{

    @ApiModelProperty(value = "收发人信息ID")
    private Long periodSendReceiveInfoId;

    @ApiModelProperty(value = "订单ID ")
    private Long periodOrderId;

    @ApiModelProperty(value = "发货人地址")
    private String sendAddress;

    @ApiModelProperty(value = "发货人姓名")
    private String sendName;

    @ApiModelProperty(value = "支付类型")
    private Integer payType;

    @ApiModelProperty(value = "发货人备注")
    private String sendRemark;

    @ApiModelProperty(value = "下单时间")
    private LocalDateTime orderTime;

    @ApiModelProperty(value = "收货人姓名")
    private String receiveName;

    @ApiModelProperty(value = "联系方式")
    private String mobilePhone;

    @ApiModelProperty(value = "要求收货时间")
    private LocalDateTime requestTime;

    @ApiModelProperty(value = "收货人地址")
    private String address;

    @ApiModelProperty(value = "收货人备注")
    private String receiveRemark;

}
