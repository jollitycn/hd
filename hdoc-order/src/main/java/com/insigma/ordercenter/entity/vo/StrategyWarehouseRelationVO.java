package com.insigma.ordercenter.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel("策略关联仓库信息VO")
@EqualsAndHashCode(callSuper = true)
public class StrategyWarehouseRelationVO extends BaseVO{

    @ApiModelProperty(value = "表主键")
    private Integer srrId;

    @ApiModelProperty(value = "仓库编号")
    private Integer warehouseId;

    @ApiModelProperty(value = "策略商品分类id")
    private Integer sptId;

    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;

}
