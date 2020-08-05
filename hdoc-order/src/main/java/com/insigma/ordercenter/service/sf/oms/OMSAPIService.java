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



    public static APIResponse query(OMSServiceCode testService, BaseVO data) throws UnsupportedEncodingException {
        CallExpressServiceTools client = CallExpressServiceTools.getInstance();

        // set common header
        Map<String, String> params = new HashMap<String, String>();

        String timeStamp = String.valueOf(System.currentTimeMillis());
        String msgData = JSON.toJSONString(data);
        //CallExpressServiceTools.packageMsgData(testService);

//        params.put("partnerID", CLIENT_CODE);  // 顾客编码 ，对应丰桥上获取的clientCode
        params.put("requestID", OMSUtil.UUID());
        params.put("serviceCode", testService.getCode());// 接口服务码
        params.put("timestamp", timeStamp);
        params.put("msgData", msgData);
      //  params.put("msgDigest", CallExpressServiceTools.getMsgDigest(msgData, timeStamp, CHECK_WORD));

        // System.out.println(params.get("requestID"));
        long startTime = System.currentTimeMillis();

        System.out.println("====调用请求：" + params.get("msgData"));

        // STEP.5 HTTP调用顺丰接口 [注:响应为明文]
        String result = HttpClientUtil.post( OMSUtil.buildUrl(params), params);
        System.out.println("====调用丰桥的接口服务代码：" + String.valueOf(testService.getCode()) + " 接口耗时：" + String.valueOf(System.currentTimeMillis() - startTime) + "====");
        System.out.println("===调用地址 ===" + OMSUtil.CALL_URL_BOX);
//        System.out.println("===顾客编码 ===" + CLIENT_CODE);
        System.out.println("===返回结果：" + result);
        return JSON.parseObject(result, APIResponse.class);
    }
}
