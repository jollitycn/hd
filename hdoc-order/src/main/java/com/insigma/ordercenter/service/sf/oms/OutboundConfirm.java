package com.insigma.ordercenter.service.sf.oms;

import com.insigma.ordercenter.entity.vo.BaseVO;

import java.util.List;

public class OutboundConfirm  extends BaseVO {
    /**
     * actualShipDateTime : 1559715069000
     * carrier : CP
     * carrierProduct : SE0030
     * companyCode : SUBWAY886
     * dataStatus : 900
     * erpOrder : 3236745471812081
     * outboundContainer : [{"containerNo":"101000042296013","containerType":"0","items":[{"actualQty":1,"erpOrderLineNum":"1","inventoryStatus":"10","lot":"","lotattdesc":";;2019-04-22;;;;;N;;;;;","skuNo":"WMSTESTID3","userDef1":"","userDef11":"","userDef2":"10","userDef3":"1","userDef4":"","userDef5":"","userDef6":"","userDef7":"0","userDef8":"1","weight":2,"weightUm":"KG"}],"userDef1":"","userDef2":"","userDef3":"","userDef4":"","userDef5":"","userDef6":"","userDef7":"0","userDef8":"1","weight":2,"weightUm":"KG"}]
     * outboundDetail : [{"actualQty":1,"erpOrderLineNum":"1","skuNo":"WMSTESTID3"}]
     * returnTracking :
     * sfOrderNo : OB412459453353034489-100
     * transactionId : 203SO190605000009
     * userDef1 :
     * userDef2 : 000001308000*1,
     * userDef3 :
     * userDef4 :
     * userDef5 :
     * userDef6 :
     * userDef7 : 0
     * userDef8 : 0
     * warehouseCode : 010VB
     * wayBillNo : SF7001001199534
     */

    private long actualShipDateTime;
    private String carrier;
    private String carrierProduct;
    private String companyCode;
    private String dataStatus;
    private String erpOrder;
    private String returnTracking;
    private String sfOrderNo;
    private String transactionId;
    private String userDef1;
    private String userDef2;
    private String userDef3;
    private String userDef4;
    private String userDef5;
    private String userDef6;
    private String userDef7;
    private String userDef8;
    private String warehouseCode;
    private String wayBillNo;
    private List<OutboundContainerBean> outboundContainer;
    private List<OutboundDetailBean> outboundDetail;

    public long getActualShipDateTime() {
        return actualShipDateTime;
    }

    public void setActualShipDateTime(long actualShipDateTime) {
        this.actualShipDateTime = actualShipDateTime;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getCarrierProduct() {
        return carrierProduct;
    }

    public void setCarrierProduct(String carrierProduct) {
        this.carrierProduct = carrierProduct;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getErpOrder() {
        return erpOrder;
    }

    public void setErpOrder(String erpOrder) {
        this.erpOrder = erpOrder;
    }

    public String getReturnTracking() {
        return returnTracking;
    }

    public void setReturnTracking(String returnTracking) {
        this.returnTracking = returnTracking;
    }

    public String getSfOrderNo() {
        return sfOrderNo;
    }

    public void setSfOrderNo(String sfOrderNo) {
        this.sfOrderNo = sfOrderNo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getUserDef1() {
        return userDef1;
    }

    public void setUserDef1(String userDef1) {
        this.userDef1 = userDef1;
    }

    public String getUserDef2() {
        return userDef2;
    }

    public void setUserDef2(String userDef2) {
        this.userDef2 = userDef2;
    }

    public String getUserDef3() {
        return userDef3;
    }

    public void setUserDef3(String userDef3) {
        this.userDef3 = userDef3;
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

    public String getUserDef6() {
        return userDef6;
    }

    public void setUserDef6(String userDef6) {
        this.userDef6 = userDef6;
    }

    public String getUserDef7() {
        return userDef7;
    }

    public void setUserDef7(String userDef7) {
        this.userDef7 = userDef7;
    }

    public String getUserDef8() {
        return userDef8;
    }

    public void setUserDef8(String userDef8) {
        this.userDef8 = userDef8;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getWayBillNo() {
        return wayBillNo;
    }

    public void setWayBillNo(String wayBillNo) {
        this.wayBillNo = wayBillNo;
    }

    public List<OutboundContainerBean> getOutboundContainer() {
        return outboundContainer;
    }

    public void setOutboundContainer(List<OutboundContainerBean> outboundContainer) {
        this.outboundContainer = outboundContainer;
    }

    public List<OutboundDetailBean> getOutboundDetail() {
        return outboundDetail;
    }

    public void setOutboundDetail(List<OutboundDetailBean> outboundDetail) {
        this.outboundDetail = outboundDetail;
    }

    public static class OutboundContainerBean {
        /**
         * containerNo : 101000042296013
         * containerType : 0
         * items : [{"actualQty":1,"erpOrderLineNum":"1","inventoryStatus":"10","lot":"","lotattdesc":";;2019-04-22;;;;;N;;;;;","skuNo":"WMSTESTID3","userDef1":"","userDef11":"","userDef2":"10","userDef3":"1","userDef4":"","userDef5":"","userDef6":"","userDef7":"0","userDef8":"1","weight":2,"weightUm":"KG"}]
         * userDef1 :
         * userDef2 :
         * userDef3 :
         * userDef4 :
         * userDef5 :
         * userDef6 :
         * userDef7 : 0
         * userDef8 : 1
         * weight : 2.0
         * weightUm : KG
         */

        private String containerNo;
        private String containerType;
        private String userDef1;
        private String userDef2;
        private String userDef3;
        private String userDef4;
        private String userDef5;
        private String userDef6;
        private String userDef7;
        private String userDef8;
        private double weight;
        private String weightUm;
        private List<ItemsBean> items;

        public String getContainerNo() {
            return containerNo;
        }

        public void setContainerNo(String containerNo) {
            this.containerNo = containerNo;
        }

        public String getContainerType() {
            return containerType;
        }

        public void setContainerType(String containerType) {
            this.containerType = containerType;
        }

        public String getUserDef1() {
            return userDef1;
        }

        public void setUserDef1(String userDef1) {
            this.userDef1 = userDef1;
        }

        public String getUserDef2() {
            return userDef2;
        }

        public void setUserDef2(String userDef2) {
            this.userDef2 = userDef2;
        }

        public String getUserDef3() {
            return userDef3;
        }

        public void setUserDef3(String userDef3) {
            this.userDef3 = userDef3;
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

        public String getUserDef6() {
            return userDef6;
        }

        public void setUserDef6(String userDef6) {
            this.userDef6 = userDef6;
        }

        public String getUserDef7() {
            return userDef7;
        }

        public void setUserDef7(String userDef7) {
            this.userDef7 = userDef7;
        }

        public String getUserDef8() {
            return userDef8;
        }

        public void setUserDef8(String userDef8) {
            this.userDef8 = userDef8;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public String getWeightUm() {
            return weightUm;
        }

        public void setWeightUm(String weightUm) {
            this.weightUm = weightUm;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * actualQty : 1.0
             * erpOrderLineNum : 1
             * inventoryStatus : 10
             * lot :
             * lotattdesc : ;;2019-04-22;;;;;N;;;;;
             * skuNo : WMSTESTID3
             * userDef1 :
             * userDef11 :
             * userDef2 : 10
             * userDef3 : 1
             * userDef4 :
             * userDef5 :
             * userDef6 :
             * userDef7 : 0
             * userDef8 : 1
             * weight : 2.0
             * weightUm : KG
             */

            private double actualQty;
            private String erpOrderLineNum;
            private String inventoryStatus;
            private String lot;
            private String lotattdesc;
            private String skuNo;
            private String userDef1;
            private String userDef11;
            private String userDef2;
            private String userDef3;
            private String userDef4;
            private String userDef5;
            private String userDef6;
            private String userDef7;
            private String userDef8;
            private double weight;
            private String weightUm;

            public double getActualQty() {
                return actualQty;
            }

            public void setActualQty(double actualQty) {
                this.actualQty = actualQty;
            }

            public String getErpOrderLineNum() {
                return erpOrderLineNum;
            }

            public void setErpOrderLineNum(String erpOrderLineNum) {
                this.erpOrderLineNum = erpOrderLineNum;
            }

            public String getInventoryStatus() {
                return inventoryStatus;
            }

            public void setInventoryStatus(String inventoryStatus) {
                this.inventoryStatus = inventoryStatus;
            }

            public String getLot() {
                return lot;
            }

            public void setLot(String lot) {
                this.lot = lot;
            }

            public String getLotattdesc() {
                return lotattdesc;
            }

            public void setLotattdesc(String lotattdesc) {
                this.lotattdesc = lotattdesc;
            }

            public String getSkuNo() {
                return skuNo;
            }

            public void setSkuNo(String skuNo) {
                this.skuNo = skuNo;
            }

            public String getUserDef1() {
                return userDef1;
            }

            public void setUserDef1(String userDef1) {
                this.userDef1 = userDef1;
            }

            public String getUserDef11() {
                return userDef11;
            }

            public void setUserDef11(String userDef11) {
                this.userDef11 = userDef11;
            }

            public String getUserDef2() {
                return userDef2;
            }

            public void setUserDef2(String userDef2) {
                this.userDef2 = userDef2;
            }

            public String getUserDef3() {
                return userDef3;
            }

            public void setUserDef3(String userDef3) {
                this.userDef3 = userDef3;
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

            public String getUserDef6() {
                return userDef6;
            }

            public void setUserDef6(String userDef6) {
                this.userDef6 = userDef6;
            }

            public String getUserDef7() {
                return userDef7;
            }

            public void setUserDef7(String userDef7) {
                this.userDef7 = userDef7;
            }

            public String getUserDef8() {
                return userDef8;
            }

            public void setUserDef8(String userDef8) {
                this.userDef8 = userDef8;
            }

            public double getWeight() {
                return weight;
            }

            public void setWeight(double weight) {
                this.weight = weight;
            }

            public String getWeightUm() {
                return weightUm;
            }

            public void setWeightUm(String weightUm) {
                this.weightUm = weightUm;
            }
        }
    }

    public static class OutboundDetailBean {
        /**
         * actualQty : 1.0
         * erpOrderLineNum : 1
         * skuNo : WMSTESTID3
         */

        private double actualQty;
        private String erpOrderLineNum;
        private String skuNo;

        public double getActualQty() {
            return actualQty;
        }

        public void setActualQty(double actualQty) {
            this.actualQty = actualQty;
        }

        public String getErpOrderLineNum() {
            return erpOrderLineNum;
        }

        public void setErpOrderLineNum(String erpOrderLineNum) {
            this.erpOrderLineNum = erpOrderLineNum;
        }

        public String getSkuNo() {
            return skuNo;
        }

        public void setSkuNo(String skuNo) {
            this.skuNo = skuNo;
        }
    }
}
