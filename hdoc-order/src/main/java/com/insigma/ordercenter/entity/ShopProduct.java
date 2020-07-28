package com.insigma.ordercenter.entity;

    import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* <p>
    * 电商发货比例表
    * </p>
*
* @author LiuHao
* @since 2020-07-28
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("t_shop_product")
    @ApiModel(value="ShopProduct对象", description="电商发货比例表")
    public class ShopProduct implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "电商发货比例表主键id")
            @TableId(value = "spid", type = IdType.ID_WORKER)
    private Long spid;

            @ApiModelProperty(value = "店铺id")
    private Long shopId;

            @ApiModelProperty(value = "商品id")
    private Long productId;

            @ApiModelProperty(value = "分配比例")
    private Integer ratio;

            @ApiModelProperty(value = "数量")
    private Integer number;

            @ApiModelProperty(value = "平台库存预警值（低于）")
    private Integer warningValue;

            @ApiModelProperty(value = "创建人id")
    private Long createId;

            @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

            @ApiModelProperty(value = "更新人id")
    private Long modifyId;

            @ApiModelProperty(value = "更新时间")
    private LocalDateTime modifyTime;


        public static final String SPID = "spid";

        public static final String SHOP_ID = "shop_id";

        public static final String PRODUCT_ID = "product_id";

        public static final String RATIO = "ratio";

        public static final String NUMBER = "number";

        public static final String WARNING_VALUE = "warning_value";

        public static final String CREATE_ID = "create_id";

        public static final String CREATE_TIME = "create_time";

        public static final String MODIFY_ID = "modify_id";

        public static final String MODIFY_TIME = "modify_time";

}
