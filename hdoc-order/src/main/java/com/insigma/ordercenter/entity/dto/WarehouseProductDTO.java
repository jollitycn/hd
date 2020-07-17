package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author youwk
 * @ClassName WarehouseProductReq
 * @description TODO
 * @date 2020/7/10 16:15
 * @Version 1.0
 */
@Data
@ApiModel("仓库绑定产品请求体")
public class WarehouseProductDTO implements Serializable {

    @ApiModelProperty("仓库编号")
    private Integer warehouseId;

    @ApiModelProperty("产品编号")
    private Long[] productIds;
}
