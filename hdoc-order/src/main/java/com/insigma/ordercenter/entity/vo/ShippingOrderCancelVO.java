package com.insigma.ordercenter.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel(value = "取消订单时，查询发货单信息列表")
@EqualsAndHashCode(callSuper = true)
public class ShippingOrderCancelVO extends BaseVO{

    @ApiModelProperty(value = "发货单ID")
    private Long shippingOrderId;

    @ApiModelProperty(value = "仓库ID ")
    private Long warehouseId;

    @ApiModelProperty(value = "物流公司ID")
    private Long expressCompanyId;

    @ApiModelProperty(value = "物流单号")
    private String expressNo;

    @ApiModelProperty(value = "创建人id")
    private Long createId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改人id")
    private Long modifyId;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifyTime;

    @ApiModelProperty(value = "是否删除（0：未删除，1：已删除）")
    private Integer isDeleted;

    @ApiModelProperty(value = "状态（0：待出库，1：待取货，2：已发货，3：冻结，4：取消 5：拒收 6:异常 7：已完成）")
    private Integer status;

    @ApiModelProperty(value = "收货人姓名")
    private String receiveName;

    @ApiModelProperty(value = "联系方式")
    private String mobilePhone;

    @ApiModelProperty(value = "要求收货时间")
    private LocalDateTime requestTime;

    @ApiModelProperty(value = "收货人地址")
    private String address;

    @ApiModelProperty(value = "收货人备注")
    private String receiveRemark;

    @ApiModelProperty(value = "是否为拆合单（0：否，1：拆单，2：合单）")
    private Integer isCombined;

    @ApiModelProperty(value = "是否拒收（0：否，1：是）")
    private Integer isRefuse;

    @ApiModelProperty(value = "发货单编号")
    private String shippingOrderNo;

    @ApiModelProperty(value = "发货时间")
    private String sendTime;

    @ApiModelProperty(value = "运费")
    private BigDecimal expressFee;

}
