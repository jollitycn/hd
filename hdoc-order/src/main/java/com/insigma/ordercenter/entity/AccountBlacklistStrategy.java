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
 * 账户黑名单表
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_account_blacklist_strategy")
@ApiModel(value = "AccountBlacklistStrategy对象", description = "账户黑名单表")
public class AccountBlacklistStrategy implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账户黑名单ID")
    @TableId(value = "account_blacklist_strategy_id", type = IdType.ID_WORKER)
    private Long accountBlacklistStrategyId;

    @ApiModelProperty(value = "策略ID")
    private Long strategyId;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "帐号")
    private String account;

    @ApiModelProperty(value = "原因")
    private String reason;

    @ApiModelProperty(value = "是否删除（0：未删除，1：已删除）")
    private Integer isDeleted;


    public static final String ACCOUNT_BLACKLIST_STRATEGY_ID = "account_blacklist_strategy_id";

    public static final String STRATEGY_ID = "strategy_id";

    public static final String SHOP_ID = "shop_id";

    public static final String ACCOUNT = "account";

    public static final String REASON = "reason";

    public static final String IS_DELETED = "is_deleted";

}
