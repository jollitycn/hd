package com.insigma.ordercenter.logistics.sf.qiao;

import com.educiot.common.base.BaseVO;
import lombok.Data;

@Data
public class SubMailNo extends BaseVO {
    /**
     * orderId : QIAO-20200605-003
     * parcelQty : 2
     */

    private String orderId;
    private int parcelQty;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getParcelQty() {
        return parcelQty;
    }

    public void setParcelQty(int parcelQty) {
        this.parcelQty = parcelQty;
    }
}
