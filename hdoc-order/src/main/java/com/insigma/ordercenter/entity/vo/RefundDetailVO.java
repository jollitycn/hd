package com.insigma.ordercenter.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.insigma.ordercenter.constant.Constant;
import com.insigma.ordercenter.entity.OrderDetail;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @program: hdoc-parent
 * @description: 退货单详情返回封装类
 * @author: XuChao
 * @create: 2020-07-30 16:30
 **/
@Data
public class RefundDetailVO {

    @ApiModelProperty(value = "退货单ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long refundId;

    @ApiModelProperty(value = "审核人ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long auditId;

    @ApiModelProperty(value = "订单ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderId;

    @ApiModelProperty(value = "审核人名称")
    private String auditName;

    @ApiModelProperty(value = "退货仓库")
    private String warehouseName;

    @ApiModelProperty(value = "退换理由")
    private String reason;

    @ApiModelProperty(value = "退货处理日期")
    @JsonFormat(pattern = Constant.Sys.LOCALDATETIME_FORMATTER, timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "是否入库 1是 0否")
    private Integer status;

    @ApiModelProperty(value = "商品列表")
    private List<OrderDetail> productList;

}
