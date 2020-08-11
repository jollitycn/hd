package com.insigma.ordercenter.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.insigma.ordercenter.constant.Constant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author AH
 * @program hdoc-parent
 * @description：订单列表
 * @date Create in 2020/7/16
 */
@Data
@ApiModel(value = "订单列表")
@EqualsAndHashCode(callSuper = true)
public class OrderListVO extends BaseVO {

    @ApiModelProperty(value = "订单ID ")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderId;

    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    @ApiModelProperty(value = "订单状态（0：待审核，1：待出库，2：打单出库，3：已完成，4：冻结）")
    private Integer orderStatus;

    @ApiModelProperty(value = "订单来源")
    private String platformName;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "城市")
    private String locationCity;

    @ApiModelProperty(value = "省名称")
    private String province;

    @ApiModelProperty(value = "收货人备注")
    private String receiveRemark;

    @ApiModelProperty(value = "下单时间")
    @JsonFormat(pattern = Constant.Sys.LOCALDATETIME_FORMATTER, timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "审核时间")
    @JsonFormat(pattern = Constant.Sys.LOCALDATETIME_FORMATTER, timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime reviewTime;

    @ApiModelProperty(value = "最早发货时间")
    @JsonFormat(pattern = Constant.Sys.LOCALDATETIME_FORMATTER, timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime sendTime;

    @ApiModelProperty(value = "收件人姓名")
    private String consumerName;

    @ApiModelProperty(value = "联系方式")
    private String mobilePhone;

    @ApiModelProperty(value = "异常原因")
    private String errorReason;

    @ApiModelProperty(value = "是否是手动单（0：否，1：是）")
    private Integer isHandOrder;

    @ApiModelProperty(value = "合单")
    private Integer isCombined;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "店铺名称")
    private Long shopName;

    @ApiModelProperty(value = "店铺编码")
    private String platformNo;

    @ApiModelProperty(value = "联系方式")
    private String mobilePhoneOrder;

    @ApiModelProperty(value = "应收合计")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "拆单")
    private Integer splitOrder;

    @ApiModelProperty(value = "原单号")
    private String originOrderNo;

    @ApiModelProperty(value = "运费")
    private BigDecimal fee;

    @ApiModelProperty(value = "是否是预约订单（0：否，1：是）")
    private Integer isPeriod;

    @ApiModelProperty(value = "预约订单ID")
    private Long periodOrderId;

    @ApiModelProperty(value = "发货单列表")
    private List<ShippingOrderVO> shippingOrderVOS;


}
