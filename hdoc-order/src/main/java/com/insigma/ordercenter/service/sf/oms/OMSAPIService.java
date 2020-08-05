package com.insigma.ordercenter.service.sf.oms;


import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.insigma.ordercenter.entity.vo.BaseVO;
import com.insigma.ordercenter.logistics.sf.oms.AESCipher;
import com.insigma.ordercenter.logistics.sf.oms.HmacSha512CoderFactory;
import com.insigma.ordercenter.logistics.sf.oms.RequestBean;
import com.insigma.ordercenter.util.HttpClientUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class OMSAPIService {

    private static Logger log = LoggerFactory.getLogger(OMSAPIService.class);
    public static final int TIMEOUT = 30000;
    public static final String CHARSET = "UTF-8";
    public static final String VERSION = "1.0";
    public static final String AES256_KEY = "9A94Nt5372630G6o1pF6H2786f446F61";// key
    public static final String MACSHA_512 = "v2NlU9A59N4uI75cc6F5mDZ242S5xM72";// 盐
    //public static final String REQUES_URL = "http://scs-oms2-bspwms.sit.sf-express.com/index.do?appId=111111&method=inbound&source=jialefuapptoken&appToken=jialefuapptoken&v=1.0&timestamp=123456789&signMethod=md5&sign=223&deviceToken=444&userToken=jialefu&format=json";
    public static final String REQUES_URL = "https://scs-oms2-bspwms.sit.sf-express.com:45316/index.do";
    //public static final String REQUES_URL = "http://localhost:8080/index.do?appId=111111&method=inbound&source=jialefuapptoken&appToken=jialefuapptoken&v=1.0&timestamp=123456789&signMethod=md5&sign=223&deviceToken=444&userToken=jialefu&format=json";


    private static  final String API_TOKEN="YNXH_NEWAPPTOKEN";
    private static  final String USER_API="YNXH_NEWTOKEN";
    private static  final String SOURCE_CHANNEL="YNXH_NEW";
    private static  final String USER_TOKEN="YNXH_NEWTOKEN";

    public static APIResponse query(OMSServiceCode method, BaseVO data) throws UnsupportedEncodingException {
        String	source = JSON.toJSONString(data);//CallExpressServiceTools.packageMsgData(method);
        AESCipher cipher = new AESCipher(AES256_KEY.getBytes(CHARSET));
        String msgData = cipher.getEncryptedMessage(source);
        System.out.println(msgData);

        System.out.println(cipher.getDecryptedMessage(msgData));
        System.err.println(	AES256CipherExternalFactory.AES256Decode(msgData, AES256_KEY));
        String dataDigest = HmacSha512CoderFactory.getHmacSha512Coder(MACSHA_512, msgData);
        log.info("dataDigest:{}", dataDigest);

        String msgDataToUrlEncode = URLEncoder.encode(msgData, CHARSET);
        log.info("msgDataToUrlEncode:{}", msgDataToUrlEncode);
        String dataDigestToUrlEncode = URLEncoder.encode(dataDigest, CHARSET);
        log.info("dataDigestToUrlEncode:{}", dataDigestToUrlEncode);

        String msgDataToUrlDecode = sfmd5.decodeUrl(msgDataToUrlEncode);
        log.info("msgDataToUrlDecode:{}", msgDataToUrlDecode);
        String dataDigestToUrlDecode = sfmd5.decodeUrl(dataDigestToUrlEncode);
        log.info("dataDigestToUrlDecode:{}", dataDigestToUrlDecode);

        if (HmacSha512CoderFactory.getHmacSha512Coder(MACSHA_512, msgDataToUrlDecode).equals(dataDigestToUrlDecode)) {
            log.info("SUCCESS");

            String decodeMsgData = AES256CipherExternalFactory.AES256Decode(msgDataToUrlDecode, AES256_KEY);
            log.info("decodeMsgData:{}", decodeMsgData);
        } else {
            log.info("Error");
        }

        // STEP.5 准备参数报文
        RequestBean request = new RequestBean(new RequestBean.Request(msgDataToUrlEncode, dataDigestToUrlEncode));
        RequestBean request1 = new RequestBean(new RequestBean.Request(msgDataToUrlEncode, dataDigestToUrlEncode));
        request.setAppId("111111");
        request.setMethod(method.getCode());
//			params.put("appID", UUID.randomUUID().toString().replace("-", ""));
//			params.put("method", testService.getCode());// 接口服务码
//			params.put("source", SOURCE_CHANNEL);
//			params.put("sourceChannel", SOURCE_CHANNEL);
//			params.put("appToken", API_TOKEN);
//			params.put("v", "1.0");
//			params.put("timestamp", timeStamp);
//			params.put("userToken", USER_TOKEN);
//			params.put("msgData", msgData);
//			params.put("msgDigest",msgDigest );

        request.setSource(SOURCE_CHANNEL);
        request.setTimestamp(System.currentTimeMillis());
        request.setAppToken(API_TOKEN);
        request.setUserToken(USER_TOKEN);
        request.setV(VERSION);
        String json = new Gson().toJson(request1);

        // STEP.5 HTTP调用顺丰接口 [注:响应为明文]
        String url  = REQUES_URL +"?source=" + request.getSource()
                //+ "&appId=" +request.getAppId()
                + "&method=" + request.getMethod()
                + "&timestamp=" + request.getTimestamp()
                + "&appToken=" + request.getAppToken()
                + "&userToken=" + request.getUserToken()
                + "&v="+request.getV();
        String response = post(    url , json);

        System.out.println("远程接口:"+url);
        System.out.println("远程body:"+json);
        System.out.println("远程接口响应:"+response);

        return JSON.parseObject(response, APIResponse.class);
    }

    /**
     * HTTP -> POST
     *
     * @param url
     * @param param
     * @return
     * @throws Exception
     */
    public static String post(String url, String param) {
        String result = null;
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        try {
            httpclient = HttpClients.createDefault();

            HttpPost postmethod = new HttpPost(url);

            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(TIMEOUT).setConnectTimeout(TIMEOUT).setSocketTimeout(TIMEOUT)
                    .build();
            postmethod.setConfig(requestConfig);
            postmethod.addHeader("content-type", "application/json");
            postmethod.setEntity(new StringEntity(param, CHARSET));

            response = httpclient.execute(postmethod);

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
//
//    public static APIResponse query(OMSServiceCode testService, BaseVO data) throws UnsupportedEncodingException {
////        CallExpressServiceTools client = CallExpressServiceTools.getInstance();
//
//        // set common header
//        Map<String, String> params = new HashMap<String, String>();
//
//        String timeStamp = String.valueOf(System.currentTimeMillis());
//        String msgData = JSON.toJSONString(data);
//        //CallExpressServiceTools.packageMsgData(testService);
//
////        params.put("partnerID", CLIENT_CODE);  // 顾客编码 ，对应丰桥上获取的clientCode
//        params.put("requestID", OMSUtil.UUID());
//        params.put("serviceCode", testService.getCode());// 接口服务码
//        params.put("timestamp", timeStamp);
//        params.put("msgData", msgData);
//      //  params.put("msgDigest", CallExpressServiceTools.getMsgDigest(msgData, timeStamp, CHECK_WORD));
//
//        // System.out.println(params.get("requestID"));
//        long startTime = System.currentTimeMillis();
//
//        System.out.println("====调用请求：" + params.get("msgData"));
//
//        // STEP.5 HTTP调用顺丰接口 [注:响应为明文]
//        String result = HttpClientUtil.post( OMSUtil.buildUrl(params), params);
//        System.out.println("====调用丰桥的接口服务代码：" + String.valueOf(testService.getCode()) + " 接口耗时：" + String.valueOf(System.currentTimeMillis() - startTime) + "====");
//        System.out.println("===调用地址 ===" + OMSUtil.CALL_URL_BOX);
////        System.out.println("===顾客编码 ===" + CLIENT_CODE);
//        System.out.println("===返回结果：" + result);
//        return JSON.parseObject(result, APIResponse.class);
//    }
}
