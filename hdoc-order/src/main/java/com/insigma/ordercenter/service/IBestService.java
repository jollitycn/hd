package com.insigma.ordercenter.service;

import com.insigma.ordercenter.logistics.best.sdk.getShippingOrderInfo.request.GetShippingOrderInfoReq;
import com.insigma.ordercenter.logistics.best.sdk.getShippingOrderInfo.response.GetShippingOrderInfoRsp;
import com.insigma.ordercenter.logistics.best.sdk.twCancelNotiry.request.TwCancelNotiryReq;
import com.insigma.ordercenter.logistics.best.sdk.twCancelNotiry.response.TwCancelNotiryRsp;
import com.insigma.ordercenter.logistics.best.sdk.twSoNotify.request.TwSoNotifyReq;
import com.insigma.ordercenter.logistics.best.sdk.twSoNotify.response.TwSoNotifyRsp;
import com.insigma.ordercenter.logistics.best.sdk.wmsSkuNotify.request.WmsSkuNotifyReq;
import com.insigma.ordercenter.logistics.best.sdk.wmsSkuNotify.response.WmsSkuNotifyRsp;

/**
 * 百世物流相关接口
 *
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/30 16:09
 */
public interface IBestService {

    /**
     * 仓配单取消接口
     * 返回：flag标识 SUCCESS-成功 FAILURE-失败（下同）
     *
     * @param req 取消的发货单信息
     * @return com.insigma.ordercenter.logistics.best.sdk.twCancelNotiry.response.TwCancelNotiryRsp
     * @author Pan Juncai
     * @date 2020/7/30 16:22
     */
    TwCancelNotiryRsp twCancelNotiry(TwCancelNotiryReq req);

    /**
     * 仓配出库单同步接口
     *
     * @param req 发货信息
     * @return com.insigma.ordercenter.logistics.best.sdk.twSoNotify.response.TwSoNotifyRsp
     * @author Pan Juncai
     * @date 2020/7/30 16:23
     */
    TwSoNotifyRsp twSoNotify(TwSoNotifyReq req);

    /**
     * 商品信息同步接口
     *
     * @param req 商品信息信息
     * @return com.insigma.ordercenter.logistics.best.sdk.wmsSkuNotify.response.WmsSkuNotifyRsp
     * @author Pan Juncai
     * @date 2020/7/30 16:23
     */
    WmsSkuNotifyRsp wmsSkuNotify(WmsSkuNotifyReq req);

    /**
     * 运单信息查询接口
     *
     * @param req 查询条件
     * @return com.insigma.ordercenter.logistics.best.sdk.getShippingOrderInfo.response.GetShippingOrderInfoRsp
     * @author Pan Juncai
     * @date 2020/7/30 16:24
     */
    GetShippingOrderInfoRsp synTraceQuery(GetShippingOrderInfoReq req);
}
