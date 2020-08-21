package com.insigma.ordercenter.entity.vo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.insigma.ordercenter.entity.StrategyRegionRelation;
import com.insigma.ordercenter.entity.StrategyWarehouseRelation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@ApiModel("商品、分类策略表VO")
@EqualsAndHashCode(callSuper = true)
public class StrategyProductTypeVO extends BaseVO{

    @ApiModelProperty(value = "策略商品、分类表主键")
    private Integer strategyProductTypeId;

    @ApiModelProperty(value = "参数类型（1-商品分类，2-商品）")
    private Integer paramType;

    @ApiModelProperty(value = "参数id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long paramId;

    @ApiModelProperty(value = "策略名称")
    private String strategyName;

    @ApiModelProperty(value = "是否禁用")
    private Integer isStop;

    @ApiModelProperty(value = "商品分类名称")
    private String dataValue;

    @ApiModelProperty(value = " 策略关联区域列表")
    private List<StrategyRegionRelationVO> strategyRegionRelations;

    @ApiModelProperty(value = " 策略关联仓库列表")
    private List<StrategyWarehouseRelationVO> strategyWarehouseRelations;
}
