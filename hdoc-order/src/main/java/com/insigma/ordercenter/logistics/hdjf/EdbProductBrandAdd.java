package com.insigma.ordercenter.logistics.hdjf;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

import java.util.List;

@Data
public class EdbProductBrandAdd extends BaseVO {
    /**
     * orderInfo : {"brand_name":"Zimmerli齐穆里2016","OutBrandNO":"Z005","Cdescript":[],"Cremark":[],"Is_active":"1"}
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
         * brand_name : Zimmerli齐穆里2016
         * OutBrandNO : Z005
         * Cdescript : []
         * Cremark : []
         * Is_active : 1
         */

        private String brand_name;
        private String OutBrandNO;
        private String Is_active;
        private List<?> Cdescript;
        private List<?> Cremark;

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getOutBrandNO() {
            return OutBrandNO;
        }

        public void setOutBrandNO(String OutBrandNO) {
            this.OutBrandNO = OutBrandNO;
        }

        public String getIs_active() {
            return Is_active;
        }

        public void setIs_active(String Is_active) {
            this.Is_active = Is_active;
        }

        public List<?> getCdescript() {
            return Cdescript;
        }

        public void setCdescript(List<?> Cdescript) {
            this.Cdescript = Cdescript;
        }

        public List<?> getCremark() {
            return Cremark;
        }

        public void setCremark(List<?> Cremark) {
            this.Cremark = Cremark;
        }
    }
}
