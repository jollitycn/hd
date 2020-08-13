package com.insigma.ordercenter.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
 * @since 2020-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_order_pay_relation")
@ApiModel(value = "OrderPayRelation对象", description = "")
public class OrderPayRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "支付id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderPayId;

    @ApiModelProperty(value = "支付卡号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long cardNo;


}
