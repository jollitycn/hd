package com.insigma.ordercenter.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.io.Serializable;
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 
    * </p>
*
* @author youwk
* @since 2020-08-18
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("t_strategy_warehouse_relation")
    @ApiModel(value="StrategyWarehouseRelation对象", description="")
    public class StrategyWarehouseRelation implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "表主键")
            @TableId(value = "srr_id", type = IdType.AUTO)
    private Integer srrId;

            @ApiModelProperty(value = "仓库编号")
    private Integer warehouseId;

            @ApiModelProperty(value = "策略商品分类id")
    private Integer sptId;


        public static final String SRR_ID = "srr_id";

        public static final String WAREHOUSE_ID = "warehouse_id";

        public static final String SPT_ID = "spt_id";

}
