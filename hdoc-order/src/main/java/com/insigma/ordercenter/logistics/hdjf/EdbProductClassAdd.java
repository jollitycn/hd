package com.insigma.ordercenter.logistics.hdjf;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

@Data
public class EdbProductClassAdd extends BaseVO {

    /**
     * orderInfo : {"sort_name":"终端品","OutSortNO":"HC-037","sort_type":"产品"}
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
         * sort_name : 终端品
         * OutSortNO : HC-037
         * sort_type : 产品
         */

        private String sort_name;
        private String OutSortNO;
        private String sort_type;

        public String getSort_name() {
            return sort_name;
        }

        public void setSort_name(String sort_name) {
            this.sort_name = sort_name;
        }

        public String getOutSortNO() {
            return OutSortNO;
        }

        public void setOutSortNO(String OutSortNO) {
            this.OutSortNO = OutSortNO;
        }

        public String getSort_type() {
            return sort_type;
        }

        public void setSort_type(String sort_type) {
            this.sort_type = sort_type;
        }
    }
}
