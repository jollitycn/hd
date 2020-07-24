//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.insigma.ordercenter.service.sf.oms;

import com.alibaba.fastjson.JSON;
import com.insigma.ordercenter.logistics.sf.qiao.*;
import com.insigma.ordercenter.service.sf.qiao.EspServiceCode;
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
            url = OMSAPIService.CALL_URL_BOX;
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


    public static String packageMsgData( OMSServiceCode espServiceCode) {

        String request = "";
        switch (espServiceCode) {
            case TRANSPORT:
                request = expReceCreateOrder();
                break;
            case INBOUND:
                request = expReceSearchOrderResp();
                break;
            case OUTBOUND:
                request = expReceUpdateOrder();
                break;
            case CANCEL_TRANSPORT:
                request = expReceFilterOrderBsp();
                break;
            case ROUTE_QUERY:
                request = expReceSearchRoutes();
                break;
            case QUERY_WAYBILL:
                request = expReceGetSubMailno();
                break;
            case CANCEL_INBOUND:
                request = expReceQuerySfwaybill();
                break;
            case CANCEL_OUTBOUND:
                request = expReceQuerySfwaybill();
                break;
            case COMMODITY_INFO:
                request = expReceQuerySfwaybill();
                break;
            default:
                break;
        }

        return request;
    }

    private static String expReceCreateOrder() {
        String request = "{\r\n" +
                "	\"orderItems\": [{\r\n" +
                "		\"monthlyAccount\": \"7550612539\",\r\n" +
                "		\"temperatureLevelName\": \"0至10\",\r\n" +
                "		\"remark\": \"这是备注\",\r\n" +
                "		\"skuName\": \"维生素C咀嚼片\",\r\n" +
                "		\"quantity\": \"50\",\r\n" +
                "		\"grossWeight\": \"12\",\r\n" +
                "		\"volume\": \"12\"\r\n" +
                "	}],\r\n" +
                "	\"erpOrder\": \"1233\",\r\n" +
                "	\"monthlyAccount\": \"7550612539\",\r\n" +
                "	\"consigneeProvinceName\": \"广东省\",\r\n" +
                "	\"paymentTypeCode\": \"PR_ACCOUNT\",\r\n" +
                "	\"shipperLocationName\": \"宝安M17大厦A栋07\",\r\n" +
                "	\"shipperProvinceName\": \"广东省\",\r\n" +
                "	\"shipperContactName\": \"奥特曼\",\r\n" +
                "	\"shipperCityName\": \"深圳市\",\r\n" +
                "	\"consigneeLocationName\": \"广东省深圳市南山区深圳南山深南大道58号\",\r\n" +
                "	\"extenSystemOrderNo\": \"A00000002\",\r\n" +
                "	\"shipperName\": \"M17星制药\",\r\n" +
                "	\"consigneeCityName\": \"深圳市\",\r\n" +
                "	\"remark\": \"这是备注\",\r\n" +
                "	\"consigneeName\": \"顺丰物流公司\",\r\n" +
                "	\"consigneeContactName\": \"李生\",\r\n" +
                "	\"multiReceiveAddress\": \"0\",\r\n" +
                "	\"consigneeContactTel\": \"13924222888\",\r\n" +
                "	\"consigneeDistrictName\": \"宝安区\",\r\n" +
                "	\"shipperContactTel\": \"13700000002\",\r\n" +
                "	\"shipperDistrictName\": \"福田区\",\r\n" +
                "	\"productCode\": \"SE0059\",\r\n" +
                "	\"temperatureLevelCode\": \"5\",\r\n" +
                "	\"sourceCode\": \"demo-sysrem\",\r\n" +
                "	\"sourceChannel\": \"demo-sysrem\",\r\n" +
                "	\"orderTime\": \"2018-01-01 12:12:12\",\r\n" +
                "	\"emergentFlag\": \"1\",\r\n" +
                "	\"transportType\": \"LAND\",\r\n" +
                "\r\n" +
                "	\"orderServices\": [{\r\n" +
                "\r\n" +
                "			\"serviceValue\": \"\",\r\n" +
                "			\"serviceCode\": \"VA0003\"\r\n" +
                "		},\r\n" +
                "\r\n" +
                "		{\r\n" +
                "\r\n" +
                "			\"serviceValue\": \"3000\",\r\n" +
                "			\"serviceCode\": \"VA0021\"\r\n" +
                "		},\r\n" +
                "		{\r\n" +
                "\r\n" +
                "			\"serviceValue\": \"\",\r\n" +
                "			\"serviceCode\": \"VA0059\"\r\n" +
                "		},\r\n" +
                "		{\r\n" +
                "\r\n" +
                "			\"serviceValue\": \"\",\r\n" +
                "			\"serviceCode\": \"VA0058\"\r\n" +
                "		}\r\n" +
                "	]\r\n" +
                "}";
//        Order order = new Order();
//        List<CargoDetail> cargoDetails = new ArrayList<>();
//        CargoDetail cargoDetail = new CargoDetail();
//        cargoDetail.setCount(2.365);
//        cargoDetail.setUnit("个");
//        cargoDetail.setWeight(6.1);
//        cargoDetail.setAmount(100.5111);
//        cargoDetail.setCurrency("HKD");
//        cargoDetail.setName("护肤品1");
//        cargoDetail.setSourceArea("CHN");
//        cargoDetails.add(cargoDetail);
//
//        order.setCargoDetails(cargoDetails);
//        List<ContactInfo> contactInfoList = new ArrayList<>();
//        ContactInfo contactInfo = new ContactInfo();
//        contactInfo.setContactType(1);
//        contactInfo.setCountry("CN");
//        contactInfo.setTel("4006789888");
//        contactInfo.setPostCode("580058");
//        contactInfo.setContact("小曾1");
//        contactInfo.setAddress("广东省深圳市南山区软件产业基地11栋1");
//        contactInfoList.add(contactInfo);
//
//        ContactInfo contactInfo1 = new ContactInfo();
//        contactInfo1.setContactType(2);
//        contactInfo1.setCompany("顺丰速运");
//        contactInfo1.setCountry("CN");
//        contactInfo1.setTel("18688806057");
//        contactInfo1.setPostCode("580058");
//        contactInfo1.setContact("小邱1");
//        contactInfo1.setAddress("广东省广州市白云区湖北大厦1");
//        contactInfoList.add(contactInfo1);
//
//        order.setContactInfoList(contactInfoList);
//        order.setLanguage("zh_CN");
//        order.setOrderId("QIAO-20200618-004");
//        order.setCustomsInfo(new CustomsInfo());
//        order.setExtraInfoList(new ArrayList<>());
//        order.setIsOneselfPickup(0);
//        order.setExpressTypeId(1);
//        order.setMonthlyCard("7551234567");
//        order.setParcelQty(1);
//        order.setPayMethod(1);
//        order.setTotalWeight(6.0);
//        request = JSON.toJSONString(order);



        return request;
    }

    private static String expReceSearchOrderResp() {
        String request = "";
        OrderSearchReqDto dto = new OrderSearchReqDto();
        dto.setCargoDetails(new ArrayList<>());
        dto.setContactInfoList(new ArrayList<>());
        dto.setOrderId("SF7444407670710");
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
        contactInfo.setAddress("测试订单，请不要接单12");
        contactInfoList.add(contactInfo);

        ContactInfo contactInfo1 = new ContactInfo();
        contactInfo1.setContactType(2);
        contactInfo1.setCompany("顺丰速运1");
        contactInfo1.setCountry("CN");
        contactInfo1.setTel("18688806057");
        contactInfo1.setPostCode("580058");
        contactInfo1.setContact("小邱");
        contactInfo1.setAddress("测试订单，请不要接单1");
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

    //===返回结果：{"apiErrorMsg":"","apiResponseID":"000173744CA0EE3FEFF1EEFC90D1E13F","apiResultCode":"A1000","apiResultData":"{\"success\":true,\"errorCode\":\"S0000\",\"errorMsg\":null,\"msgData\":{\"orderId\":\"QIAO-20200618-004\",\"originCode\":\"755\",\"destCode\":\"020\",\"filterResult\":2,\"remark\":\"\",\"url\":null,\"paymentLink\":null,\"isUpstairs\":null,\"isSpecialWarehouseService\":null,\"mappingMark\":null,\"agentMailno\":null,\"returnExtraInfoList\":null,\"waybillNoInfoList\":[{\"waybillType\":1,\"waybillNo\":\"SF7444407670710\"}],\"routeLabelInfo\":[{\"code\":\"1000\",\"routeLabelData\":{\"waybillNo\":\"SF7444407670710\",\"sourceTransferCode\":\"\",\"sourceCityCode\":\"755\",\"sourceDeptCode\":\"755\",\"sourceTeamCode\":\"\",\"destCityCode\":\"020\",\"destDeptCode\":\"020\",\"destDeptCodeMapping\":\"\",\"destTeamCode\":\"\",\"destTeamCodeMapping\":\"\",\"destTransferCode\":\"020\",\"destRouteLabel\":\"020\",\"proName\":\"顺丰标快\",\"cargoTypeCode\":\"C201\",\"limitTypeCode\":\"T4\",\"expressTypeCode\":\"B1\",\"codingMapping\":\"\",\"codingMappingOut\":\"\",\"xbFlag\":\"0\",\"printFlag\":\"000000000\",\"twoDimensionCode\":\"MMM={'k1':'020','k2':'020','k3':'','k4':'T4','k5':'SF7444407670710','k6':'','k7':'fadf0aa3'}\",\"proCode\":\"T4\",\"printIcon\":\"00000000\",\"abFlag\":\"\",\"destPortCode\":\"\",\"destCountry\":\"\",\"destPostCode\":\"\",\"goodsValueTotal\":\"\",\"currencySymbol\":\"\",\"cusBatch\":\"\",\"goodsNumber\":\"\",\"errMsg\":\"\",\"checkCode\":\"fadf0aa3\",\"proIcon\":\"\",\"fileIcon\":\"\",\"fbaIcon\":\"\",\"icsmIcon\":\"\",\"destGisDeptCode\":\"020\",\"newIcon\":null},\"message\":\"SF7444407670710:\"}],\"contactInfoList\":null}}"}
}
