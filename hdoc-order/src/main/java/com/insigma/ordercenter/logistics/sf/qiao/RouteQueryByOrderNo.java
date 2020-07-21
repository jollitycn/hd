package com.insigma.ordercenter.logistics.sf.qiao;

import com.educiot.common.base.BaseVO;
import lombok.Data;

import java.util.List;

@Data
public class RouteQueryByOrderNo extends BaseVO {
    /**
     * language : 0
     * trackingType : 1
     * trackingNumber : ["SF7444407228423"]
     * methodType : 1
     */

    private String language;
    private String trackingType;
    private String methodType;
    private List<String> trackingNumber;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTrackingType() {
        return trackingType;
    }

    public void setTrackingType(String trackingType) {
        this.trackingType = trackingType;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public List<String> getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(List<String> trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}
