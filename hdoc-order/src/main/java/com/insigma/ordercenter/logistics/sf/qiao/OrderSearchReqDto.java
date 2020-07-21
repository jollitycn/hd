package com.insigma.ordercenter.logistics.sf.qiao;

import com.educiot.common.base.BaseVO;
import lombok.Data;

import java.util.List;

@Data
public class OrderSearchReqDto extends BaseVO {

    private List<CargoDetail> cargoDetails;
    private List<ContactInfo> contactInfoList;
    //查询类型：1.正向单查询，传入的orderId为
    //正向订单号，2.退货单查询，传入的
    //orderId为退货原始订单号
    private int searchType;
    private String language;
    private String orderId;  
}
