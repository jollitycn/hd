package com.insigma.ordercenter.logistics.hdjf.edb;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

@Data
public class EdbTradeGet extends BaseVO {
    private String dateType;
    private String beginTime;
    private String endTime;
    private String importMark;
    private String proceStatus;
    private String pageNo;
    private String orderStatus;
    private String pageSize;
    private String paymentStatus;
    private String productInfoType;
    private String fields;
}
