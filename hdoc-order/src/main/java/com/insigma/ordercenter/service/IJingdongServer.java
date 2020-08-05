package com.insigma.ordercenter.service;

import com.jd.open.api.sdk.request.ECLP.*;
import com.jd.open.api.sdk.request.etms.LdopReceiveTraceGetRequest;
import com.jd.open.api.sdk.response.ECLP.*;
import com.jd.open.api.sdk.response.etms.LdopReceiveTraceGetResponse;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/8/3 14:41
 */
public interface IJingdongServer {

    /**
     *  添加主商品信息---用于新商品同步时使用。
     * @param goodsInfoRequest
     * @return
     */
    EclpGoodsTransportGoodsInfoResponse transportGoodsInfo(EclpGoodsTransportGoodsInfoRequest goodsInfoRequest);

    /**
     * 查询事业部下商品资料
     * @param goodsInfoRequest
     * @return
     */
    EclpGoodsQueryGoodsInfoResponse getTransportGoodsInfo(EclpGoodsQueryGoodsInfoRequest goodsInfoRequest);

    /**
     *  添加采购入库单
     * @param addPoOrderRequest
     * @return
     */
    EclpPoAddPoOrderResponse addPoOrder(EclpPoAddPoOrderRequest addPoOrderRequest);

    /**
     * 查询采购入库单入库详情
     * @param poQueryPoOrderRequest
     * @return
     */
    EclpPoQueryPoOrderResponse queryPoOrder(EclpPoQueryPoOrderRequest poQueryPoOrderRequest);

    /**
     * 取消采购入库单
     * @param poCancalPoOrderRequest
     * @return
     */
    EclpPoCancalPoOrderResponse cancalPoOrder(EclpPoCancalPoOrderRequest poCancalPoOrderRequest);

    /**
     * 出库单下发
     * @param orderAddOrderRequest
     * @return
     */
    EclpOrderAddOrderResponse addOrder(EclpOrderAddOrderRequest orderAddOrderRequest);

    /**
     * 出库单详情查询
     * @param orderQueryOrderRequest
     * @return
     */
    EclpOrderQueryOrderResponse queryOrder(EclpOrderQueryOrderRequest orderQueryOrderRequest);

    /**
     * 出库单取消
     * @param orderCancelOrderRequest
     * @return
     */
    EclpOrderCancelOrderResponse cancelOrder(EclpOrderCancelOrderRequest orderCancelOrderRequest);

    /**
     * 查询京东快递物流跟踪信息
     * @param receiveTraceGetRequest
     * @return
     */
    LdopReceiveTraceGetResponse get(LdopReceiveTraceGetRequest receiveTraceGetRequest);
}
