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
/**
* <p>
    * 物流报价表DetailDTO
    * </p>
*
* @author Jason
* @since 2020-07-30
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("t_express_fee")
    @ApiModel(value="ExpressFeeDetailDTO对象", description="物流报价表DetailDTO")
public class ExpressFeeDetailDTO extends ExpressFee {
    private static final long serialVersionUID = 1L;
}