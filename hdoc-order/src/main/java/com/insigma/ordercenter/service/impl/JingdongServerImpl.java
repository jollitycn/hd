package com.insigma.ordercenter.service.impl;

//import com.insigma.ordercenter.service.IJingdongServer;
//import com.jd.open.api.sdk.DefaultJdClient;
//import com.jd.open.api.sdk.JdClient;
//import com.jd.open.api.sdk.request.ECLP.*;
//import com.jd.open.api.sdk.request.etms.LdopReceiveTraceGetRequest;
//import com.jd.open.api.sdk.response.ECLP.*;
//import com.jd.open.api.sdk.response.etms.LdopReceiveTraceGetResponse;
import com.insigma.ordercenter.service.IJingdongServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/8/3 14:42
 */
@Slf4j
@Service
public class JingdongServerImpl implements IJingdongServer {

//    private final static String SERVER_URL ="https://api.jd.com/routerjson";
//
//    private final static String appKey ="A2B5AF66DEF5176F5F39239584E3DA27";
//
//    private final static String appSecret ="186f0721281a4706a4666250af810915";
//
//    private final static String accessToken ="7e5236a716334b1db30659cedda4667b2ytc";
//
//    @Override
//    public EclpGoodsTransportGoodsInfoResponse transportGoodsInfo(EclpGoodsTransportGoodsInfoRequest goodsInfoRequest) {
//
//        JdClient client=new DefaultJdClient(SERVER_URL,accessToken,appKey,appSecret);
//        try {
//            EclpGoodsTransportGoodsInfoResponse response=client.execute(goodsInfoRequest);
//            return response;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    @Override
//    public EclpGoodsQueryGoodsInfoResponse getTransportGoodsInfo(EclpGoodsQueryGoodsInfoRequest goodsInfoRequest) {
//
//        JdClient client=new DefaultJdClient(SERVER_URL,accessToken,appKey,appSecret);
//        try {
//            EclpGoodsQueryGoodsInfoResponse response=client.execute(goodsInfoRequest);
//            return response;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public EclpPoAddPoOrderResponse addPoOrder(EclpPoAddPoOrderRequest addPoOrderRequest) {
//
//        JdClient client=new DefaultJdClient(SERVER_URL,accessToken,appKey,appSecret);
//        try {
//            EclpPoAddPoOrderResponse response=client.execute(addPoOrderRequest);
//            return response;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public EclpPoQueryPoOrderResponse queryPoOrder(EclpPoQueryPoOrderRequest poQueryPoOrderRequest) {
//
//        JdClient client=new DefaultJdClient(SERVER_URL,accessToken,appKey,appSecret);
//        try {
//            EclpPoQueryPoOrderResponse response=client.execute(poQueryPoOrderRequest);
//            return response;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public EclpPoCancalPoOrderResponse cancalPoOrder(EclpPoCancalPoOrderRequest poCancalPoOrderRequest) {
//
//        JdClient client=new DefaultJdClient(SERVER_URL,accessToken,appKey,appSecret);
//        try {
//            EclpPoCancalPoOrderResponse response=client.execute(poCancalPoOrderRequest);
//            return response;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    @Override
//    public EclpOrderAddOrderResponse addOrder(EclpOrderAddOrderRequest orderAddOrderRequest) {
//
//        JdClient client=new DefaultJdClient(SERVER_URL,accessToken,appKey,appSecret);
//        try {
//            EclpOrderAddOrderResponse response=client.execute(orderAddOrderRequest);
//            return response;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public EclpOrderQueryOrderResponse queryOrder(EclpOrderQueryOrderRequest orderQueryOrderRequest) {
//
//        JdClient client=new DefaultJdClient(SERVER_URL,accessToken,appKey,appSecret);
//        try {
//            EclpOrderQueryOrderResponse response=client.execute(orderQueryOrderRequest);
//            return response;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public EclpOrderCancelOrderResponse cancelOrder(EclpOrderCancelOrderRequest orderCancelOrderRequest) {
//        JdClient client=new DefaultJdClient(SERVER_URL,accessToken,appKey,appSecret);
//        try {
//            EclpOrderCancelOrderResponse response=client.execute(orderCancelOrderRequest);
//            return response;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public LdopReceiveTraceGetResponse get(LdopReceiveTraceGetRequest receiveTraceGetRequest) {
//        JdClient client=new DefaultJdClient(SERVER_URL,accessToken,appKey,appSecret);
//        try {
//            LdopReceiveTraceGetResponse response=client.execute(receiveTraceGetRequest);
//            return response;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
