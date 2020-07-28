package com.insigma.ordercenter.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * @program: hdoc-parent
 * @description: 订单审单分派发仓库生成发货单时参数封装类
 * @author: AH
 * @create: 2020-07-21 17:10
 **/
@Data
@ApiModel(value = "订单审单分派发仓库生成发货单")
public class AddShippingOrderDTO {

    @ApiModelProperty(value = "发货单ID")
    @TableId(value = "shipping_order_id", type = IdType.ID_WORKER)
    private Long shippingOrderId;

    @ApiModelProperty(value = "仓库ID ")
    private Long warehouseId;

    @ApiModelProperty(value = "物流公司ID")
    private Long expressCompanyId;

    @ApiModelProperty(value = "订单明细ID")
    private Long orderDetailId;

    @ApiModelProperty(value = "创建人id")
    private Long createId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "是否删除（0：未删除，1：已删除）")
    private Integer isDeleted;

    @ApiModelProperty(value = "状态（0：待出库，1：待取货，2：已发货，3：冻结，4：冻结）")
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


}