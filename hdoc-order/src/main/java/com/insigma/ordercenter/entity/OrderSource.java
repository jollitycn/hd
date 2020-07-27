package com.insigma.ordercenter.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.insigma.ordercenter.constant.Constant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单来源定义表
 * </p>
 *
 * @author Jason
 * @since 2020-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_order_source")
@ApiModel(value = "OrderSource对象", description = "货主信息表")
public class OrderSource implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "货主ID")
    @TableId(value = "order_source_id", type = IdType.AUTO)
    private Integer orderSourceId;

    @ApiModelProperty(value = "货主编号")
    private String sourceNo;

    @ApiModelProperty(value = "货主名称")
    private String sourceName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "是否删除（0：未删除，1：已删除）")
    private Integer isDeleted;

    @ApiModelProperty(value = "说明")
    private String remark;

    @ApiModelProperty(value = "是否停用（0：未停用，1：已停用）")
    private Integer isStop;

    @ApiModelProperty(value = "创建人ID")
    private Long createId;

    public static final String ORDER_SOURCE_ID = "order_source_id";

    public static final String SOURCE_NO = "source_no";

    public static final String SOURCE_NAME = "source_name";

    public static final String CREATE_TIME = "create_time";

    public static final String IS_DELETED = "is_deleted";

    public static final String REMARK = "remark";

    public static final String IS_STOP = "is_stop";

    public static final String CREATE_ID = "create_id";

}
