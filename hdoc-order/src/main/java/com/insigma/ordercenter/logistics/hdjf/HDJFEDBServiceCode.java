//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.insigma.ordercenter.logistics.hdjf;

public enum HDJFEDBServiceCode {
////    TRANSPORT("transport", "冷运运输订单接收"),
//    INBOUND("inbound", "入库单接收"),
//    OUTBOUND("outbound", "出库单接收"),
////    OUTBOUND_CONFIRM("outboundconfirm", "出库单确认"),
////    OUTBOUND_DETAIL_QUERY("outbounddetailquery", "出库单明细查询"),
//
////    CANCEL_TRANSPORT("cancel_transport", "冷运运输订单取消"),
//    ROUTE_QUERY("routequery", "路由查询"),
//   INBOUND_QUERY("inboundquery", "入库查询"),
////    QUERY_WAYBILL("query_waybill", "冷运运输信息查询"),
//    CANCEL_INBOUND("inboundcancel", "取消入库单"),
//    CANCEL_OUTBOUND("outboundcancel","取消出库单");
//    COMMODITY_INFO("commodity_info","商品信息");


//    edbReadySynDeliveries("edbReadySynDeliveries", "取消入库单"),
//    edbGetSynDeliveries("edbGetSynDeliveries", "取消入库单"),
//    edbExpressUpdate("edbExpressUpdate", "取消入库单"),
//    edbProductGet("edbProductGet", "取消入库单"),
//    edbTradeAdd("edbTradeAdd", "取消入库单"),
//    edbInStoreAdd("edbInStoreAdd", "取消入库单"),
//    edbTradeGet("edbTradeGet", "取消入库单"),
//    edbTradReturnGet("edbTradReturnGet", "取消入库单"),
//    edbInvoiceGet("edbInvoiceGet", "取消入库单"),
//    edbProductBaseInfoGet("edbProductBaseInfoGet", "取消入库单"),
//    edbTradeImportStatusUpdate("edbTradeImportStatusUpdate", "取消入库单"),
//    edbProductClassAdd("edbProductClassAdd", "取消入库单"),
//    edbProductClassGet("edbProductClassGet", "取消入库单"),
//    edbProductClassUpdate("edbProductClassUpdate", "取消入库单"),
//    edbProductDetailAdd("edbProductDetailAdd", "取消入库单"),
//    edbInStoreConfirm("edbInStoreConfirm", "取消入库单"),
//    edbTradeCancel("edbTradeCancel", "取消入库单"),
//    edbTradeUpdate("edbTradeUpdate", "取消入库单"),
//    edbLogisticsCompaniesUpdate("edbLogisticsCompaniesUpdate", "取消入库单"),
//    edbInventoryAdd("edbInventoryAdd", "取消入库单"),
//    edbProductBrandGet("edbProductBrandGet", "取消入库单"),
//    edbTradeDeliveryBatch("edbTradeDeliveryBatch", "取消入库单"),
//    edbReturnStoreAdd("edbReturnStoreAdd", "取消入库单"),
    edbProductBrandAdd("edbProductBrandAdd", "取消入库单");



    private String code;
    private String path;

    private HDJFEDBServiceCode(String code, String path) {
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
