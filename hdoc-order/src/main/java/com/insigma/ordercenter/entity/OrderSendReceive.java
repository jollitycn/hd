package com.insigma.ordercenter.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.insigma.ordercenter.constant.Constant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 收发人信息
 * </p>
 *
 * @author AH
 * @since 2020-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_order_send_receive")
@ApiModel(value = "OrderSendReceive对象", description = "收发人信息")
public class OrderSendReceive implements Serializable {

    private static final long serialVersionUID = 1L;

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
    @JsonFormat(pattern = Constant.Sys.LOCALDATETIME_FORMATTER, timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
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

    @ApiModelProperty(value = "登录名")
    private String loginName;


    public static final String SEND_RECEIVE_INFO_ID = "send_receive_info_id";

    public static final String ORDER_ID = "order_id";

    public static final String SEND_NAME = "send_name";

    public static final String PAY_TYPE = "pay_type";

    public static final String SEND_REMARK = "send_remark";

    public static final String ORDER_TIME = "order_time";

    public static final String RECEIVE_NAME = "receive_name";

    public static final String MOBILE_PHONE = "mobile_phone";

    public static final String REQUEST_TIME = "request_time";

    public static final String ADDRESS = "address";

    public static final String RECEIVE_REMARK = "receive_remark";

    public static final String ORDER_REASON = "order_reason";

    public static final String LOCATION_CITY = "location_city";

    public static final String PROVINCE = "province";

}
