package com.insigma.ordercenter.logistics.jd;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.insigma.ordercenter.base.Result;
import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.request.ECLP.*;
import com.jd.open.api.sdk.request.etms.LdopReceiveTraceGetRequest;
import com.jd.open.api.sdk.response.ECLP.*;
import com.jd.open.api.sdk.response.etms.LdopReceiveTraceGetResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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

    private final static String accessToken ="83e038410de948578f69a18f94a5c861nlmd";


    public static void getCode() {
        String s = "https://open-oauth.jd.com/oauth2/to_login?app_key=A2B5AF66DEF5176F5F39239584E3DA27&response_type" +
         "=code&redirect_uri=www.baidu.com&state=20180416&scope=snsapi_base";

        System.out.println();
    }

    public static void main(String[] args) throws Exception {



       // addTransportGoodsInfo();
       // getTransportGoodsInfo();
         addPoOrder();
      //  queryPoOrder();
        //cancalPoOrder();

        //addOrder();
       // queryOrder();
       // cancelOrder();
      //  get();
    }

    public static void url() throws Exception{

            String url = "https://open-oauth.jd.com/oauth2/refresh_token?app_key=A2B5AF66DEF5176F5F39239584E3DA27&app_secret=186f0721281a4706a4666250af810915&grant_type=refresh_token&refresh_token=916a10aa68764931ba01773a781ec35bote2";
    URL uri = new URL(url);

        URLConnection conn = uri.openConnection();
        // 设置通用的请求属性
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("Accept-Charset","utf-8");
        // 发送POST请求必须设置如下两行
        conn.setDoOutput(true);
        conn.setDoInput(true);
        // 获取URLConnection对象对应的输出流
        PrintWriter  out = new PrintWriter(conn.getOutputStream());
        // 发送请求参数
        //out.print(param);
        // flush输出流的缓冲
        out.flush();
        // 定义BufferedReader输入流来读取URL的响应
        BufferedReader  in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "utf-8"));

        String s = in.readLine();

        System.out.println(s);
    }
    // 添加
    public static void addTransportGoodsInfo() {

        JdClient client=new DefaultJdClient(SERVER_URL,accessToken,appKey,appSecret);
        EclpGoodsTransportGoodsInfoRequest request=new EclpGoodsTransportGoodsInfoRequest();
        request.setDeptNo("EBU4418046549450");
        request.setIsvGoodsNo("ceshi00010");
        request.setSpGoodsNo("");
        request.setBarcodes("6900000000000010");
        request.setThirdCategoryNo("655");
        request.setGoodsName("接口测试下发商品");
        request.setAbbreviation("悦丝spa精选海藻  护手霜80g");
        request.setBrandNo("290850");
        request.setBrandName("奥克斯品牌");
        request.setManufacturer("北京奥克斯厂商");
        request.setProduceAddress("北京");
        request.setSafeDays(1);

        try {
            EclpGoodsTransportGoodsInfoResponse response = client.execute(request);
            log.info(response.getGoodsNo());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 查询事业部下商品资料
    public static void getTransportGoodsInfo() {
        JdClient client=new DefaultJdClient(SERVER_URL,accessToken,appKey,appSecret);
        EclpGoodsQueryGoodsInfoRequest request=new EclpGoodsQueryGoodsInfoRequest();
        request.setDeptNo("EBU4418046549450");
        request.setIsvGoodsNos("");
        request.setGoodsNos("EMG4418100996085");
        request.setQueryType("1");
        request.setBarcodes("");
        request.setPageNo(1);
        request.setPageSize(100);
        try {
            EclpGoodsQueryGoodsInfoResponse response=client.execute(request);

            log.info(response.getMsg());
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
        request.setDeptNo("EBU4418046549450");
        request.setReferenceOrder("手工单号");
        request.setInboundRemark("备注信息");
        request.setBuyer("111");
        request.setLogicParam("111,114,333,3");
        request.setWhNo("110011046");
        request.setSupplierNo("EMS4418047435805");
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
            System.out.println(Result.success(response.getPoResult()));
            log.info(response.getPoResult().getCode()+":"+response.getPoResult().getMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 出库单下发
    public static void addOrder () {

        JdClient client=new DefaultJdClient(SERVER_URL,accessToken,appKey,appSecret);
        EclpOrderAddOrderRequest request=new EclpOrderAddOrderRequest();

        request.setIsvUUID("1111111");
        request.setIsvSource("ISV0020000000068");
        request.setShopNo("ESP0020000035310");
        request.setBdOwnerNo("");
        request.setDepartmentNo("EBU4418046549450");
        request.setWarehouseNo("110011046");
        request.setShipperNo("CYS0000010");
        request.setSalesPlatformOrderNo("");
        request.setSalePlatformSource("6");
        request.setSalesPlatformCreateTime(new Date());
        request.setSoType("1");
        request.setConsigneeName("测试");
        request.setConsigneeMobile("18825162021");
        request.setConsigneePhone("");
        request.setConsigneeEmail("");
        request.setExpectDate(new Date());
        request.setAddressProvince("");
        request.setAddressCity("");
        request.setAddressCounty("");
        request.setAddressTown("");
        request.setConsigneeAddress("广东省广州市增城区海伦堡花园23座403");
        request.setConsigneePostcode("");
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
