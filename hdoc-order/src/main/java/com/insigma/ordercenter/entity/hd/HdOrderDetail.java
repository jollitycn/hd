package com.insigma.ordercenter.entity.hd;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author youwk
 * @ClassName HdOrderDetail
 * @description TODO
 * @date 2020/7/24 15:53
 * @Version 1.0
 */
@Data
public class HdOrderDetail implements Serializable {

    private String xtwldm	   ;    //    网店代码（传‘00051’）
    private String tid        ;    //	    平台订单号码
    private String oid	       ;    //      子单号
    private String outer_iid  ;    // 	平台商品编码
    private String outer_sku_i;    // 	商家外部编码
    private Integer num_iid	   ;    //  平台商品id
    private Integer num	       ;    //   数量
    private BigDecimal price      ;    //	价格
    private BigDecimal payment	   ;    //         金额
    private BigDecimal divide_order_fee	;    //付款金额
    private BigDecimal discount_fee	    ;    //优惠金额
    private String title	            ;    //商品标题
    private BigDecimal proportion      	;    //价格比例
    //规格
    private String productSpecs;

    //单位
    private String productUnit;

    //小计
    private BigDecimal count;

}
