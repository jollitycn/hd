package com.insigma.ordercenter.logistics.sf.qiao;

import com.educiot.common.base.BaseVO;
import lombok.Data;

import java.util.List;

@Data
public class OrderSearchRespDto extends BaseVO {
    private String orderId;
    private String originCode;
    private String destCode;
    // 筛单结果：
    //1：人工确认
    //2：可收派
    //3：不可以收派
    private String filterResult;
    private List<ExtraInfo> returnExtraInfoList;
    private List<WaybillNoInfo> waybillNoInfoList;
    private List<RouteLabelInfo> routeLabelInfo;
}
