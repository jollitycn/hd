package com.insigma.ordercenter.service.sf.oms;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

import java.util.List;

@Data
public class Outbound  extends BaseVO {
    /**
     * detail : [{"isBom":"N","skuName":"羊腿礼盒","skuNo":"10000011","packageUnitCode":"EA","quantity":"1","erpOrderLineNum":1}]
     * consignee : {"consigneeContactTel":"13552179716","consigneeLocationName":"北京北京市大兴区原生墅41-3","consigneeCityName":"北京市","consigneeContactPhone":"13552179716","consigneeContactName":"刘素芳","consigneeDistrictName":"大兴区","consigneeCountryName":"中国","consigneeProvinceName":"北京"}
     * customer : {"customerMonthlyCard":"0276712331","companyCode":"0276712331"}
     * warehouseCode : P027CSA
     * orgErpOrder : 00200703840107
     * priorityCode : 3
     * sfOrderType : PO
     * carrierServiceType : SE0113
     * shipper : {}
     * completeDelivery : Y
     * orgTradeOrder : 200723131756699
     * orderTime : 2020-07-23 13:17:56
     * carrierCode : CP
     * addedService : [{}]
     * paymentDistrict : 大兴区
     * erpOrder : 00200703840107
     */

    private ConsigneeBean consignee;
    private CustomerBean customer;
    private String warehouseCode;
    private String orgErpOrder;
    private String priorityCode;
    private String sfOrderType;
    private String carrierServiceType;
    private ShipperBean shipper;
    private String completeDelivery;
    private String orgTradeOrder;
    private String orderTime;
    private String carrierCode;
    private String paymentDistrict;
    private String erpOrder;
    private List<DetailBean> detail;
    private List<AddedServiceBean> addedService;

    public ConsigneeBean getConsignee() {
        return consignee;
    }

    public void setConsignee(ConsigneeBean consignee) {
        this.consignee = consignee;
    }

    public CustomerBean getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerBean customer) {
        this.customer = customer;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getOrgErpOrder() {
        return orgErpOrder;
    }

    public void setOrgErpOrder(String orgErpOrder) {
        this.orgErpOrder = orgErpOrder;
    }

    public String getPriorityCode() {
        return priorityCode;
    }

    public void setPriorityCode(String priorityCode) {
        this.priorityCode = priorityCode;
    }

    public String getSfOrderType() {
        return sfOrderType;
    }

    public void setSfOrderType(String sfOrderType) {
        this.sfOrderType = sfOrderType;
    }

    public String getCarrierServiceType() {
        return carrierServiceType;
    }

    public void setCarrierServiceType(String carrierServiceType) {
        this.carrierServiceType = carrierServiceType;
    }

    public ShipperBean getShipper() {
        return shipper;
    }

    public void setShipper(ShipperBean shipper) {
        this.shipper = shipper;
    }

    public String getCompleteDelivery() {
        return completeDelivery;
    }

    public void setCompleteDelivery(String completeDelivery) {
        this.completeDelivery = completeDelivery;
    }

    public String getOrgTradeOrder() {
        return orgTradeOrder;
    }

    public void setOrgTradeOrder(String orgTradeOrder) {
        this.orgTradeOrder = orgTradeOrder;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public String getPaymentDistrict() {
        return paymentDistrict;
    }

    public void setPaymentDistrict(String paymentDistrict) {
        this.paymentDistrict = paymentDistrict;
    }

    public String getErpOrder() {
        return erpOrder;
    }

    public void setErpOrder(String erpOrder) {
        this.erpOrder = erpOrder;
    }

    public List<DetailBean> getDetail() {
        return detail;
    }

    public void setDetail(List<DetailBean> detail) {
        this.detail = detail;
    }

    public List<AddedServiceBean> getAddedService() {
        return addedService;
    }

    public void setAddedService(List<AddedServiceBean> addedService) {
        this.addedService = addedService;
    }

    public static class ConsigneeBean {
        /**
         * consigneeContactTel : 13552179716
         * consigneeLocationName : 北京北京市大兴区原生墅41-3
         * consigneeCityName : 北京市
         * consigneeContactPhone : 13552179716
         * consigneeContactName : 刘素芳
         * consigneeDistrictName : 大兴区
         * consigneeCountryName : 中国
         * consigneeProvinceName : 北京
         */

        private String consigneeContactTel;
        private String consigneeLocationName;
        private String consigneeCityName;
        private String consigneeContactPhone;
        private String consigneeContactName;
        private String consigneeDistrictName;
        private String consigneeCountryName;
        private String consigneeProvinceName;

        public String getConsigneeContactTel() {
            return consigneeContactTel;
        }

        public void setConsigneeContactTel(String consigneeContactTel) {
            this.consigneeContactTel = consigneeContactTel;
        }

        public String getConsigneeLocationName() {
            return consigneeLocationName;
        }

        public void setConsigneeLocationName(String consigneeLocationName) {
            this.consigneeLocationName = consigneeLocationName;
        }

        public String getConsigneeCityName() {
            return consigneeCityName;
        }

        public void setConsigneeCityName(String consigneeCityName) {
            this.consigneeCityName = consigneeCityName;
        }

        public String getConsigneeContactPhone() {
            return consigneeContactPhone;
        }

        public void setConsigneeContactPhone(String consigneeContactPhone) {
            this.consigneeContactPhone = consigneeContactPhone;
        }

        public String getConsigneeContactName() {
            return consigneeContactName;
        }

        public void setConsigneeContactName(String consigneeContactName) {
            this.consigneeContactName = consigneeContactName;
        }

        public String getConsigneeDistrictName() {
            return consigneeDistrictName;
        }

        public void setConsigneeDistrictName(String consigneeDistrictName) {
            this.consigneeDistrictName = consigneeDistrictName;
        }

        public String getConsigneeCountryName() {
            return consigneeCountryName;
        }

        public void setConsigneeCountryName(String consigneeCountryName) {
            this.consigneeCountryName = consigneeCountryName;
        }

        public String getConsigneeProvinceName() {
            return consigneeProvinceName;
        }

        public void setConsigneeProvinceName(String consigneeProvinceName) {
            this.consigneeProvinceName = consigneeProvinceName;
        }
    }

    public  class CustomerBean {
        /**
         * customerMonthlyCard : 0276712331
         * companyCode : 0276712331
         */

        private String customerMonthlyCard;
        private String companyCode;

        public String getCustomerMonthlyCard() {
            return customerMonthlyCard;
        }

        public void setCustomerMonthlyCard(String customerMonthlyCard) {
            this.customerMonthlyCard = customerMonthlyCard;
        }

        public String getCompanyCode() {
            return companyCode;
        }

        public void setCompanyCode(String companyCode) {
            this.companyCode = companyCode;
        }
    }

    public  class ShipperBean {
    }

    public  class DetailBean {
        /**
         * isBom : N
         * skuName : 羊腿礼盒
         * skuNo : 10000011
         * packageUnitCode : EA
         * quantity : 1
         * erpOrderLineNum : 1
         */

        private String isBom;
        private String skuName;
        private String skuNo;
        private String packageUnitCode;
        private String quantity;
        private int erpOrderLineNum;

        public String getIsBom() {
            return isBom;
        }

        public void setIsBom(String isBom) {
            this.isBom = isBom;
        }

        public String getSkuName() {
            return skuName;
        }

        public void setSkuName(String skuName) {
            this.skuName = skuName;
        }

        public String getSkuNo() {
            return skuNo;
        }

        public void setSkuNo(String skuNo) {
            this.skuNo = skuNo;
        }

        public String getPackageUnitCode() {
            return packageUnitCode;
        }

        public void setPackageUnitCode(String packageUnitCode) {
            this.packageUnitCode = packageUnitCode;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public int getErpOrderLineNum() {
            return erpOrderLineNum;
        }

        public void setErpOrderLineNum(int erpOrderLineNum) {
            this.erpOrderLineNum = erpOrderLineNum;
        }
    }

    public  class AddedServiceBean {
       private  String serviceCode;
       private String  value;
    }
}
