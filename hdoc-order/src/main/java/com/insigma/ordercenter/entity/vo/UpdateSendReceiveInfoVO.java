package com.insigma.ordercenter.entity.vo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "修改收发人信息封装实体")
@EqualsAndHashCode(callSuper = true)
public class UpdateSendReceiveInfoVO extends BaseVO{

    @ApiModelProperty(value = "收发人信息ID")
    @TableId(value = "send_receive_info_id", type = IdType.ID_WORKER)
    private Long sendReceiveInfoId;

    @ApiModelProperty(value = "订单ID ")
    private Long orderId;

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

    @ApiModelProperty(value = "建单原因")
    private String orderReason;

    @ApiModelProperty(value = "所在城市")
    private String locationCity;

    @ApiModelProperty(value = "所在省份")
    private String province;

}
