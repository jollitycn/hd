package com.insigma.ordercenter.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel("策略关联区域信息VO")
@EqualsAndHashCode(callSuper = true)
public class StrategyRegionRelationVO extends BaseVO{

    @ApiModelProperty(value = "表主键")
    private Integer srrId;

    @ApiModelProperty(value = "区域编号")
    private Integer regionId;

    @ApiModelProperty(value = "策略商品分类id")
    private Integer sptId;

    @ApiModelProperty(value = "区域全称")
    private String mergerName;

}
