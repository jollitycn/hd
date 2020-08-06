package com.insigma.ordercenter.service.sf.oms;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

import java.util.List;

@Data
public class TransportRequest extends BaseVO {
    /**
     * orderItems : [{"temperatureLevelName":"0至10","remark":"这是备注","skuName":"维生素C咀嚼片","quantity":"50","grossWeight":"12","volume":"12"}]
     * erpOrder : 1233
     * monthlyAccount : 7550612539
     * consigneeProvinceName : 广东省
     * paymentTypeCode : PR_ACCOUNT
     * shipperLocationName : 宝安M17大厦A栋07
     * shipperProvinceName : 广东省
     * shipperContactName : 奥特曼
     * shipperCityName : 深圳市
     * consigneeLocationName : 广东省深圳市南山区深圳南山深南大道58号
     * extenSystemOrderNo : A00000002
     * shipperName : M17星制药
     * consigneeCityName : 深圳市
     * remark : 这是备注
     * consigneeName : 顺丰物流公司
     * consigneeContactName : 李生
     * consigneeContactTel : 13924222888
     * consigneeDistrictName : 宝安区
     * shipperContactTel : 13700000002
     * shipperDistrictName : 福田区
     * productCode : SE0059
     * temperatureLevelCode : 5
     * sourceCode : demo-sysrem
     * orderTime : 2018-01-01 12:12:12
     * transportType : LAND
     * orderServices : [{"serviceValue":"","serviceCode":"VA0003"},{"serviceValue":"3000","serviceCode":"VA0021"},{"serviceValue":"","serviceCode":"VA0059"},{"serviceValue":"","serviceCode":"VA0058"}]
     */

    private String erpOrder;
    private String monthlyAccount;
    private String consigneeProvinceName;
    private String paymentTypeCode;
    private String shipperLocationName;
    private String shipperProvinceName;
    private String shipperContactName;
    private String shipperCityName;
    private String consigneeLocationName;
    private String extenSystemOrderNo;
    private String shipperName;
    private String consigneeCityName;
    private String remark;
    private String consigneeName;
    private String consigneeContactName;
    private String consigneeContactTel;
    private String consigneeDistrictName;
    private String shipperContactTel;
    private String shipperDistrictName;
    private String productCode;
    private String temperatureLevelCode;
    private String sourceCode;
    private String orderTime;
    private String transportType;
    private List<OrderItemsBean> orderItems;
    private List<OrderServicesBean> orderServices;

    public String getErpOrder() {
        return erpOrder;
    }

    public void setErpOrder(String erpOrder) {
        this.erpOrder = erpOrder;
    }

    public String getMonthlyAccount() {
        return monthlyAccount;
    }

    public void setMonthlyAccount(String monthlyAccount) {
        this.monthlyAccount = monthlyAccount;
    }

    public String getConsigneeProvinceName() {
        return consigneeProvinceName;
    }

    public void setConsigneeProvinceName(String consigneeProvinceName) {
        this.consigneeProvinceName = consigneeProvinceName;
    }

    public String getPaymentTypeCode() {
        return paymentTypeCode;
    }

    public void setPaymentTypeCode(String paymentTypeCode) {
        this.paymentTypeCode = paymentTypeCode;
    }

    public String getShipperLocationName() {
        return shipperLocationName;
    }

    public void setShipperLocationName(String shipperLocationName) {
        this.shipperLocationName = shipperLocationName;
    }

    public String getShipperProvinceName() {
        return shipperProvinceName;
    }

    public void setShipperProvinceName(String shipperProvinceName) {
        this.shipperProvinceName = shipperProvinceName;
    }

    public String getShipperContactName() {
        return shipperContactName;
    }

    public void setShipperContactName(String shipperContactName) {
        this.shipperContactName = shipperContactName;
    }

    public String getShipperCityName() {
        return shipperCityName;
    }

    public void setShipperCityName(String shipperCityName) {
        this.shipperCityName = shipperCityName;
    }

    public String getConsigneeLocationName() {
        return consigneeLocationName;
    }

    public void setConsigneeLocationName(String consigneeLocationName) {
        this.consigneeLocationName = consigneeLocationName;
    }

    public String getExtenSystemOrderNo() {
        return extenSystemOrderNo;
    }

    public void setExtenSystemOrderNo(String extenSystemOrderNo) {
        this.extenSystemOrderNo = extenSystemOrderNo;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getConsigneeCityName() {
        return consigneeCityName;
    }

    public void setConsigneeCityName(String consigneeCityName) {
        this.consigneeCityName = consigneeCityName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeContactName() {
        return consigneeContactName;
    }

    public void setConsigneeContactName(String consigneeContactName) {
        this.consigneeContactName = consigneeContactName;
    }

    public String getConsigneeContactTel() {
        return consigneeContactTel;
    }

    public void setConsigneeContactTel(String consigneeContactTel) {
        this.consigneeContactTel = consigneeContactTel;
    }

    public String getConsigneeDistrictName() {
        return consigneeDistrictName;
    }

    public void setConsigneeDistrictName(String consigneeDistrictName) {
        this.consigneeDistrictName = consigneeDistrictName;
    }

    public String getShipperContactTel() {
        return shipperContactTel;
    }

    public void setShipperContactTel(String shipperContactTel) {
        this.shipperContactTel = shipperContactTel;
    }

    public String getShipperDistrictName() {
        return shipperDistrictName;
    }

    public void setShipperDistrictName(String shipperDistrictName) {
        this.shipperDistrictName = shipperDistrictName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getTemperatureLevelCode() {
        return temperatureLevelCode;
    }

    public void setTemperatureLevelCode(String temperatureLevelCode) {
        this.temperatureLevelCode = temperatureLevelCode;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public List<OrderItemsBean> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemsBean> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OrderServicesBean> getOrderServices() {
        return orderServices;
    }

    public void setOrderServices(List<OrderServicesBean> orderServices) {
        this.orderServices = orderServices;
    }

    public static class OrderItemsBean {
        /**
         * temperatureLevelName : 0至10
         * remark : 这是备注
         * skuName : 维生素C咀嚼片
         * quantity : 50
         * grossWeight : 12
         * volume : 12
         */

        private String temperatureLevelName;
        private String remark;
        private String skuName;
        private String quantity;
        private String grossWeight;
        private String volume;

        public String getTemperatureLevelName() {
            return temperatureLevelName;
        }

        public void setTemperatureLevelName(String temperatureLevelName) {
            this.temperatureLevelName = temperatureLevelName;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSkuName() {
            return skuName;
        }

        public void setSkuName(String skuName) {
            this.skuName = skuName;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getGrossWeight() {
            return grossWeight;
        }

        public void setGrossWeight(String grossWeight) {
            this.grossWeight = grossWeight;
        }

        public String getVolume() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }
    }

    public static class OrderServicesBean {
        /**
         * serviceValue :
         * serviceCode : VA0003
         */

        private String serviceValue;
        private String serviceCode;

        public String getServiceValue() {
            return serviceValue;
        }

        public void setServiceValue(String serviceValue) {
            this.serviceValue = serviceValue;
        }

        public String getServiceCode() {
            return serviceCode;
        }

        public void setServiceCode(String serviceCode) {
            this.serviceCode = serviceCode;
        }
    }
}

