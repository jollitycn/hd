package com.insigma.ordercenter.logistics.sf.qiao;

import com.educiot.common.base.BaseVO;
import lombok.Data;

@Data
public class WaybillNoInfo extends BaseVO {
    //1：母单 2 :子单 3 : 签回单
    private int waybillType;
    private String waybillNo;


}
