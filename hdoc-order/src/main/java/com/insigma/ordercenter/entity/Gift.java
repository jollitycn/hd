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

/**
 * <p>
 * 赠品信息表
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_gift")
@ApiModel(value = "Gift对象", description = "赠品信息表")
public class Gift implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "赠品id")
    @TableId(value = "gift_id", type = IdType.ID_WORKER)
    private Long giftId;

    @ApiModelProperty(value = "赠品策略id")
    private Long giftStrategyId;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "赠品的数量")
    private Long giftNum;


    public static final String GIFT_ID = "gift_id";

    public static final String GIFT_STRATEGY_ID = "gift_strategy_id";

    public static final String PRODUCT_ID = "product_id";

    public static final String GIFT_NUM = "gift_num";

}
