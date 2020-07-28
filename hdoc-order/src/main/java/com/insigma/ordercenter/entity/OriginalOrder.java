package com.insigma.ordercenter.entity;

import java.math.BigDecimal;

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
 * 原始订单表
 * </p>
 *
 * @author AH
 * @since 2020-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_original_order")
@ApiModel(value = "OriginalOrder对象", description = "原始订单表")
public class OriginalOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "原始订单ID ")
    @TableId(value = "original_order_id", type = IdType.ID_WORKER)
    private Long originalOrderId;

    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    @ApiModelProperty(value = "下单时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "收件人姓名")
    private String consumerName;

    @ApiModelProperty(value = "联系方式")
    private String mobilePhone;

    @ApiModelProperty(value = "应收合计")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "收货人地址")
    private String address;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "订单ID ")
    private Long orderId;


    public static final String ORIGINAL_ORDER_ID = "original_order_id";

    public static final String ORDER_NO = "order_no";

    public static final String CREATE_TIME = "create_time";

    public static final String SHOP_ID = "shop_id";

    public static final String CONSUMER_NAME = "consumer_name";

    public static final String MOBILE_PHONE = "mobile_phone";

    public static final String TOTAL_PRICE = "total_price";

    public static final String ADDRESS = "address";

    public static final String REMARK = "remark";

    public static final String ORDER_ID = "order_id";

}
