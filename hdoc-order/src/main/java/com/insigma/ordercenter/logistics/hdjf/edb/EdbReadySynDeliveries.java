package com.insigma.ordercenter.logistics.hdjf.edb;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

@Data
public class EdbReadySynDeliveries extends BaseVO {


    /**
     * orderInfo : {"order_no":"SO1708070002056","express":"韵达","express_no":"SO1708070002056","consignee":"王菁雯","delivery_time":"2017-08-08 10:00","package_num":"1"}
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
         * order_no : SO1708070002056
         * express : 韵达
         * express_no : SO1708070002056
         * consignee : 王菁雯
         * delivery_time : 2017-08-08 10:00
         * package_num : 1
         */

        private String order_no;
        private String express;
        private String express_no;
        private String consignee;
        private String delivery_time;
        private String package_num;

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getExpress() {
            return express;
        }

        public void setExpress(String express) {
            this.express = express;
        }

        public String getExpress_no() {
            return express_no;
        }

        public void setExpress_no(String express_no) {
            this.express_no = express_no;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getDelivery_time() {
            return delivery_time;
        }

        public void setDelivery_time(String delivery_time) {
            this.delivery_time = delivery_time;
        }

        public String getPackage_num() {
            return package_num;
        }

        public void setPackage_num(String package_num) {
            this.package_num = package_num;
        }
    }
}
