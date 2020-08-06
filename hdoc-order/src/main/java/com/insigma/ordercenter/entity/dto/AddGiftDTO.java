package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/29 19:19
 */
@Data
@ApiModel("赠品信息入参实体")
public class AddGiftDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品id", required = true)
    private List<Long> productId;

    @ApiModelProperty(value = "赠品策略id", required = true)
    private Long giftStrategyId;

//    @ApiModelProperty(value = "赠品的数量", required = true)
//    private Long giftNum;
}
