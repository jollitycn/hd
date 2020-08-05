package com.insigma.ordercenter.service.sf.oms;

import java.util.Map;
import java.util.UUID;

public class OMSUtil {


    //丰桥新沙箱测试顾客编码  Yg4Zf06w_sxZs3A5D
    //校验码                          3Xdk1jqeG1Xod9nUXus8Op7DNOkchTnw
    public static final String AES256_KEY = "9A94Nt5372630G6o1pF6H2786f446F61";  //此处替换为您在丰桥平台获取的顾客编码
    public static final String MACSHA_512 = "v2NlU9A59N4uI75cc6F5mDZ242S5xM72";//此处替换为您在丰桥平台获取的校验码
    //    渠道来源：YNXH_NEW
//    接口token校验：YNXH_NEWAPPTOKEN
//    用户接口检验：YNXH_NEWTOKEN
//    AES256加密key：9A94Nt5372630G6o1pF6H2786f446F61
//    HMAC-SHA key：v2NlU9A59N4uI75cc6F5mDZ242S5xM72
//
    public static final String CHARSET = "UTF-8";
    public static  final String API_TOKEN="YNXH_NEWAPPTOKEN";
    public static  final String USER_API="YNXH_NEWTOKEN";
    public static  final String SOURCE_CHANNEL="YNXH_NEW";
    public static  final String USER_TOKEN="YNXH_NEWTOKEN";
    //沙箱环境的地址
    public static final String CALL_URL_BOX =  "https://scs-oms2-bspwms.sit.sf-express.com:45316/index.do";



    public static String buildUrl(Map<String, String> params) {
        return CALL_URL_BOX +"?source=" + params.get("source")
                //+ "&appId=" +request.getAppId()
                + "&method=" + params.get("method")
                + "&timestamp=" + params.get("timestamp")
                + "&appToken=" + params.get("appToken")
                + "&userToken=" + params.get("userToken")
                + "&v="+params.get("v");
    }

    public static String UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
