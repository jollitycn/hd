//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.insigma.ordercenter.service.sf.oms;

public enum OMSServiceCode {
    TRANSPORT("transport", "冷运运输订单接收"),
    INBOUND("inbound", "入库单接收"),
    OUTBOUND("outbound", "出库单接收"),
    OUTBOUND_CONFIRM("outboundconfirm", "出库单确认"),
    OUTBOUND_DETAIL_QUERY("outbounddetailquery", "出库单明细查询"),

    CANCEL_TRANSPORT("cancel_transport", "冷运运输订单取消"),
    ROUTE_QUERY("routequery", "路由查询"),
   INBOUND_QUERY("inboundquery", "入库查询"),
    QUERY_WAYBILL("query_waybill", "冷运运输信息查询"),
    CANCEL_INBOUND("inboundcancel", "取消入库单"),
    CANCEL_OUTBOUND("outboundcancel","取消出库单"),
    COMMODITY_INFO("commodity_info","商品信息");




    private String code;
    private String path;

    private OMSServiceCode(String code, String path) {
        this.code = code;
        this.path = path;
    }

    public String getCode() {
        return this.code;
    }

    public String getPath() {
        return this.path;
    }
}
