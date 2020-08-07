package com.insigma.ordercenter.logistics.sf.qiao;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

@Data
public class OrderFilterResponse extends BaseVO {

    private boolean success;
    private String errorCode;
    private String errorMsg;
    private OrderFilterData msgData;

}
