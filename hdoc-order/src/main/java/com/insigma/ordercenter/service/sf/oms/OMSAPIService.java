package com.insigma.ordercenter.service.sf.oms;


import com.alibaba.fastjson.JSON;
import com.insigma.ordercenter.entity.vo.BaseVO;
import com.insigma.ordercenter.util.HttpClientUtil;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class OMSAPIService {

    //丰桥新沙箱测试顾客编码  Yg4Zf06w_sxZs3A5D
    //校验码                          3Xdk1jqeG1Xod9nUXus8Op7DNOkchTnw
    private static final String CLIENT_CODE = "alcTaVtXvKtuOfc9ZyC8rEc8j6Hzogmg";  //此处替换为您在丰桥平台获取的顾客编码
    private static final String CHECK_WORD = "W0b4XVyNzMaO9u0QZ3KwilcVbeAv6sN6";//此处替换为您在丰桥平台获取的校验码

//
//    private static  final String AES256_KEY="alcTaVtXvKtuOfc9ZyC8rEc8j6Hzogmg";
//    private static  final String MACSHA_512="W0b4XVyNzMaO9u0QZ3KwilcVbeAv6sN6";
    //沙箱环境的地址
    public static final String CALL_URL_BOX =  "https://scs-oms2-bspwms.sit.sf-express.com:45316/index.do";

    public static void main(String[] args) throws UnsupportedEncodingException {
        OMSServiceCode    testService=   OMSServiceCode.TRANSPORT;
//        OMSServiceCode       testService=      OMSServiceCode.INBOUND;
//        OMSServiceCode    testService=    OMSServiceCode.OUTBOUND;
//        OMSServiceCode   testService=  OMSServiceCode.CANCEL_TRANSPORT;
//        OMSServiceCode   testService= OMSServiceCode.ROUTE_QUERY;
//        OMSServiceCode    testService= OMSServiceCode.QUERY_WAYBILL;
//        OMSServiceCode    testService= OMSServiceCode.CANCEL_INBOUND;
//        OMSServiceCode    testService=  OMSServiceCode. CANCEL_OUTBOUND;
//        OMSServiceCode   testService= OMSServiceCode.COMMODITY_INFO;

        query(testService);
    }

    public static APIResponse query(OMSServiceCode testService, BaseVO data) throws UnsupportedEncodingException {
        CallExpressServiceTools client = CallExpressServiceTools.getInstance();

        // set common header
        Map<String, String> params = new HashMap<String, String>();

        String timeStamp = String.valueOf(System.currentTimeMillis());
        String msgData = JSON.toJSONString(data);
        //CallExpressServiceTools.packageMsgData(testService);

//        params.put("partnerID", CLIENT_CODE);  // 顾客编码 ，对应丰桥上获取的clientCode
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
//        System.out.println("===顾客编码 ===" + CLIENT_CODE);
        System.out.println("===返回结果：" + result);
        return JSON.parseObject(result, APIResponse.class);
    }

    public static String query(OMSServiceCode testService) throws UnsupportedEncodingException {
        CallExpressServiceTools client = CallExpressServiceTools.getInstance();

        // set common header
        Map<String, String> params = new HashMap<String, String>();

        String timeStamp = String.valueOf(System.currentTimeMillis());
        String msgData = CallExpressServiceTools.packageMsgData(testService);

//        params.put("partnerID", CLIENT_CODE);  // 顾客编码 ，对应丰桥上获取的clientCode
        params.put("appID", UUID.randomUUID().toString().replace("-", ""));
        params.put("method", testService.getCode());// 接口服务码
        params.put("source", "sfdemo");
        params.put("appToken", "sfdemoapptoken");
        params.put("v", "1.0");
        params.put("timestamp", timeStamp);
        params.put("userToken", "sfdemotoken");
        params.put("msgData", msgData);
        params.put("msgDigest", CallExpressServiceTools.getMsgDigest(msgData, timeStamp, CHECK_WORD));


//        appId=111111&method=transport&source=sfdemo&appToken=sfdemoapptoken&v=&userToken=sfdemotoken

        // System.out.println(params.get("requestID"));
        long startTime = System.currentTimeMillis();

        System.out.println("====调用请求：" + params.get("msgData"));
        String result = HttpClientUtil.post(CALL_URL_BOX, params);

        System.out.println("====调用丰桥的接口服务代码：" + String.valueOf(testService.getCode()) + " 接口耗时：" + String.valueOf(System.currentTimeMillis() - startTime) + "====");
        System.out.println("===调用地址 ===" + CALL_URL_BOX);
//        System.out.println("===顾客编码 ===" + CLIENT_CODE);
        System.out.println("===返回结果：" + result);
        return result;
    }
}
