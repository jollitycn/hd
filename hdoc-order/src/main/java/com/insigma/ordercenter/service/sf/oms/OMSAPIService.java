package com.insigma.ordercenter.service.sf.oms;


import com.alibaba.fastjson.JSON;
import com.insigma.ordercenter.entity.vo.BaseVO;
import com.insigma.ordercenter.logistics.sf.oms.AESCipher;
import com.insigma.ordercenter.logistics.sf.oms.HmacSha512CoderFactory;
import com.insigma.ordercenter.util.HttpClientUtil;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class OMSAPIService {

    //丰桥新沙箱测试顾客编码  Yg4Zf06w_sxZs3A5D
    //校验码                          3Xdk1jqeG1Xod9nUXus8Op7DNOkchTnw
    private static final String AES256_KEY = "9A94Nt5372630G6o1pF6H2786f446F61";  //此处替换为您在丰桥平台获取的顾客编码
    private static final String MACSHA_512 = "v2NlU9A59N4uI75cc6F5mDZ242S5xM72";//此处替换为您在丰桥平台获取的校验码
//    渠道来源：YNXH_NEW
//    接口token校验：YNXH_NEWAPPTOKEN
//    用户接口检验：YNXH_NEWTOKEN
//    AES256加密key：9A94Nt5372630G6o1pF6H2786f446F61
//    HMAC-SHA key：v2NlU9A59N4uI75cc6F5mDZ242S5xM72
//
public static final String CHARSET = "UTF-8";
    private static  final String API_TOKEN="YNXH_NEWAPPTOKEN";
   private static  final String USER_API="YNXH_NEWTOKEN";
    private static  final String SOURCE_CHANNEL="YNXH_NEW";
   private static  final String USER_TOKEN="YNXH_NEWTOKEN";
    //沙箱环境的地址
    public static final String CALL_URL_BOX =  "https://scs-oms2-bspwms.sit.sf-express.com:45316/index.do";

    public static void main(String[] args) throws UnsupportedEncodingException {
      //  OMSServiceCode    testService=   OMSServiceCode.TRANSPORT;
//        OMSServiceCode       testService=      OMSServiceCode.INBOUND;
      OMSServiceCode    testService=    OMSServiceCode.OUTBOUND;
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
      //  params.put("msgDigest", CallExpressServiceTools.getMsgDigest(msgData, timeStamp, CHECK_WORD));

        // System.out.println(params.get("requestID"));
        long startTime = System.currentTimeMillis();

        System.out.println("====调用请求：" + params.get("msgData"));

        // STEP.5 HTTP调用顺丰接口 [注:响应为明文]
        String url  = CALL_URL_BOX +"?source=" + params.get("source")
                //+ "&appId=" +request.getAppId()
                + "&method=" + params.get("method")
                + "&timestamp=" + params.get("timestamp")
                + "&appToken=" + params.get("appToken")
                + "&userToken=" + params.get("userToken")
                + "&v="+params.get("v");
        String result = HttpClientUtil.post(url, params);

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

        // STEP.1 业务报文urlencode
         msgData = URLEncoder.encode(msgData, "UTF-8");

        // STEP.2 业务报文加密 [注:AESCipher非线程安全]
        AESCipher aesciphe = new AESCipher(AES256_KEY.getBytes(CHARSET));
        String msgDigest = aesciphe.getEncryptedMessage(msgData);

        System.out.println("加密报文:"+msgDigest);

        // STEP.3 生成摘要
        String sourceDiges = HmacSha512CoderFactory.getHmacSha512Coder(MACSHA_512, msgDigest);

        // STEP.4 报文及摘要再次转码
         msgData = URLEncoder.encode(msgDigest, CHARSET);
         msgDigest = URLEncoder.encode(sourceDiges, CHARSET);
//        params.put("partnerID", CLIENT_CODE);  // 顾客编码 ，对应丰桥上获取的clientCode
        params.put("appID", UUID.randomUUID().toString().replace("-", ""));
        params.put("method", testService.getCode());// 接口服务码
        params.put("source", SOURCE_CHANNEL);
        params.put("sourceChannel", SOURCE_CHANNEL);
        params.put("appToken", API_TOKEN);
        params.put("v", "1.0");
        params.put("timestamp", timeStamp);
        params.put("userToken", USER_TOKEN);
        params.put("msgData", msgData);
        params.put("msgDigest",msgDigest );


        String url  = CALL_URL_BOX +"?source=" + params.get("source")
                //+ "&appId=" +request.getAppId()
                + "&method=" + params.get("method")
                + "&timestamp=" + params.get("timestamp")
                + "&appToken=" + params.get("appToken")
                + "&userToken=" + params.get("userToken")
                + "&appID=" + params.get("appID")
                + "&sourceChannel=" + params.get("sourceChannel")
                + "&v="+params.get("v");
//        appId=111111&method=transport&source=sfdemo&appToken=sfdemoapptoken&v=&userToken=sfdemotoken

        // System.out.println(params.get("requestID"));
        long startTime = System.currentTimeMillis();

        System.out.println("====调用请求：" + params.get("msgData"));
        System.out.println("====调用请求报文：" + params.get("msgDigest"));
        String result = HttpClientUtil.post(url, params);

        System.out.println("====调用丰桥的接口服务代码：" + String.valueOf(testService.getCode()) + " 接口耗时：" + String.valueOf(System.currentTimeMillis() - startTime) + "====");
        System.out.println("===调用地址 ===" + url);
//        System.out.println("===顾客编码 ===" + CLIENT_CODE);
        System.out.println("===返回结果：" + result);
        return result;
    }
}
