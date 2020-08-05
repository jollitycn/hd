package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author AH
 * @program hdoc-parent
 * @description：发送MQ信息封装实体
 * @date Create in 2020/8/5
 */
@Data
@ApiModel(value = "发送MQ信息封装实体DTO")
public class MQMessageDTO {

    private Long orderId;

    private String orderNo;

    private Integer orderStatus;

    private Long shopId;

    private Long originOrderId;

    private Long shippingOrderId;

    private Long warehouseId;

    private Long expressCompanyId;

    private String expressNo;

    private Integer status;

}