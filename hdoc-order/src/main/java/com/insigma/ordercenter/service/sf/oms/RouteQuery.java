package com.insigma.ordercenter.service.sf.oms;


import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

@Data
public class RouteQuery extends BaseVO {

    /**
     * orderType : 1
     * waybillNo : 978301989046
     */

    private String orderType;
    private String waybillNo;
    /**
     * sfOrderNo : OB342092375611829242-100
     */

    private String sfOrderNo;
    /**
     * erpOrder : PO18101713423778
     * sourceCode : SFTEST
     */

    private String erpOrder;
    private String sourceCode;
    /**
     * companyCode : PSS2943
     */

    private String companyCode;

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }


    public String getSfOrderNo() {
        return sfOrderNo;
    }

    public void setSfOrderNo(String sfOrderNo) {
        this.sfOrderNo = sfOrderNo;
    }

    public String getErpOrder() {
        return erpOrder;
    }

    public void setErpOrder(String erpOrder) {
        this.erpOrder = erpOrder;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}

