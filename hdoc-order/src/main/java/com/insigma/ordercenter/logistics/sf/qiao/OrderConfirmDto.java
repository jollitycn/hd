package com.insigma.ordercenter.logistics.sf.qiao;

import com.educiot.common.base.BaseVO;

/**
 * @author Administrator
 */

public class OrderConfirmDto extends BaseVO {
    /**
     * dealType : 2
     * orderId : QIAO-20200605-0031
     */

    private int dealType;
    private String orderId;

    public int getDealType() {
        return dealType;
    }

    public void setDealType(int dealType) {
        this.dealType = dealType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
