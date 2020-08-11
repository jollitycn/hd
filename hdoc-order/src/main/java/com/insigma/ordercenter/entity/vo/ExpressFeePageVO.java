package com.insigma.ordercenter.entity.vo;
import com.insigma.ordercenter.entity.ExpressFee;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;
/**
* <p>
    * 物流报价表PageVO
    * </p>
*
* @author Jason
* @since 2020-07-30
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("t_express_fee")
    @ApiModel(value="ExpressFeePageVO对象", description="物流报价表PageVO")
public class ExpressFeePageVO extends ExpressFee {
    private static final long serialVersionUID = 1L;
    private String productTypeName;
}