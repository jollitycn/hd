package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: hdoc-parent
 * @description: 添加退货单请求参数封装类
 * @author: XuChao
 * @create: 2020-07-30 16:26
 **/
@Data
public class AddRefundDTO {

    @ApiModelProperty(value = "退货单ID")
    private Long refundId;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "商品id集合")
    private List<Long> productIdList;

    @ApiModelProperty(value = "仓库id")
    private Long warehouseId;

    @ApiModelProperty(value = "退换理由")
    private String reason;

    @ApiModelProperty(value = "是否入库 1是 0否")
    private Integer status;
}
