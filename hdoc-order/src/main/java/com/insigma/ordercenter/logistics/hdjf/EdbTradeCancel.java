package com.insigma.ordercenter.logistics.hdjf;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

@Data
public class EdbTradeCancel extends BaseVO {
    /**
     * orderInfo : {"tid":"S1709090055178"}
     */

    private OrderInfoBean orderInfo;

    public OrderInfoBean getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoBean orderInfo) {
        this.orderInfo = orderInfo;
    }

    public static class OrderInfoBean {
        /**
         * tid : S1709090055178
         */

        private String tid;

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }
    }
}
