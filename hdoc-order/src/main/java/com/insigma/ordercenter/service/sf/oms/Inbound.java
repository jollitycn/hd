package com.insigma.ordercenter.service.sf.oms;

import com.insigma.ordercenter.entity.vo.BaseVO;

import java.util.List;


public class Inbound extends BaseVO {
    /**
     * supplierCode : CARREFOUR
     * warehouseCode : P024CSB
     * sfOrderType : PI
     * licensePlateNumber : 221
     * orderTime : 2018-09-20 15:50:50
     * distributionType : Y
     * tradePlatform : JD
     * erpOrder : 11000853401256
     * userDef1 :
     * item : [{"lotatt02":"2018-09-20 15:50:50","qtyUm":"EA","lotatt03":"2018-09-20 15:50:50","lotatt01":"2018-09-20 15:50:50","usetItemDef4":"","usetItemDef3":"","usetItemDef2":"","inventoryStatus":"10","lotatt05":"1234","usetItemDef1":"","remark":"","erpOrderLineNum":"123123","lot":"123","expirationTime":6,"price":11,"qty":"2616","skuNo":"LF15050041001","usetItemDef8":"","usetItemDef7":"","usetItemDef6":"","usetItemDef5":""}]
     * tradeOrder : 111
     * originalNo :
     * userDef8 :
     * userDef6 :
     * requirement :
     * userDef7 :
     * userDef4 :
     * userDef5 :
     * userDef2 :
     * buyer :
     * driverCalls :
     * erpOrderType : 10
     * userDef3 :
     * buyerPhone :
     * driver :
     * expectDate : 2018-09-20 15:50:50
     * customer : {"companyCode":"7550057640","customerMonthlyCard":"7550057640"}
     */

    private String supplierCode;
    private String warehouseCode;
    private String sfOrderType;
    private String licensePlateNumber;
    private String orderTime;
    private String distributionType;
    private String tradePlatform;
    private String erpOrder;
    private String userDef1;
    private String tradeOrder;
    private String originalNo;
    private String userDef8;
    private String userDef6;
    private String requirement;
    private String userDef7;
    private String userDef4;
    private String userDef5;
    private String userDef2;
    private String buyer;
    private String driverCalls;
    private String erpOrderType;
    private String userDef3;
    private String buyerPhone;
    private String driver;
    private String expectDate;
    private CustomerBean customer;
    private List<ItemBean> item;

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getSfOrderType() {
        return sfOrderType;
    }

    public void setSfOrderType(String sfOrderType) {
        this.sfOrderType = sfOrderType;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getDistributionType() {
        return distributionType;
    }

    public void setDistributionType(String distributionType) {
        this.distributionType = distributionType;
    }

    public String getTradePlatform() {
        return tradePlatform;
    }

    public void setTradePlatform(String tradePlatform) {
        this.tradePlatform = tradePlatform;
    }

    public String getErpOrder() {
        return erpOrder;
    }

    public void setErpOrder(String erpOrder) {
        this.erpOrder = erpOrder;
    }

    public String getUserDef1() {
        return userDef1;
    }

    public void setUserDef1(String userDef1) {
        this.userDef1 = userDef1;
    }

    public String getTradeOrder() {
        return tradeOrder;
    }

    public void setTradeOrder(String tradeOrder) {
        this.tradeOrder = tradeOrder;
    }

    public String getOriginalNo() {
        return originalNo;
    }

    public void setOriginalNo(String originalNo) {
        this.originalNo = originalNo;
    }

    public String getUserDef8() {
        return userDef8;
    }

    public void setUserDef8(String userDef8) {
        this.userDef8 = userDef8;
    }

    public String getUserDef6() {
        return userDef6;
    }

    public void setUserDef6(String userDef6) {
        this.userDef6 = userDef6;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getUserDef7() {
        return userDef7;
    }

    public void setUserDef7(String userDef7) {
        this.userDef7 = userDef7;
    }

    public String getUserDef4() {
        return userDef4;
    }

    public void setUserDef4(String userDef4) {
        this.userDef4 = userDef4;
    }

    public String getUserDef5() {
        return userDef5;
    }

    public void setUserDef5(String userDef5) {
        this.userDef5 = userDef5;
    }

    public String getUserDef2() {
        return userDef2;
    }

    public void setUserDef2(String userDef2) {
        this.userDef2 = userDef2;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getDriverCalls() {
        return driverCalls;
    }

    public void setDriverCalls(String driverCalls) {
        this.driverCalls = driverCalls;
    }

    public String getErpOrderType() {
        return erpOrderType;
    }

    public void setErpOrderType(String erpOrderType) {
        this.erpOrderType = erpOrderType;
    }

    public String getUserDef3() {
        return userDef3;
    }

    public void setUserDef3(String userDef3) {
        this.userDef3 = userDef3;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getExpectDate() {
        return expectDate;
    }

    public void setExpectDate(String expectDate) {
        this.expectDate = expectDate;
    }

    public CustomerBean getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerBean customer) {
        this.customer = customer;
    }

    public List<ItemBean> getItem() {
        return item;
    }

    public void setItem(List<ItemBean> item) {
        this.item = item;
    }

    public static class CustomerBean {
        /**
         * companyCode : 7550057640
         * customerMonthlyCard : 7550057640
         */

        private String companyCode;
        private String customerMonthlyCard;

        public String getCompanyCode() {
            return companyCode;
        }

        public void setCompanyCode(String companyCode) {
            this.companyCode = companyCode;
        }

        public String getCustomerMonthlyCard() {
            return customerMonthlyCard;
        }

        public void setCustomerMonthlyCard(String customerMonthlyCard) {
            this.customerMonthlyCard = customerMonthlyCard;
        }
    }

    public static class ItemBean {
        /**
         * lotatt02 : 2018-09-20 15:50:50
         * qtyUm : EA
         * lotatt03 : 2018-09-20 15:50:50
         * lotatt01 : 2018-09-20 15:50:50
         * usetItemDef4 :
         * usetItemDef3 :
         * usetItemDef2 :
         * inventoryStatus : 10
         * lotatt05 : 1234
         * usetItemDef1 :
         * remark :
         * erpOrderLineNum : 123123
         * lot : 123
         * expirationTime : 6
         * price : 11
         * qty : 2616
         * skuNo : LF15050041001
         * usetItemDef8 :
         * usetItemDef7 :
         * usetItemDef6 :
         * usetItemDef5 :
         */

        private String lotatt02;
        private String qtyUm;
        private String lotatt03;
        private String lotatt01;
        private String usetItemDef4;
        private String usetItemDef3;
        private String usetItemDef2;
        private String inventoryStatus;
        private String lotatt05;
        private String usetItemDef1;
        private String remark;
        private String erpOrderLineNum;
        private String lot;
        private int expirationTime;
        private int price;
        private String qty;
        private String skuNo;
        private String usetItemDef8;
        private String usetItemDef7;
        private String usetItemDef6;
        private String usetItemDef5;

        public String getLotatt02() {
            return lotatt02;
        }

        public void setLotatt02(String lotatt02) {
            this.lotatt02 = lotatt02;
        }

        public String getQtyUm() {
            return qtyUm;
        }

        public void setQtyUm(String qtyUm) {
            this.qtyUm = qtyUm;
        }

        public String getLotatt03() {
            return lotatt03;
        }

        public void setLotatt03(String lotatt03) {
            this.lotatt03 = lotatt03;
        }

        public String getLotatt01() {
            return lotatt01;
        }

        public void setLotatt01(String lotatt01) {
            this.lotatt01 = lotatt01;
        }

        public String getUsetItemDef4() {
            return usetItemDef4;
        }

        public void setUsetItemDef4(String usetItemDef4) {
            this.usetItemDef4 = usetItemDef4;
        }

        public String getUsetItemDef3() {
            return usetItemDef3;
        }

        public void setUsetItemDef3(String usetItemDef3) {
            this.usetItemDef3 = usetItemDef3;
        }

        public String getUsetItemDef2() {
            return usetItemDef2;
        }

        public void setUsetItemDef2(String usetItemDef2) {
            this.usetItemDef2 = usetItemDef2;
        }

        public String getInventoryStatus() {
            return inventoryStatus;
        }

        public void setInventoryStatus(String inventoryStatus) {
            this.inventoryStatus = inventoryStatus;
        }

        public String getLotatt05() {
            return lotatt05;
        }

        public void setLotatt05(String lotatt05) {
            this.lotatt05 = lotatt05;
        }

        public String getUsetItemDef1() {
            return usetItemDef1;
        }

        public void setUsetItemDef1(String usetItemDef1) {
            this.usetItemDef1 = usetItemDef1;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getErpOrderLineNum() {
            return erpOrderLineNum;
        }

        public void setErpOrderLineNum(String erpOrderLineNum) {
            this.erpOrderLineNum = erpOrderLineNum;
        }

        public String getLot() {
            return lot;
        }

        public void setLot(String lot) {
            this.lot = lot;
        }

        public int getExpirationTime() {
            return expirationTime;
        }

        public void setExpirationTime(int expirationTime) {
            this.expirationTime = expirationTime;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getSkuNo() {
            return skuNo;
        }

        public void setSkuNo(String skuNo) {
            this.skuNo = skuNo;
        }

        public String getUsetItemDef8() {
            return usetItemDef8;
        }

        public void setUsetItemDef8(String usetItemDef8) {
            this.usetItemDef8 = usetItemDef8;
        }

        public String getUsetItemDef7() {
            return usetItemDef7;
        }

        public void setUsetItemDef7(String usetItemDef7) {
            this.usetItemDef7 = usetItemDef7;
        }

        public String getUsetItemDef6() {
            return usetItemDef6;
        }

        public void setUsetItemDef6(String usetItemDef6) {
            this.usetItemDef6 = usetItemDef6;
        }

        public String getUsetItemDef5() {
            return usetItemDef5;
        }

        public void setUsetItemDef5(String usetItemDef5) {
            this.usetItemDef5 = usetItemDef5;
        }
    }
}
