package com.insigma.ordercenter.entity.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.insigma.ordercenter.constant.Constant;
import com.insigma.ordercenter.entity.OrderDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel(value = "收发人信息返回封装实体")
@EqualsAndHashCode(callSuper = true)
public class SendReceiveInfoVO extends BaseVO{

    /**
     * 订单相关字段
     */
    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    @ApiModelProperty(value = "0：新建保存，1：编辑/提交保存")
    private Integer saveStatus;

    @ApiModelProperty(value = "订单状态（0：待审核，1：待出库，2：打单出库，3：已完成，4：冻结）")
    private Integer orderStatus;

    @ApiModelProperty(value = "下单时间")
    @JsonFormat(pattern = Constant.Sys.LOCALDATETIME_FORMATTER, timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "收件人姓名")
    private String consumerName;

    @ApiModelProperty(value = "应收合计")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "是否为拆合单（0：否，1：拆单，2：合单）")
    private Integer isCombined;

    @ApiModelProperty(value = "原单号")
    private String originOrderId;

    @ApiModelProperty(value = "收件人联系方式")
    private String mobilePhoneOrder;

    @ApiModelProperty(value = "运费")
    private BigDecimal fee;

    @ApiModelProperty(value = "是否异常（0：否，1：是）")
    private Integer isError;

    @ApiModelProperty(value = "异常原因")
    private String errorReason;

    @ApiModelProperty(value = "是否是预约订单（0：否，1：是）")
    private Integer isPeriod;

    @ApiModelProperty(value = "预约订单ID")
    private Long periodOrderId;

    @ApiModelProperty(value = "审核时间")
    @JsonFormat(pattern = Constant.Sys.LOCALDATETIME_FORMATTER, timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime reviewTime;

    @ApiModelProperty(value = "是否是手动单（0：否，1：是）")
    private Integer isHandOrder;

    /**
     * 收发件人相关字段
     */

    @ApiModelProperty(value = "收发人信息ID")
    @JsonSerialize(using = ToStringSerializer.class)
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
    @JsonFormat(pattern = Constant.Sys.LOCALDATETIME_FORMATTER, timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime orderTime;

    @ApiModelProperty(value = "收货人姓名")
    private String receiveName;

    @ApiModelProperty(value = "发货人联系方式")
    private String mobilePhone;

    @ApiModelProperty(value = "要求收货时间")
    @JsonFormat(pattern = Constant.Sys.LOCALDATETIME_FORMATTER, timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
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

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "登录名")
    private String loginName;

    @ApiModelProperty(value = "邮政编码")
    private String postalCode;

    @ApiModelProperty(value = "订单商品列表")
    private List<OrderDetail> orderDetails;

}
