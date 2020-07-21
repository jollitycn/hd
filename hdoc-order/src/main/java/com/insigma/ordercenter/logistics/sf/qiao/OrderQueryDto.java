package com.insigma.ordercenter.logistics.sf.qiao;

import com.educiot.common.base.BaseVO;

/**
 * @author Administrator
 */
public class OrderQueryDto extends BaseVO {
    /**
     * searchType : 1
     * orderId : QIAO-20200605-003
     * language : zh-cn
     */

    private int searchType;
    private String orderId;
    private String language;

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
