package com.insigma.ordercenter.logistics.hdjf;

import com.insigma.ordercenter.entity.vo.BaseVO;

import java.util.List;

public class EdbTradeAdd extends BaseVO {
    /**
     * orderInfo : {"out_tid":"WZ2017121900003","shop_id":"17","storage_id":"4","buyer_id":"中原一点红","buyer_msg":",9.28云集廊坊仓安慕希黄桃+燕麦出库PO201709280103","seller_remark":[],"consignee":"张<三","telephone":"13288888888","mobilPhone":"13288888888","privince":"陕西省","city":"西安市","area":"高新区","address":"陕西省西安市高新区丈八五路46号长盛科技产业园","actual_freight_get":"0","is_COD":"0","order_totalMoney":"138","product_totalMoney":"138","favorable_money":"0","terminal_type":"手机","pay_method":"微信支付","out_payNo":"20549778_02081654","pay_date":"2017-12-19 08:16","order_date":"2017-12-19 08:16","pay_status":"已付款","invoice_type":"个人","invoice_title":"发票抬头","invoice_msg":"发票内容"}
     * product_info : {"product_item":{"barCode":"911911888308057","product_title":"兄弟鸭舌，真正宗的武汉原味，正品信赖二十年久远","standard":"袋","favorite_money":"0","orderGoods_Num":"1","cost_Price":"46","out_tid":"WZ2017121900003"}}
     */

    private OrderInfoBean orderInfo;
    private ProductInfoBean product_info;

    public OrderInfoBean getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoBean orderInfo) {
        this.orderInfo = orderInfo;
    }

    public ProductInfoBean getProduct_info() {
        return product_info;
    }

    public void setProduct_info(ProductInfoBean product_info) {
        this.product_info = product_info;
    }

    public static class OrderInfoBean {
        /**
         * out_tid : WZ2017121900003
         * shop_id : 17
         * storage_id : 4
         * buyer_id : 中原一点红
         * buyer_msg : ,9.28云集廊坊仓安慕希黄桃+燕麦出库PO201709280103
         * seller_remark : []
         * consignee : 张<三
         * telephone : 13288888888
         * mobilPhone : 13288888888
         * privince : 陕西省
         * city : 西安市
         * area : 高新区
         * address : 陕西省西安市高新区丈八五路46号长盛科技产业园
         * actual_freight_get : 0
         * is_COD : 0
         * order_totalMoney : 138
         * product_totalMoney : 138
         * favorable_money : 0
         * terminal_type : 手机
         * pay_method : 微信支付
         * out_payNo : 20549778_02081654
         * pay_date : 2017-12-19 08:16
         * order_date : 2017-12-19 08:16
         * pay_status : 已付款
         * invoice_type : 个人
         * invoice_title : 发票抬头
         * invoice_msg : 发票内容
         */

        private String out_tid;
        private String shop_id;
        private String storage_id;
        private String buyer_id;
        private String buyer_msg;
        private String consignee;
        private String telephone;
        private String mobilPhone;
        private String privince;
        private String city;
        private String area;
        private String address;
        private String actual_freight_get;
        private String is_COD;
        private String order_totalMoney;
        private String product_totalMoney;
        private String favorable_money;
        private String terminal_type;
        private String pay_method;
        private String out_payNo;
        private String pay_date;
        private String order_date;
        private String pay_status;
        private String invoice_type;
        private String invoice_title;
        private String invoice_msg;
        private List<?> seller_remark;

        public String getOut_tid() {
            return out_tid;
        }

        public void setOut_tid(String out_tid) {
            this.out_tid = out_tid;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getStorage_id() {
            return storage_id;
        }

        public void setStorage_id(String storage_id) {
            this.storage_id = storage_id;
        }

        public String getBuyer_id() {
            return buyer_id;
        }

        public void setBuyer_id(String buyer_id) {
            this.buyer_id = buyer_id;
        }

        public String getBuyer_msg() {
            return buyer_msg;
        }

        public void setBuyer_msg(String buyer_msg) {
            this.buyer_msg = buyer_msg;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getMobilPhone() {
            return mobilPhone;
        }

        public void setMobilPhone(String mobilPhone) {
            this.mobilPhone = mobilPhone;
        }

        public String getPrivince() {
            return privince;
        }

        public void setPrivince(String privince) {
            this.privince = privince;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getActual_freight_get() {
            return actual_freight_get;
        }

        public void setActual_freight_get(String actual_freight_get) {
            this.actual_freight_get = actual_freight_get;
        }

        public String getIs_COD() {
            return is_COD;
        }

        public void setIs_COD(String is_COD) {
            this.is_COD = is_COD;
        }

        public String getOrder_totalMoney() {
            return order_totalMoney;
        }

        public void setOrder_totalMoney(String order_totalMoney) {
            this.order_totalMoney = order_totalMoney;
        }

        public String getProduct_totalMoney() {
            return product_totalMoney;
        }

        public void setProduct_totalMoney(String product_totalMoney) {
            this.product_totalMoney = product_totalMoney;
        }

        public String getFavorable_money() {
            return favorable_money;
        }

        public void setFavorable_money(String favorable_money) {
            this.favorable_money = favorable_money;
        }

        public String getTerminal_type() {
            return terminal_type;
        }

        public void setTerminal_type(String terminal_type) {
            this.terminal_type = terminal_type;
        }

        public String getPay_method() {
            return pay_method;
        }

        public void setPay_method(String pay_method) {
            this.pay_method = pay_method;
        }

        public String getOut_payNo() {
            return out_payNo;
        }

        public void setOut_payNo(String out_payNo) {
            this.out_payNo = out_payNo;
        }

        public String getPay_date() {
            return pay_date;
        }

        public void setPay_date(String pay_date) {
            this.pay_date = pay_date;
        }

        public String getOrder_date() {
            return order_date;
        }

        public void setOrder_date(String order_date) {
            this.order_date = order_date;
        }

        public String getPay_status() {
            return pay_status;
        }

        public void setPay_status(String pay_status) {
            this.pay_status = pay_status;
        }

        public String getInvoice_type() {
            return invoice_type;
        }

        public void setInvoice_type(String invoice_type) {
            this.invoice_type = invoice_type;
        }

        public String getInvoice_title() {
            return invoice_title;
        }

        public void setInvoice_title(String invoice_title) {
            this.invoice_title = invoice_title;
        }

        public String getInvoice_msg() {
            return invoice_msg;
        }

        public void setInvoice_msg(String invoice_msg) {
            this.invoice_msg = invoice_msg;
        }

        public List<?> getSeller_remark() {
            return seller_remark;
        }

        public void setSeller_remark(List<?> seller_remark) {
            this.seller_remark = seller_remark;
        }
    }

    public static class ProductInfoBean {
        /**
         * product_item : {"barCode":"911911888308057","product_title":"兄弟鸭舌，真正宗的武汉原味，正品信赖二十年久远","standard":"袋","favorite_money":"0","orderGoods_Num":"1","cost_Price":"46","out_tid":"WZ2017121900003"}
         */

        private ProductItemBean product_item;

        public ProductItemBean getProduct_item() {
            return product_item;
        }

        public void setProduct_item(ProductItemBean product_item) {
            this.product_item = product_item;
        }

        public static class ProductItemBean {
            /**
             * barCode : 911911888308057
             * product_title : 兄弟鸭舌，真正宗的武汉原味，正品信赖二十年久远
             * standard : 袋
             * favorite_money : 0
             * orderGoods_Num : 1
             * cost_Price : 46
             * out_tid : WZ2017121900003
             */

            private String barCode;
            private String product_title;
            private String standard;
            private String favorite_money;
            private String orderGoods_Num;
            private String cost_Price;
            private String out_tid;

            public String getBarCode() {
                return barCode;
            }

            public void setBarCode(String barCode) {
                this.barCode = barCode;
            }

            public String getProduct_title() {
                return product_title;
            }

            public void setProduct_title(String product_title) {
                this.product_title = product_title;
            }

            public String getStandard() {
                return standard;
            }

            public void setStandard(String standard) {
                this.standard = standard;
            }

            public String getFavorite_money() {
                return favorite_money;
            }

            public void setFavorite_money(String favorite_money) {
                this.favorite_money = favorite_money;
            }

            public String getOrderGoods_Num() {
                return orderGoods_Num;
            }

            public void setOrderGoods_Num(String orderGoods_Num) {
                this.orderGoods_Num = orderGoods_Num;
            }

            public String getCost_Price() {
                return cost_Price;
            }

            public void setCost_Price(String cost_Price) {
                this.cost_Price = cost_Price;
            }

            public String getOut_tid() {
                return out_tid;
            }

            public void setOut_tid(String out_tid) {
                this.out_tid = out_tid;
            }
        }
    }
}
