package com.insigma.ordercenter.logistics.sf.qiao;

import com.educiot.common.base.BaseVO;
import lombok.Data;

import java.util.List;

@Data
public class QuerySFRouteData extends BaseVO {
    private List<RouteResp> routeResps;
}
