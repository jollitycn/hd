package com.insigma.ordercenter.logistics.sf.qiao;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

import java.util.List;

@Data
public class RouteResp  extends BaseVO {
    //顺丰运单号
    private String mailNo;
    //路由信息（列表）
    private List<Route> routes;
}
