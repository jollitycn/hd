//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.insigma.ordercenter.service.sf;

import com.alibaba.fastjson.JSON;
import com.insigma.ordercenter.logistics.sf.qiao.*;
import com.insigma.ordercenter.util.HttpClientUtil;
import com.insigma.ordercenter.util.VerifyCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class CallExpressServiceTools {
    private static Logger logger = LoggerFactory.getLogger(CallExpressServiceTools.class);
    private static CallExpressServiceTools tools = new CallExpressServiceTools();

    private CallExpressServiceTools() {
    }

    public static CallExpressServiceTools getInstance() {
        Class var0 = CallExpressServiceTools.class;
        synchronized (CallExpressServiceTools.class) {
            if (tools == null) {
                tools = new CallExpressServiceTools();
            }
        }

        return tools;
    }

    public static String callSfExpressServiceByCSIM(String reqURL, String reqXML, String clientCode, String checkword) {
        String result = null;
        String verifyCode = VerifyCodeUtil.md5EncryptAndBase64(reqXML + checkword);
        result = querySFAPIservice(reqURL, reqXML, verifyCode);
        return result;
    }

    public static String querySFAPIservice(String url, String xml, String verifyCode) {
        HttpClientUtil httpclient = new HttpClientUtil();
        if (url == null) {
            url = "http://bsp-oisp.sf-express.com/bsp-oisp/sfexpressService";
        }

        String result = null;

        try {
            result = httpclient.postSFAPI(url, xml, verifyCode);
            return result;
        } catch (Exception var6) {
            logger.warn(" " + var6);
            return null;
        }
    }

//    public static String packageMsgData(EspServiceCode espServiceCode) {
//        String jsonString = "";
//
//        try {
//            InputStream is = new FileInputStream(espServiceCode.getPath());
//            byte[] bs = new byte[is.available()];
//            is.read(bs);
//            jsonString = new String(bs);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        return jsonString;
//    }


    public static String packageMsgData( EspServiceCode espServiceCode) {
        String request = "";
        switch (espServiceCode) {
            case EXP_RECE_CREATE_ORDER:
                request = expReceCreateOrder();
                break;
            case EXP_RECE_SEARCH_ORDER_RESP:
                request = expReceSearchOrderResp();
                break;
            case EXP_RECE_UPDATE_ORDER:
                request = expReceUpdateOrder();
                break;
            case EXP_RECE_FILTER_ORDER_BSP:
                request = expReceFilterOrderBsp();
                break;
            case EXP_RECE_SEARCH_ROUTES:
                request = expReceSearchRoutes();
                break;
            case EXP_RECE_GET_SUB_MAILNO:
                request = expReceGetSubMailno();
                break;
            case EXP_RECE_QUERY_SFWAYBILL:
                request = expReceQuerySfwaybill();
                break;
            default:
                break;
        }

        return request;
    }

    private static String expReceCreateOrder() {
        String request = "";
        Order order = new Order();
        List<CargoDetail> cargoDetails = new ArrayList<>();
        CargoDetail cargoDetail = new CargoDetail();
        cargoDetail.setCount(2.365);
        cargoDetail.setUnit("个");
        cargoDetail.setWeight(6.1);
        cargoDetail.setAmount(100.5111);
        cargoDetail.setCurrency("HKD");
        cargoDetail.setName("护肤品1");
        cargoDetail.setSourceArea("CHN");
        cargoDetails.add(cargoDetail);
        order.setCargoDetails(cargoDetails);
        List<ContactInfo> contactInfoList = new ArrayList<>();
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setContactType(1);
        contactInfo.setCountry("CN");
        contactInfo.setTel("4006789888");
        contactInfo.setPostCode("580058");
        contactInfo.setContact("小曾");
        contactInfo.setAddress("广东省深圳市南山区软件产业基地11栋");
        contactInfoList.add(contactInfo);

        ContactInfo contactInfo1 = new ContactInfo();
        contactInfo1.setContactType(2);
        contactInfo1.setCompany("顺丰速运");
        contactInfo1.setCountry("CN");
        contactInfo1.setTel("18688806057");
        contactInfo1.setPostCode("580058");
        contactInfo1.setContact("小邱");
        contactInfo1.setAddress("广东省广州市白云区湖北大厦");
        contactInfoList.add(contactInfo1);

        order.setContactInfoList(contactInfoList);
        order.setLanguage("zh_CN");
        order.setOrderId("QIAO-20200618-003");
        order.setCustomsInfo(new CustomsInfo());
        order.setExtraInfoList(new ArrayList<>());
        order.setIsOneselfPickup(0);
        order.setMonthlyCard("7551234567");
        order.setParcelQty(1);
        order.setPayMethod(1);
        order.setTotalWeight(6.0);
        request = JSON.toJSONString(order);
        return request;
    }

    private static String expReceSearchOrderResp() {
        String request = "";
        OrderSearchReqDto dto = new OrderSearchReqDto();
        dto.setCargoDetails(new ArrayList<>());
        dto.setContactInfoList(new ArrayList<>());
        dto.setOrderId("TE201407020016");
        dto.setLanguage("zh_CN");
        dto.setSearchType(1);
        request = JSON.toJSONString(dto);
        return request;
    }

    private static String expReceUpdateOrder() {
        String request = "";
        return request;
    }

    private static String expReceFilterOrderBsp() {
        String request = "";
        OrderFilter filter = new OrderFilter();
        List<ContactInfo> contactInfoList = new ArrayList<>();
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setContactType(1);
        contactInfo.setCountry("CN");
        contactInfo.setTel("4006789888");
        contactInfo.setPostCode("580058");
        contactInfo.setContact("小曾");
        contactInfo.setAddress("创业总部基地B07二楼");
        contactInfoList.add(contactInfo);

        ContactInfo contactInfo1 = new ContactInfo();
        contactInfo1.setContactType(2);
        contactInfo1.setCompany("顺丰速运");
        contactInfo1.setCountry("CN");
        contactInfo1.setTel("18688806057");
        contactInfo1.setPostCode("580058");
        contactInfo1.setContact("小邱");
        contactInfo1.setAddress("测试订单，请不要接单");
        contactInfoList.add(contactInfo1);

        filter.setContactInfos(contactInfoList);
        filter.setFilterType(1);
        //filter.setMonthlyCard();
        filter.setOrderId("TE201407020016");
        OrderFilter[] filters = new OrderFilter[]{filter};
        request = JSON.toJSONString(filters);
        return request;
    }

    private static String expReceSearchRoutes() {
        String request = "";
        QuerySFRoute dto = new QuerySFRoute();
        //dto.setCheckPhoneNo();
        dto.setLanguage(0);
        dto.setMethodType(1);
        dto.setTrackingType(1);
        List<String> numbers = new ArrayList<>();
        numbers.add("444003077898");
        numbers.add("441003077850");
        dto.setTrackingNumber(numbers);
        //dto.setReferenceNumber();
        request = JSON.toJSONString(dto);
        return request;
    }

    private static String expReceGetSubMailno() {
        String request = "";
        return request;
    }

    private static String expReceQuerySfwaybill() {
        String request = "";
        return request;
    }


    public static String getMsgDigest(String msgData, String timeStamp, String md5Key) throws UnsupportedEncodingException {
        String msgDigest = VerifyCodeUtil.md5EncryptAndBase64(URLEncoder.encode(msgData + timeStamp + md5Key, "UTF-8"));
        return msgDigest;
    }
}
