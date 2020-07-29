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
 * 区域黑名单表
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_region_blacklist_strategy")
@ApiModel(value = "RegionBlacklistStrategy对象", description = "区域黑名单表")
public class RegionBlacklistStrategy implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "区域黑名单ID")
    @TableId(value = "region_blacklist_strategy_id", type = IdType.ID_WORKER)
    private Long regionBlacklistStrategyId;

    @ApiModelProperty(value = "策略ID")
    private Long strategyId;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "原因")
    private String reason;

    @ApiModelProperty(value = "是否删除（0：未删除，1：已删除）")
    private Integer isDeleted;


    public static final String REGION_BLACKLIST_STRATEGY_ID = "region_blacklist_strategy_id";

    public static final String STRATEGY_ID = "strategy_id";

    public static final String PROVINCE = "province";

    public static final String SHOP_ID = "shop_id";

    public static final String CITY = "city";

    public static final String REASON = "reason";

    public static final String IS_DELETED = "is_deleted";

}
