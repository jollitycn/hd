package com.insigma.ordercenter.logistics.sf.qiao;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

@Data
public class CustomsInfo extends BaseVO {
    private double declaredValue;
    private String declaredValueCurrency;
    private String customsBatchs;
    private int taxPayMethod;
    private String taxSettleAccounts;
    private String paymentTool;
    private String paymentNumber;
    private String orderName;
    private String orderCertType;
    private String orderCertNo;
    private String tax;
}
