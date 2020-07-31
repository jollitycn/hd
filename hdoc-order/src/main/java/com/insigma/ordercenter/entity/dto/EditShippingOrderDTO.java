package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: hdoc-parent
 * @description: 编辑补货单参数封装类
 * @author: XuChao
 * @create: 2020-07-21 17:10
 **/
@Data
public class EditShippingOrderDTO {

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "状态（0：待出库，1：待取货，2：已发货，3：冻结，4：冻结）")
    private Integer status;

    @ApiModelProperty(value = "收货人姓名")
    private String receiveName;

    @ApiModelProperty(value = "收货人备注")
    private String receiveRemark;

    @ApiModelProperty(value = "联系方式")
    private String mobilePhone;

    @ApiModelProperty(value = "收货人地址")
    private String address;

    @ApiModelProperty(value = "仓库id")
    private Long warehouseId;

    @ApiModelProperty(value = "物流公司id")
    private Long expressCompanyId;

    @ApiModelProperty(value = "更改原因")
    private String changeReason;

}
