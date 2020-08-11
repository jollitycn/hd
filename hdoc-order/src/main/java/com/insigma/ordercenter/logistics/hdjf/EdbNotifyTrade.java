package com.insigma.ordercenter.logistics.hdjf;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

@Data
public class EdbNotifyTrade extends BaseVO {
    /**
     * bizOrderId : WZ201804270001
     * bizOrderPushType : 部分退款
     */

    private String bizOrderId;
    private String bizOrderPushType;

    public String getBizOrderId() {
        return bizOrderId;
    }

    public void setBizOrderId(String bizOrderId) {
        this.bizOrderId = bizOrderId;
    }

    public String getBizOrderPushType() {
        return bizOrderPushType;
    }

    public void setBizOrderPushType(String bizOrderPushType) {
        this.bizOrderPushType = bizOrderPushType;
    }
}
