package com.insigma.ordercenter.logistics.hdjf;

import java.util.List;

public class CreateOrder {
    /**
     * clientID : TEST
     * logisticProviderID : YTO
     * customerId : a92266073246b3ed2a2f0ff4d0b2bf5e
     * txLogisticID : LP07082300225709
     * tradeNo : 2007082300225709
     * mailNo : 124579546621
     * totalServiceFee : 0.0
     * codSplitFee : 0.0
     * orderType : 1
     * serviceType : 0
     * flag : 0
     * sender : {"name":"张三","postCode":"310013","phone":"231234134","mobile":"13575745195","prov":"上海","city":"上海,浦东区","address":"新龙科技大厦9层"}
     * receiver : {"name":"李四","postCode":"100000","phone":"231234134","prov":"北京","city":"北京市、朝阳区","address":"新龙科技大厦9层"}
     * increments : [{"type":"1","name":"到付","money":"2.00","remark":"到付","attr":"1111"}]
     * sendStartTime : 2005-08-24 08:00:00.0 CST
     * sendEndTime : 2005-08-24 12:00:00.0 CST
     * goodsValue : 1900
     * itemsValue : 2000
     * items : [{"itemName":"Nokia N73","number":"2","itemValue":"2"},{"itemName":"Nokia N72","number":"1","itemValue":"2"}]
     * insuranceValue : 0.0
     * special : 0
     * remark : 易碎品
     */

    private String clientID;
    private String logisticProviderID;
    private String customerId;
    private String txLogisticID;
    private String tradeNo;
    private String mailNo;
    private String totalServiceFee;
    private String codSplitFee;
    private String orderType;
    private String serviceType;
    private String flag;
    private SenderBean sender;
    private ReceiverBean receiver;
    private String sendStartTime;
    private String sendEndTime;
    private String goodsValue;
    private String itemsValue;
    private String insuranceValue;
    private String special;
    private String remark;
    private List<IncrementsBean> increments;
    private List<ItemsBean> items;

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getLogisticProviderID() {
        return logisticProviderID;
    }

    public void setLogisticProviderID(String logisticProviderID) {
        this.logisticProviderID = logisticProviderID;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTxLogisticID() {
        return txLogisticID;
    }

    public void setTxLogisticID(String txLogisticID) {
        this.txLogisticID = txLogisticID;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getMailNo() {
        return mailNo;
    }

    public void setMailNo(String mailNo) {
        this.mailNo = mailNo;
    }

    public String getTotalServiceFee() {
        return totalServiceFee;
    }

    public void setTotalServiceFee(String totalServiceFee) {
        this.totalServiceFee = totalServiceFee;
    }

    public String getCodSplitFee() {
        return codSplitFee;
    }

    public void setCodSplitFee(String codSplitFee) {
        this.codSplitFee = codSplitFee;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public SenderBean getSender() {
        return sender;
    }

    public void setSender(SenderBean sender) {
        this.sender = sender;
    }

    public ReceiverBean getReceiver() {
        return receiver;
    }

    public void setReceiver(ReceiverBean receiver) {
        this.receiver = receiver;
    }

    public String getSendStartTime() {
        return sendStartTime;
    }

    public void setSendStartTime(String sendStartTime) {
        this.sendStartTime = sendStartTime;
    }

    public String getSendEndTime() {
        return sendEndTime;
    }

    public void setSendEndTime(String sendEndTime) {
        this.sendEndTime = sendEndTime;
    }

    public String getGoodsValue() {
        return goodsValue;
    }

    public void setGoodsValue(String goodsValue) {
        this.goodsValue = goodsValue;
    }

    public String getItemsValue() {
        return itemsValue;
    }

    public void setItemsValue(String itemsValue) {
        this.itemsValue = itemsValue;
    }

    public String getInsuranceValue() {
        return insuranceValue;
    }

    public void setInsuranceValue(String insuranceValue) {
        this.insuranceValue = insuranceValue;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<IncrementsBean> getIncrements() {
        return increments;
    }

    public void setIncrements(List<IncrementsBean> increments) {
        this.increments = increments;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class SenderBean {
        /**
         * name : 张三
         * postCode : 310013
         * phone : 231234134
         * mobile : 13575745195
         * prov : 上海
         * city : 上海,浦东区
         * address : 新龙科技大厦9层
         */

        private String name;
        private String postCode;
        private String phone;
        private String mobile;
        private String prov;
        private String city;
        private String address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPostCode() {
            return postCode;
        }

        public void setPostCode(String postCode) {
            this.postCode = postCode;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getProv() {
            return prov;
        }

        public void setProv(String prov) {
            this.prov = prov;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public static class ReceiverBean {
        /**
         * name : 李四
         * postCode : 100000
         * phone : 231234134
         * prov : 北京
         * city : 北京市、朝阳区
         * address : 新龙科技大厦9层
         */

        private String name;
        private String postCode;
        private String phone;
        private String prov;
        private String city;
        private String address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPostCode() {
            return postCode;
        }

        public void setPostCode(String postCode) {
            this.postCode = postCode;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProv() {
            return prov;
        }

        public void setProv(String prov) {
            this.prov = prov;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public static class IncrementsBean {
        /**
         * type : 1
         * name : 到付
         * money : 2.00
         * remark : 到付
         * attr : 1111
         */

        private String type;
        private String name;
        private String money;
        private String remark;
        private String attr;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getAttr() {
            return attr;
        }

        public void setAttr(String attr) {
            this.attr = attr;
        }
    }

    public static class ItemsBean {
        /**
         * itemName : Nokia N73
         * number : 2
         * itemValue : 2
         */

        private String itemName;
        private String number;
        private String itemValue;

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getItemValue() {
            return itemValue;
        }

        public void setItemValue(String itemValue) {
            this.itemValue = itemValue;
        }
    }
}
