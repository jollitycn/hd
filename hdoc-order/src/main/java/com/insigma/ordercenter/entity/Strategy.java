package com.insigma.ordercenter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 策略表
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_strategy")
@ApiModel(value = "Strategy对象", description = "策略表")
public class Strategy implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "策略ID")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "strategy_id", type = IdType.ID_WORKER)
    private Long strategyId;

    @ApiModelProperty(value = "策略code")
    private String strategyCode;

    @ApiModelProperty(value = "策略类型（0：自动审单，1：订单策略，2：订单拦截）")
    private Integer strategyType;

    @ApiModelProperty(value = "策略名称")
    private String strategyName;

    @ApiModelProperty(value = "策略内容")
    private String strategyContect;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否停用（0：未停用，1：已停用）")
    private Integer isStop;

    @ApiModelProperty(value = "自动审单时间（min）")
    private Integer autoAuditTime;

    @ApiModelProperty(value = "推送物流公司时间（min）")
    private Integer expressTime;

    @ApiModelProperty(value = "优先级")
    private Integer priority;

    @ApiModelProperty(value = "参数配置标识：0---不可配置参数   1---可配置参数")
    private Integer paramFlag;

    public static final String STRATEGY_ID = "strategy_id";

    public static final String STRATEGY_CODE = "strategy_code";

    public static final String STRATEGY_TYPE = "strategy_type";

    public static final String STRATEGY_NAME = "strategy_name";

    public static final String STRATEGY_CONTECT = "strategy_contect";

    public static final String CREATE_TIME = "create_time";

    public static final String REMARK = "remark";

    public static final String IS_STOP = "is_stop";

    public static final String AUTO_AUDIT_TIME = "auto_audit_time";

    public static final String EXPRESS_TIME = "express_time";

    public static final String PRIORITY = "priority";

    public static final String PARAM_FLAG = "param_flag";

}
