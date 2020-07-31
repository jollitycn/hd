package com.insigma.ordercenter.entity.dto;
import com.insigma.ordercenter.entity.ExpressFee;
    import java.math.BigDecimal;
    import com.baomidou.mybatisplus.annotation.TableName;
    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.io.Serializable;
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
* <p>
    * 物流报价表PageDTO
    * </p>
*
* @author Jason
* @since 2020-07-30
*/
    @Data
    @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("t_express_fee")
    @ApiModel(value="ExpressFeePageDTO对象", description="物流报价表PageDTO")
    public class ExpressFeePageDTO extends ExpressFee {
    private static final long serialVersionUID = 1L;


/**
* 当前页
*/
@ApiModelProperty(value = "当前页", name = "pageNum", required = true, example = "1")
@NotNull(message = "当前页不能为空")
@Min(value = 1L, message = "查询页码最小为1")
@Max(value = Integer.MAX_VALUE, message = "查询页码超出最大限制")
protected Integer pageNum;

/**
* 每页大小
*/
@ApiModelProperty(value = "每页大小", name = "pageSize", required = true, example = "10")
@NotNull(message = "每页大小不能为空")
@Min(value = 1L, message = "每页最少查询一条数据")
@Max(value = 100L, message = "查询数量超出限制")
protected Integer pageSize;


private String orderBy;
private String orderAsc;

}