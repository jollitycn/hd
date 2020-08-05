package com.insigma.ordercenter.service.sf.oms;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

@Data
public class CancelInbound extends BaseVO {
    private String companyCode;
    private String warehouseCode;
    private String erpOrder;
    private String sfOrderNo;
}
