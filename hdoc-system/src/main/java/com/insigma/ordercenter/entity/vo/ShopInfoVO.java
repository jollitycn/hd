package com.insigma.ordercenter.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/24 16:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("店铺信息返回实体VO")
public class ShopInfoVO extends BaseVO {
    @ApiModelProperty(value = "店铺ID", name = "shopId")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long shopId;

    @ApiModelProperty(value = "店铺名称", name = "shopName")
    private String shopName;
}
