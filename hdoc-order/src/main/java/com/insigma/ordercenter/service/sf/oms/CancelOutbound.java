package com.insigma.ordercenter.service.sf.oms;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

@Data
public class CancelOutbound extends BaseVO {
    private String erpOrder;
    private String receiptId ;
    private String companyCode ;
    private String warehouserCode;

}
