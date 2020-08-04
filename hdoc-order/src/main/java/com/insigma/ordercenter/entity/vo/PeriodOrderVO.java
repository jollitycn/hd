package com.insigma.ordercenter.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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

import java.time.LocalDateTime;

/**
 * @author Jason
 * @description 预约订单列表信息
 * @date Create in 2020/7/9
 */
@Data
@ApiModel(value = "预约订单信息返回对象")
@EqualsAndHashCode(callSuper = true)
public class PeriodOrderVO extends BaseVO{

    @ApiModelProperty(value = "预约订单ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long periodOrderId;

    @ApiModelProperty(value = "订单编号")
    private String periodOrderNo;

    @ApiModelProperty(value = "订单类型（0：预售，1：月度，2：季度，年度）")
    private Integer periodOrderType;

    @ApiModelProperty(value = "订单状态（0：待审核，1：待出库，2：打单出库，3：已完成，4：冻结）")
    private Integer orderStatus;

    @ApiModelProperty(value = "下单时间")
    @JsonFormat(pattern = Constant.Sys.LOCALDATETIME_FORMATTER, timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "店铺ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long shopId;

    @ApiModelProperty(value = "收件人姓名")
    private String consumerName;

    @ApiModelProperty(value = "联系方式")
    private String mobilePhone;

    @ApiModelProperty(value = "完成次数")
    private Integer finishCount;

    @ApiModelProperty(value = "是否停用（0：未停用，1：已停用）")
    private Integer isStop;

    @ApiModelProperty(value = "是否删除（0：未删除，1：已删除）")
    private Integer isDeleted;

    @ApiModelProperty(value = "发货规则")
    private String rule;

    @ApiModelProperty(value = "总次数")
    private Integer totalCount;

    @ApiModelProperty(value = "下次计划发货时间")
    @JsonFormat(pattern = Constant.Sys.LOCALDATETIME_FORMATTER, timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime nextSendTime;

}
