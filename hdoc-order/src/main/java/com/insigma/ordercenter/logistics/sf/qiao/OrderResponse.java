package com.insigma.ordercenter.logistics.sf.qiao;

import com.educiot.common.base.BaseVO;
import lombok.Data;

import java.util.List;


@Data
public class OrderResponse extends BaseVO {
    private String orderId;
    private String originCode;
    private String destCode;
    //      1：人工确认 //2：可收派//3：不可以收派
    private int filterResult;
    //       条件	如果filter_result=3时为必填，
//    不可以收派的原因代码：     1：收方超范围 //2：派方超范围 //3：其它原因
    private String remark;
    private String paymentLink;
    private String isUpstairs;
    private String isSpecialWarehouseService;
    private List<Service> serviceList;
    private List<ExtraInfo> returnExtraInfoList;
    private List<WaybillNoInfo> waybillNoInfoList;
    private List<RouteLabelInfo> routeLabelInfo;
}
