package com.insigma.ordercenter.logistics.hdjf.yt;

public class CancelOrder {
    /**
     * logisticProviderID : YTO
     * clientID :  TEST
     * txLogisticID : LP07082300225709
     * infoType : INSTRUCTION
     * infoContent : WITHDRAW
     * remark : 商品没了
     */

    private String logisticProviderID;
    private String clientID;
    private String txLogisticID;
    private String infoType;
    private String infoContent;
    private String remark;

    public String getLogisticProviderID() {
        return logisticProviderID;
    }

    public void setLogisticProviderID(String logisticProviderID) {
        this.logisticProviderID = logisticProviderID;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getTxLogisticID() {
        return txLogisticID;
    }

    public void setTxLogisticID(String txLogisticID) {
        this.txLogisticID = txLogisticID;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
