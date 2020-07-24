package com.insigma.ordercenter.logistics.sf.qiao;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

@Data
public class WaybillsFee  extends BaseVO {

    /**
     * trackingNum : QIAO-20200605-003
     * trackingType : 1
     */

    private String trackingNum;
    private String trackingType;

    public String getTrackingNum() {
        return trackingNum;
    }

    public void setTrackingNum(String trackingNum) {
        this.trackingNum = trackingNum;
    }

    public String getTrackingType() {
        return trackingType;
    }

    public void setTrackingType(String trackingType) {
        this.trackingType = trackingType;
    }
}
