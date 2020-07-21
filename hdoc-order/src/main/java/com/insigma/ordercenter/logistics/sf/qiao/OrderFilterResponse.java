package com.insigma.ordercenter.logistics.sf.qiao;

import com.educiot.common.base.BaseVO;
import lombok.Data;

@Data
public class OrderFilterResponse extends BaseVO {

    private boolean success;
    private int errorCode;
    private String errorMsg;
    private OrderFilterData msgData;

}
