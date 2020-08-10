package com.insigma.ordercenter.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.logistics.best.sdk.twCancelNotiry.request.TwCancelNotiryReq;
import com.insigma.ordercenter.logistics.best.sdk.twCancelNotiry.response.TwCancelNotiryRsp;
import com.insigma.ordercenter.logistics.sf.qiao.OrderUpdate;
import com.insigma.ordercenter.service.IBestService;
import com.insigma.ordercenter.service.IExpressCancelService;
import com.insigma.ordercenter.service.IJingdongServer;
import com.insigma.ordercenter.service.sf.qiao.APIResponse;
import com.insigma.ordercenter.service.sf.qiao.EspServiceCode;
import com.insigma.ordercenter.service.sf.qiao.QiaoAPIService;
import com.jd.open.api.sdk.request.ECLP.EclpOrderCancelOrderRequest;
import com.jd.open.api.sdk.response.ECLP.EclpOrderCancelOrderResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ExpressCancelServiceImpl implements IExpressCancelService {

    @Resource
    private IBestService bestService;

    @Resource
    private IJingdongServer jingdongServer;

    @Override
    public Result cancelLogistics(Long shippingOrderId, int logisticsType) throws Exception{
        // 1顺丰速运 2百世汇通 3宅急送 4京东
        switch (logisticsType){
            case 1:
                OrderUpdate orderUpdate=new OrderUpdate();
                APIResponse query = QiaoAPIService.query(EspServiceCode.EXP_RECE_UPDATE_ORDER, orderUpdate);
                Result.success(query);
                break;
            case 2:
                TwCancelNotiryReq twCancelNotiryReq=new TwCancelNotiryReq();
                twCancelNotiryReq.setCustomerCode("NORMAL");
                twCancelNotiryReq.setOrderCode(shippingOrderId.toString());
                TwCancelNotiryRsp twCancelNotiryRsp = bestService.twCancelNotiry(twCancelNotiryReq);
                Result.success(twCancelNotiryRsp);
                break;
            case 3:
                break;
            case 4:
                EclpOrderCancelOrderRequest orderCancelOrderRequest=new EclpOrderCancelOrderRequest();
                orderCancelOrderRequest.setEclpSoNo(shippingOrderId.toString());
                EclpOrderCancelOrderResponse eclpOrderCancelOrderResponse = jingdongServer.cancelOrder(orderCancelOrderRequest);
                Result.success(eclpOrderCancelOrderResponse);
                break;
            default:
                break;
        }
        return Result.success();
    }
}
