package com.insigma.ordercenter.logistics.hdjf;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

@Data
public class EdbProductDetailAdd extends BaseVO {

    /**
     * orderInfo : {"brand_name":"麦富迪","sort_name":"猫湿粮SUNNY PATTAYA阳光芭堤亚","supplier":"山东麦富迪贸易发展有限公司","product_name":"阳光芭堤亚浓汁吞拿鱼+鸡肉","market_price":"0","retail_price":"0","product_intro":"1","factory_item":"1","wfpid":"192.168.0.10"}
     * detailInfo : {"detail_item":{"bar_code":"6958862109529","specification":"60g","size":"1.0","unit":"袋","weight":"0.06","contrast_purchase_price":"1.0","sell_price":"0"}}
     */

    private OrderInfoBean orderInfo;
    private DetailInfoBean detailInfo;

    public OrderInfoBean getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoBean orderInfo) {
        this.orderInfo = orderInfo;
    }

    public DetailInfoBean getDetailInfo() {
        return detailInfo;
    }

    public void setDetailInfo(DetailInfoBean detailInfo) {
        this.detailInfo = detailInfo;
    }

    public static class OrderInfoBean {
        /**
         * brand_name : 麦富迪
         * sort_name : 猫湿粮SUNNY PATTAYA阳光芭堤亚
         * supplier : 山东麦富迪贸易发展有限公司
         * product_name : 阳光芭堤亚浓汁吞拿鱼+鸡肉
         * market_price : 0
         * retail_price : 0
         * product_intro : 1
         * factory_item : 1
         * wfpid : 192.168.0.10
         */

        private String brand_name;
        private String sort_name;
        private String supplier;
        private String product_name;
        private String market_price;
        private String retail_price;
        private String product_intro;
        private String factory_item;
        private String wfpid;

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getSort_name() {
            return sort_name;
        }

        public void setSort_name(String sort_name) {
            this.sort_name = sort_name;
        }

        public String getSupplier() {
            return supplier;
        }

        public void setSupplier(String supplier) {
            this.supplier = supplier;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }

        public String getRetail_price() {
            return retail_price;
        }

        public void setRetail_price(String retail_price) {
            this.retail_price = retail_price;
        }

        public String getProduct_intro() {
            return product_intro;
        }

        public void setProduct_intro(String product_intro) {
            this.product_intro = product_intro;
        }

        public String getFactory_item() {
            return factory_item;
        }

        public void setFactory_item(String factory_item) {
            this.factory_item = factory_item;
        }

        public String getWfpid() {
            return wfpid;
        }

        public void setWfpid(String wfpid) {
            this.wfpid = wfpid;
        }
    }

    public static class DetailInfoBean {
        /**
         * detail_item : {"bar_code":"6958862109529","specification":"60g","size":"1.0","unit":"袋","weight":"0.06","contrast_purchase_price":"1.0","sell_price":"0"}
         */

        private DetailItemBean detail_item;

        public DetailItemBean getDetail_item() {
            return detail_item;
        }

        public void setDetail_item(DetailItemBean detail_item) {
            this.detail_item = detail_item;
        }

        public static class DetailItemBean {
            /**
             * bar_code : 6958862109529
             * specification : 60g
             * size : 1.0
             * unit : 袋
             * weight : 0.06
             * contrast_purchase_price : 1.0
             * sell_price : 0
             */

            private String bar_code;
            private String specification;
            private String size;
            private String unit;
            private String weight;
            private String contrast_purchase_price;
            private String sell_price;

            public String getBar_code() {
                return bar_code;
            }

            public void setBar_code(String bar_code) {
                this.bar_code = bar_code;
            }

            public String getSpecification() {
                return specification;
            }

            public void setSpecification(String specification) {
                this.specification = specification;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getContrast_purchase_price() {
                return contrast_purchase_price;
            }

            public void setContrast_purchase_price(String contrast_purchase_price) {
                this.contrast_purchase_price = contrast_purchase_price;
            }

            public String getSell_price() {
                return sell_price;
            }

            public void setSell_price(String sell_price) {
                this.sell_price = sell_price;
            }
        }
    }
}
