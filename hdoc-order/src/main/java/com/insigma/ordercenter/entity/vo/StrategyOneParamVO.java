package com.insigma.ordercenter.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/28 19:07
 */
@Data
@ApiModel("策略1的参数信息VO")
@EqualsAndHashCode(callSuper = true)
public class StrategyOneParamVO extends BaseVO {
    @ApiModelProperty(value = "自动审单时间（min）")
    private Integer autoAuditTime;

    @ApiModelProperty(value = "店铺名称")
    private List<ShopStrategyVO> shopStrategyVO;
}
