package com.insigma.ordercenter.service.sf.oms;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

@Data
public class CancelInbound extends BaseVO {
    private String companyCode;
    private String warehouseCode;
    private String erpOrder;
    private String sfOrderNo;

    public CancelInbound(String companyCode, String warehouseCode, String erpOrder, String sfOrderNo) {
        this.companyCode = companyCode;
        this.warehouseCode = warehouseCode;
        this.erpOrder = erpOrder;
        this.sfOrderNo = sfOrderNo;
    }
}
