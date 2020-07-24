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
    * 物流仓库关联表
    * </p>
*
* @author LiuHao
* @since 2020-07-22
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("t_express_warehouse_relation")
    @ApiModel(value="ExpressWarehouseRelation对象", description="物流仓库关联表")
    public class ExpressWarehouseRelation implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "物流仓库关联ID")
            @TableId(value = "express_warehouse_relation_id", type = IdType.ID_WORKER)
    private Long expressWarehouseRelationId;

            @ApiModelProperty(value = "物流公司ID")
    private Long expressCompanyId;

            @ApiModelProperty(value = "仓库ID ")
    private Long warehouseId;

            @ApiModelProperty(value = "优先级")
    private Integer priority;


        public static final String EXPRESS_WAREHOUSE_RELATION_ID = "express_warehouse_relation_id";

        public static final String EXPRESS_COMPANY_ID = "express_company_id";

        public static final String WAREHOUSE_ID = "warehouse_id";

        public static final String PRIORITY = "priority";

}
