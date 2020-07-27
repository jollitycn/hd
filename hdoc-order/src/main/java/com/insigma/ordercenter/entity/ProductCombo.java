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
    * 组合信息表
    * </p>
*
* @author Jason
* @since 2020-07-22
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("t_product_combo")
    @ApiModel(value="ProductCombo对象", description="组合信息表")
    public class ProductCombo implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "组合信息ID")
            @TableId(value = "product_combo_id", type = IdType.ID_WORKER)
    private Long productComboId;

            @ApiModelProperty(value = "父商品ID")
    private Long parentProductId;

            @ApiModelProperty(value = "子商品ID")
    private Long childProductId;

            @ApiModelProperty(value = "数量")
    private Integer quantity;

            @ApiModelProperty(value = "是否删除（0：正常，1：已删除）")
    private Integer isDeleted;


        public static final String PRODUCT_COMBO_ID = "product_combo_id";

        public static final String PARENT_PRODUCT_ID = "parent_product_id";

        public static final String CHILD_PRODUCT_ID = "child_product_id";

        public static final String QUANTITY = "quantity";

        public static final String IS_DELETED = "is_deleted";

}
