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
 * 换货策略参数配置表
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_exchange_strategy")
@ApiModel(value = "ExchangeStrategy对象", description = "换货策略参数配置表")
public class ExchangeStrategy implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "换货策略id")
    @TableId(value = "exchange_strategy_id", type = IdType.ID_WORKER)
    private Long exchangeStrategyId;

    @ApiModelProperty(value = "策略id")
    private Long strategyId;

    @ApiModelProperty(value = "换货主题")
    private String exchangeTheme;

    @ApiModelProperty(value = "开始日期")
    private LocalDate startDate;

    @ApiModelProperty(value = "结束日期")
    private LocalDate endDate;

    @ApiModelProperty(value = "原商品id")
    private Long oldProductId;

    @ApiModelProperty(value = "新商品id")
    private Long newProductId;

//    @ApiModelProperty(value = "关联的店铺（使用英文逗号分隔）")
//    private String shopIds;

    @ApiModelProperty(value = "是否停用（0：未停用，1：已停用）")
    private Integer isStop;

    public static final String EXCHANGE_STRATEGY_ID = "exchange_strategy_id";

    public static final String STRATEGY_ID = "strategy_id";

    public static final String EXCHANGE_THEME = "exchange_theme";

    public static final String START_DATE = "start_date";

    public static final String END_DATE = "end_date";

    public static final String OLD_PRODUCT_ID = "old_product_id";

    public static final String NEW_PRODUCT_ID = "new_product_id";

    public static final String IS_STOP = "is_stop";

}
