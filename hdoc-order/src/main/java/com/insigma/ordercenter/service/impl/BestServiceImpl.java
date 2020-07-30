package com.insigma.ordercenter.service.impl;

import com.insigma.ordercenter.logistics.best.sdk.Client;
import com.insigma.ordercenter.logistics.best.sdk.getShippingOrderInfo.request.GetShippingOrderInfoReq;
import com.insigma.ordercenter.logistics.best.sdk.getShippingOrderInfo.response.GetShippingOrderInfoRsp;
import com.insigma.ordercenter.logistics.best.sdk.twCancelNotiry.request.TwCancelNotiryReq;
import com.insigma.ordercenter.logistics.best.sdk.twCancelNotiry.response.TwCancelNotiryRsp;
import com.insigma.ordercenter.logistics.best.sdk.twSoNotify.request.TwSoNotifyReq;
import com.insigma.ordercenter.logistics.best.sdk.twSoNotify.response.TwSoNotifyRsp;
import com.insigma.ordercenter.logistics.best.sdk.wmsSkuNotify.request.WmsSkuNotifyReq;
import com.insigma.ordercenter.logistics.best.sdk.wmsSkuNotify.response.WmsSkuNotifyRsp;
import com.insigma.ordercenter.service.IBestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 百世物流接口实现
 *
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/30 16:10
 */
@Slf4j
@Service
public class BestServiceImpl implements IBestService {

    @Value(value = "${best.best-url}")
    private String bestUrl;

    @Value(value = "${best.partnerID}")
    private String partnerId;

    @Value(value = "${best.partnerKey}")
    private String partnerKey;

    @Value(value = "${best.messageFormat}")
    private String messageFormat;

    @Override
    public TwCancelNotiryRsp twCancelNotiry(TwCancelNotiryReq req) {
        Client client = new Client(bestUrl, partnerId, partnerKey, messageFormat);
        TwCancelNotiryRsp executed = client.executed(req);
        log.info("TwCancelNotiryRsp = {}", executed);
        return executed;
    }

    @Override
    public TwSoNotifyRsp twSoNotify(TwSoNotifyReq req) {
        Client client = new Client(bestUrl, partnerId, partnerKey, messageFormat);
        TwSoNotifyRsp executed = client.executed(req);
        log.info("TwSoNotifyRep = {}", executed);
        return executed;
    }

    @Override
    public WmsSkuNotifyRsp wmsSkuNotify(WmsSkuNotifyReq req) {
        Client client = new Client(bestUrl, partnerId, partnerKey, messageFormat);
        WmsSkuNotifyRsp executed = client.executed(req);
        log.info("WmsSkuNotifyRsp = {}", executed);
        return executed;
    }

    @Override
    public GetShippingOrderInfoRsp synTraceQuery(GetShippingOrderInfoReq req) {
        Client client = new Client(bestUrl, partnerId, partnerKey, messageFormat);
        GetShippingOrderInfoRsp executed = client.executed(req);
        log.info("GetShippingOrderInfoRsp = {}", executed);
        return executed;
    }
}
