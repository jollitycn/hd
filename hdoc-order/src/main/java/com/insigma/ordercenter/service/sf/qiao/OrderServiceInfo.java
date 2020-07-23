package com.insigma.ordercenter.service.sf.qiao;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

@Data
public class OrderServiceInfo extends BaseVO {
    private String orderNo;
    private String receiverName;
    private String address;
    private String commodityName;
    private String orderNum;
    private String receiverMobile;

}
