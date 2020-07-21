package com.insigma.ordercenter.logistics.sf.qiao;

import com.educiot.common.base.BaseVO;
import lombok.Data;

import java.util.List;

@Data
public class RouteLabelInfo extends BaseVO {
    //返回调用结果，1000：调用成功；
    private String code;
    private List<RouteLabelData> routeLabelData;
    private String message;
}
