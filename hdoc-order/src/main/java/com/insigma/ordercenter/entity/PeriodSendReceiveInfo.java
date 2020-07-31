package com.insigma.ordercenter.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author AH
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_period_send_receive_info")
@ApiModel(value = "PeriodSendReceiveInfo对象", description = "")
public class PeriodSendReceiveInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "收发人信息ID")
    @TableId(value = "period_send_receive_info_id", type = IdType.ID_WORKER)
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


    public static final String PERIOD_SEND_RECEIVE_INFO_ID = "period_send_receive_info_id";

    public static final String PERIOD_ORDER_ID = "period_order_id";

    public static final String SEND_ADDRESS = "send_address";

    public static final String SEND_NAME = "send_name";

    public static final String PAY_TYPE = "pay_type";

    public static final String SEND_REMARK = "send_remark";

    public static final String ORDER_TIME = "order_time";

    public static final String RECEIVE_NAME = "receive_name";

    public static final String MOBILE_PHONE = "mobile_phone";

    public static final String REQUEST_TIME = "request_time";

    public static final String ADDRESS = "address";

    public static final String RECEIVE_REMARK = "receive_remark";

}
