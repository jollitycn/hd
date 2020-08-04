package com.insigma.ordercenter.logistics.jd;

import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.request.ECLP.*;
import com.jd.open.api.sdk.request.etms.LdopReceiveTraceGetRequest;
import com.jd.open.api.sdk.response.ECLP.*;
import com.jd.open.api.sdk.response.etms.LdopReceiveTraceGetResponse;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 京东物流接口
 *
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/21 16:57
 */
 @Slf4j
public class JDUtil {


    private final static String SERVER_URL ="https://api.jd.com/routerjson";

    private final static String appKey ="A2B5AF66DEF5176F5F39239584E3DA27";

    private final static String appSecret ="186f0721281a4706a4666250af810915";

    private final static String accessToken ="7e5236a716334b1db30659cedda4667b2ytc";


    public static void getCode() {
        String s = "https://open-oauth.jd.com/oauth2/to_login?app_key=A2B5AF66DEF5176F5F39239584E3DA27&response_type" +
         "=code&redirect_uri=www.baidu.com&state=20180416&scope=snsapi_base";

        System.out.println();
    }

    public static void main(String[] args) {

        //addTransportGoodsInfo();
        //getTransportGoodsInfo();
         //addPoOrder();
      //  queryPoOrder();
       // cancalPoOrder();

        //addOrder();
       // queryOrder();
       // cancelOrder();
        get();
    }



    // 添加
    public static void addTransportGoodsInfo() {

        JdClient client=new DefaultJdClient(SERVER_URL,accessToken,appKey,appSecret);
        EclpGoodsTransportGoodsInfoRequest request=new EclpGoodsTransportGoodsInfoRequest();
        request.setDeptNo(" EBU0000000000027 ");
        request.setIsvGoodsNo(" 23145433 ");
        request.setSpGoodsNo("38578045095");
        request.setBarcodes("6970805738061,4527080573324 ");
        request.setThirdCategoryNo("15606");
        request.setGoodsName("洗衣机");
        request.setAbbreviation("悦丝spa精选海藻护手霜80g");
        request.setBrandNo("290850");
        request.setBrandName("奥克斯品牌");
        request.setManufacturer("北京奥克斯厂商");
        request.setProduceAddress("北京");
        request.setSafeDays(1);

        try {
            EclpGoodsTransportGoodsInfoResponse response = client.execute(request);
            log.info(response.getZhDesc());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 查询事业部下商品资料
    public static void getTransportGoodsInfo() {
        JdClient client=new DefaultJdClient(SERVER_URL,accessToken,appKey,appSecret);
        EclpGoodsQueryGoodsInfoRequest request=new EclpGoodsQueryGoodsInfoRequest();
        request.setDeptNo("EBU0000000000027");
        request.setIsvGoodsNos("23145433,23145432");
        request.setGoodsNos("EMG445930335,EMG445930335");
        request.setQueryType("1");
        request.setBarcodes("6970805738061,4527080573324");
        request.setPageNo(1);
        request.setPageSize(100);
        try {
            EclpGoodsQueryGoodsInfoResponse response=client.execute(request);

            log.info(response.getZhDesc());
           // System.out.println(response.getZhDesc());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 添加采购入库单
    public static void addPoOrder(){
        JdClient client=new DefaultJdClient(SERVER_URL,accessToken,appKey,appSecret);
        EclpPoAddPoOrderRequest request=new EclpPoAddPoOrderRequest();
        request.setSpPoOrderNo("2019010400001");
        request.setDeptNo("EBU0000000000124");
        request.setReferenceOrder("手工单号");
        request.setInboundRemark("备注信息");
        request.setBuyer("111");
        request.setLogicParam("111,114,333,3");
        request.setWhNo("110000008");
        request.setSupplierNo("EMS0000000000001");
        request.setSellerSaleOrder("20180923992,22912843233");
        request.setSaleOrder("ESL999923992,ESL99992843233");
        request.setNumApplication(String.valueOf(1));
        request.setGoodsStatus("1");
        try {
            EclpPoAddPoOrderResponse response = client.execute(request);
            log.info(response.getZhDesc());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 查询采购入库单入库详情
    public static void queryPoOrder() {
        JdClient client = new DefaultJdClient(SERVER_URL, accessToken, appKey, appSecret);

        EclpPoQueryPoOrderRequest request=new EclpPoQueryPoOrderRequest();
        request.setPoOrderNo("EPL4398046516113");
        try {
            EclpPoQueryPoOrderResponse response=client.execute(request);
            log.info(response.getQueryPoModelList().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 取消采购入库单
    public static void cancalPoOrder() {

        JdClient client = new DefaultJdClient(SERVER_URL, accessToken, appKey, appSecret);

        EclpPoCancalPoOrderRequest request=new EclpPoCancalPoOrderRequest();
        request.setPoOrderNo("EPL4398046516113");
        try {
            EclpPoCancalPoOrderResponse response=client.execute(request);
            log.info(response.getPoResult().getCode()+":"+response.getPoResult().getMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 出库单下发
    public static void addOrder () {

        JdClient client=new DefaultJdClient(SERVER_URL,accessToken,appKey,appSecret);
        EclpOrderAddOrderRequest request=new EclpOrderAddOrderRequest();

        request.setIsvUUID("ISVNO0***0001");
        request.setIsvSource("ISV0020***00006");
        request.setShopNo("ESP002***006997");
        request.setBdOwnerNo("01***0417");
        request.setDepartmentNo("EBU44***15786");
        request.setWarehouseNo("110***394");
        request.setShipperNo("CYS44***11256");
        request.setSalesPlatformOrderNo("60380****3419000");
        request.setSalePlatformSource("6");
        request.setSalesPlatformCreateTime(new Date());
        request.setSoType("1");
        request.setConsigneeName("刘*容");
        request.setConsigneeMobile("137****8921");
        request.setConsigneePhone("137****8921");
        request.setConsigneeEmail("xxx@jd.com");
        request.setExpectDate(new Date());
        request.setAddressProvince("广东省");
        request.setAddressCity("汕头市");
        request.setAddressCounty("金平区");
        request.setAddressTown("鮀浦");
        request.setConsigneeAddress("汕头市***路路口");
        request.setConsigneePostcode("32**23");
        request.setOrderMark("00002122401000000000110000000200004000200000002000");
        request.setQuantity("10");

        try {
            EclpOrderAddOrderResponse response=client.execute(request);
            log.info(response.getZhDesc());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void queryOrder  () {

        JdClient client=new DefaultJdClient(SERVER_URL,accessToken,appKey,appSecret);
        EclpOrderQueryOrderRequest request=new EclpOrderQueryOrderRequest();
        request.setEclpSoNo("ESL4398046641940");
        try {
            EclpOrderQueryOrderResponse response=client.execute(request);

            log.info(response.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void cancelOrder(){
        JdClient client=new DefaultJdClient(SERVER_URL,accessToken,appKey,appSecret);
        EclpOrderCancelOrderRequest request=new EclpOrderCancelOrderRequest();
        request.setEclpSoNo("ESL4398046641940");
        try {
            EclpOrderCancelOrderResponse response=client.execute(request);
            log.info(response.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void get(){
        JdClient client=new DefaultJdClient(SERVER_URL,accessToken,appKey,appSecret);
        LdopReceiveTraceGetRequest request=new LdopReceiveTraceGetRequest();
        request.setCustomerCode("test");
        request.setWaybillCode("test");
        try {
            LdopReceiveTraceGetResponse response=client.execute(request);
            log.info(response.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
