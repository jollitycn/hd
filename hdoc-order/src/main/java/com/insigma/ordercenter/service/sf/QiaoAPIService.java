package com.insigma.ordercenter.service.sf;


import com.alibaba.fastjson.JSON;
import com.educiot.common.base.BaseVO;
import com.example.express.api.sf.APIResponse;
import com.example.express.api.sf.service.EspServiceCode;
import com.example.express.util.HttpClientUtil;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class QiaoAPIService {

    //丰桥新沙箱测试顾客编码  Yg4Zf06w_sxZs3A5D
    //校验码                          3Xdk1jqeG1Xod9nUXus8Op7DNOkchTnw
    private static final String CLIENT_CODE = "YvLec24Y";  //此处替换为您在丰桥平台获取的顾客编码
    private static final String CHECK_WORD = "u1UQHYQpLhSmurqiTbVnTmIDERsMHKOf";//此处替换为您在丰桥平台获取的校验码


    //沙箱环境的地址
    private static final String CALL_URL_BOX = "https://sfapi-sbox.sf-express.com/std/service";
    //生产环境的地址
    private static final String CALL_URL_PROD = "https://sfapi.sf-express.com/std/service";

    public static void main(String[] args) throws UnsupportedEncodingException {
        //	EspServiceCode testService = EspServiceCode.EXP_RECE_CREATE_ORDER; //下订单 //参数不对
       // EspServiceCode testService = EspServiceCode.EXP_RECE_SEARCH_ORDER_RESP; //查订单 //查不到订单
        //    EspServiceCode testService = EspServiceCode.EXP_RECE_UPDATE_ORDER;//订单取消
       	com.example.express.api.sf.service.EspServiceCode testService = com.example.express.api.sf.service.EspServiceCode.EXP_RECE_FILTER_ORDER_BSP;//订单筛选
          // EspServiceCode testService = EspServiceCode.EXP_RECE_SEARCH_ROUTES;//查路由 ok
        //	EspServiceCode testService = EspServiceCode.EXP_RECE_GET_SUB_MAILNO;//子单号
        //	EspServiceCode testService = EspServiceCode.EXP_RECE_QUERY_SFWAYBILL;//查运费

        query(testService);
    }

    public static APIResponse query(com.example.express.api.sf.service.EspServiceCode testService, BaseVO data) throws UnsupportedEncodingException {
        CallExpressServiceTools client = CallExpressServiceTools.getInstance();

        // set common header
        Map<String, String> params = new HashMap<String, String>();

        String timeStamp = String.valueOf(System.currentTimeMillis());
        String msgData = JSON.toJSONString(data);
        //CallExpressServiceTools.packageMsgData(testService);

        params.put("partnerID", CLIENT_CODE);  // 顾客编码 ，对应丰桥上获取的clientCode
        params.put("requestID", UUID.randomUUID().toString().replace("-", ""));
        params.put("serviceCode", testService.getCode());// 接口服务码
        params.put("timestamp", timeStamp);
        params.put("msgData", msgData);
        params.put("msgDigest", CallExpressServiceTools.getMsgDigest(msgData, timeStamp, CHECK_WORD));

        // System.out.println(params.get("requestID"));
        long startTime = System.currentTimeMillis();

        System.out.println("====调用请求：" + params.get("msgData"));
        String result = HttpClientUtil.post(CALL_URL_BOX, params);

        System.out.println("====调用丰桥的接口服务代码：" + String.valueOf(testService.getCode()) + " 接口耗时：" + String.valueOf(System.currentTimeMillis() - startTime) + "====");
        System.out.println("===调用地址 ===" + CALL_URL_BOX);
        System.out.println("===顾客编码 ===" + CLIENT_CODE);
        System.out.println("===返回结果：" + result);
        return JSON.parseObject(result, APIResponse.class);
    }

    public static String query(com.example.express.api.sf.service.EspServiceCode testService) throws UnsupportedEncodingException {
        CallExpressServiceTools client = CallExpressServiceTools.getInstance();

        // set common header
        Map<String, String> params = new HashMap<String, String>();

        String timeStamp = String.valueOf(System.currentTimeMillis());
        String msgData = CallExpressServiceTools.packageMsgData(testService);

        params.put("partnerID", CLIENT_CODE);  // 顾客编码 ，对应丰桥上获取的clientCode
        params.put("requestID", UUID.randomUUID().toString().replace("-", ""));
        params.put("serviceCode", testService.getCode());// 接口服务码
        params.put("timestamp", timeStamp);
        params.put("msgData", msgData);
        params.put("msgDigest", CallExpressServiceTools.getMsgDigest(msgData, timeStamp, CHECK_WORD));

        // System.out.println(params.get("requestID"));
        long startTime = System.currentTimeMillis();

        System.out.println("====调用请求：" + params.get("msgData"));
        String result = HttpClientUtil.post(CALL_URL_BOX, params);

        System.out.println("====调用丰桥的接口服务代码：" + String.valueOf(testService.getCode()) + " 接口耗时：" + String.valueOf(System.currentTimeMillis() - startTime) + "====");
        System.out.println("===调用地址 ===" + CALL_URL_BOX);
        System.out.println("===顾客编码 ===" + CLIENT_CODE);
        System.out.println("===返回结果：" + result);
        return result;
    }
}
