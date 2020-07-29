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
import java.time.LocalDate;

/**
 * <p>
 * 赠品策略参数配置表
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_gift_strategy")
@ApiModel(value = "GiftStrategy对象", description = "赠品策略参数配置表")
public class GiftStrategy implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "赠品策略id")
    @TableId(value = "gift_strategy_id", type = IdType.ID_WORKER)
    private Long giftStrategyId;

    @ApiModelProperty(value = "策略id")
    private Long strategyId;

    @ApiModelProperty(value = "赠品主题")
    private String giftTheme;

    @ApiModelProperty(value = "开始日期")
    private LocalDate startDate;

    @ApiModelProperty(value = "结束日期")
    private LocalDate endDate;

    @ApiModelProperty(value = "关联的店铺（使用英文逗号分隔）")
    private String shopIds;

    @ApiModelProperty(value = "是否停用（0：未停用，1：已停用）")
    private Integer isStop;


    public static final String GIFT_STRATEGY_ID = "gift_strategy_id";

    public static final String STRATEGY_ID = "strategy_id";

    public static final String GIFT_THEME = "gift_theme";

    public static final String START_DATE = "start_date";

    public static final String END_DATE = "end_date";

    public static final String SHOP_IDS = "shop_ids";

    public static final String IS_STOP = "is_stop";

}
