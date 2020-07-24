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
            case OUTBOUND_CONFIRM:
                request = outboundConfirm();
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

    private static String outboundConfirm() {
        String request ="{\n" +
                "\"actualShipDateTime\": 1559715069000,\n" +
                "\"carrier\": \"CP\",\n" +
                "\"carrierProduct\": \"SE0030\",\n" +
                "\"companyCode\": \"SUBWAY886\",\n" +
                "\"dataStatus\": \"900\",\n" +
                "\"erpOrder\": \"3236745471812081\",\n" +
                "\"outboundContainer\": [{\n" +
                "\"containerNo\": \"101000042296013\",\n" +
                "\"containerType\": \"0\",\n" +
                "\"items\": [{\n" +
                "\"actualQty\": 1.0,\n" +
                "\"erpOrderLineNum\": \"1\",\n" +
                "\"inventoryStatus\": \"10\",\n" +
                "\"lot\": \"\",\n" +
                "\"lotattdesc\": \";;2019-04-22;;;;;N;;;;;\",\n" +
                "\"skuNo\": \"WMSTESTID3\",\n" +
                "\"userDef1\": \"\",\n" +
                "\"userDef11\": \"\",\n" +
                "\"userDef2\": \"10\",\n" +
                "\"userDef3\": \"1\",\n" +
                "\"userDef4\": \"\",\n" +
                "\"userDef5\": \"\",\n" +
                "\"userDef6\": \"\",\n" +
                "\"userDef7\": \"0\",\n" +
                "\"userDef8\": \"1\",\n" +
                "\"weight\": 2.0,\n" +
                "\"weightUm\": \"KG\"\n" +
                "}],\n" +
                "\"userDef1\": \"\",\n" +
                "\"userDef2\": \"\",\n" +
                "\"userDef3\": \"\",\n" +
                "参数名\n" +
                "类型\n" +
                "说明\n" +
                "code\n" +
                "字符(32)\n" +
                "接口状态 200 成功 其它异常\n" +
                "message\n" +
                "字符(500)\n" +
                "接口状态 200 成功 其它异常\n" +
                "返回参数说明\n" +
                "返回示例\n" +
                "备注\n" +
                "更多返回错误代码请看附录的错误代码描述\n" +
                "\"userDef4\": \"\",\n" +
                "\"userDef5\": \"\",\n" +
                "\"userDef6\": \"\",\n" +
                "\"userDef7\": \"0\",\n" +
                "\"userDef8\": \"1\",\n" +
                "\"weight\": 2.0,\n" +
                "\"weightUm\": \"KG\"\n" +
                "}],\n" +
                "\"outboundDetail\": [{\n" +
                "\"actualQty\": 1.0,\n" +
                "\"erpOrderLineNum\": \"1\",\n" +
                "\"skuNo\": \"WMSTESTID3\"\n" +
                "}],\n" +
                "\"returnTracking\": \"\",\n" +
                "\"sfOrderNo\": \"OB412459453353034489-100\",\n" +
                "\"transactionId\": \"203SO190605000009\",\n" +
                "\"userDef1\": \"\",\n" +
                "\"userDef2\": \"000001308000*1,\",\n" +
                "\"userDef3\": \"\",\n" +
                "\"userDef4\": \"\",\n" +
                "\"userDef5\": \"\",\n" +
                "\"userDef6\": \"\",\n" +
                "\"userDef7\": \"0\",\n" +
                "\"userDef8\": \"0\",\n" +
                "\"warehouseCode\": \"010VB\",\n" +
                "\"wayBillNo\": \"SF7001001199534\"\n" +
                "}";
        return request;
    }

    private static String expReceCreateOrder() {
        String request = "{\r\n" +
                "	\"orderItems\": [{\r\n" +
              //  "		\"monthlyAccount\": \"7550612539\",\r\n" +
                "		\"temperatureLevelName\": \"0至10\",\r\n" +
                "		\"remark\": \"这是备注\",\r\n" +
                "		\"skuName\": \"维生素C咀嚼片\",\r\n" +
                "		\"quantity\": \"50\",\r\n" +
                "		\"grossWeight\": \"12\",\r\n" +
                "		\"volume\": \"12\"\r\n" +
                "	}],\r\n" +
                "	\"erpOrder\": \"1233\",\r\n" +
           //     "	\"monthlyAccount\": \"7550612539\",\r\n" +
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
        String request = "{\n" +
                "\"carrierCode\": \"CP\",\n" +
                "\"carrierServiceType\": \"SE0004\",\n" +
                "\"erpOrder\": \"68121638522904\",\n" +
                "\"orderTime\": \"2018-10-09 12:01:15\",\n" +
                " \"sfOrderType\": \"PO\",\n" +
                "\"warehouseCode\": \"P024CSB\",\n" +
                "	\"sourceChannel\": \"demo-sysrem\",\r\n" +
                "\"remark\": \"订单备注：不要辣\",\n" +
                "\"paymentTypeCode\": \"PR_ACCOUNT\",\n" +
                "\"deliveryDate\": \"2018-10-15 12:01:15\",\n" +
                "\"customer\": {\n" +
                "\"companyCode\": \"7550137864\",\n" +
                "\"customerMonthlyCard\": \"7550760040\"\n" +
                "},\n" +
                "\"shipper\": {\n" +
                "\"shipperContactName\": \"李小明\",\n" +
                "\"shipperContactPhone\": \"10086\",\n" +
                "\"shipperContactTel\": \"18645787890\",\n" +
                "\"shipperContactEmail\": \"ling.lina@126.com\",\n" +
                "\"shipperCountryName\": \"中国\",\n" +
                "\"shipperCityName\": \"梅州市\",\n" +
                "\"shipperDistrictName\": \"平远县\",\n" +
                "\"shipperRegionName\": \"大柘镇\",\n" +
                "\"shipperLocationName\": \"广东省梅州市平远县大柘镇光明乳业分公司\",\n" +
                "\"shipperCompany\": \"深圳光明乳业有限公司平远分公司\"\n" +
                "},\n" +
                "\"consignee\": {\n" +
                "\"consigneeCityName\": \"深圳市\",\n" +
                "\"consigneeContactName\": \"莫怡然\",\n" +
                "\"consigneeContactPhone\": \"67627773\",\n" +
                "\"consigneeLocationName\": \"广东省深圳市龙岗区横岗街道红棉4路森城工业区A2栋\",\n" +
                "\"consigneeProvinceName\": \"广东省\",\n" +
                "\"consigneeCountryName\": \"中国\",\n" +
                "\"consigneeContactEmail\": \"shangbai@123.com\",\n" +
                "\"consigneeContactZipCode\": \"7553121251\",\n" +
                "\"consigneeContactTel\": \"1524545412222\",\n" +
                "\"consigneeDistrictName\": \"龙岗\",\n" +
                "\"consigneeRegionName\": \"龙岗区\",\n" +
                "\"consigneeCompany\": \"瑞意森城有限公司\"\n" +
                "},\n" +
                "\"addedService\": [{\n" +
                "\"serviceCode\": \"VA0021\",\n" +
                "\"value\": \"1000\"\n" +
                "},\n" +
                "{\n" +
                "\"serviceCode\": \"VA0003\",\n" +
                "\"value\": \"Y\"\n" +
                "},\n" +
                "{\n" +
                "\"serviceCode\": \"VA0042\",\n" +
                "\"value\": \"1000\"\n" +
                "},\n" +
                "{\n" +
                "\"serviceCode\": \"VA0019\",\n" +
                "\"value\": \"4000\"\n" +
                "}\n" +
                "],\n" +
                "\"detail\": [{\n" +
                "\"erpOrderLineNum\": 1,\n" +
                "\"skuNo\": \"6422731233\",\n" +
                "\"skuName\": \"Topfer特福芬 新妈妈茶（混合花草冲饮） 200g\",\n" +
                "\"packageUnitCode\": \"件\",\n" +
                "\"lot\": \"1505444444\",\n" +
                "\"quantity\": 10\n" +
                "},\n" +
                "{\n" +
                "\"erpOrderLineNum\": 2,\n" +
                "\"skuNo\": \"8139526156\",\n" +
                "\"skuName\": \"WEI-I味一 旗鱼松 105g\",\n" +
                "\"packageUnitCode\": \"包\",\n" +
                "\"lot\": \"1505444444\",\n" +
                "\"quantity\": 10\n" +
                "}\n" +
                "]\n" +
                "}\n" +
                "{\n" +
                "\"carrierCode\": \"CP\",\n" +
                "\"carrierServiceType\": \"SE0059\",\n" +
                "\"completeDelivery\": \"Y\",\n" +
                "\"consignee\": {\n" +
                "\"consigneeCityName\": \"深圳市\",\n" +
                "\"consigneeCompany\": \"测试\",\n" +
                "\"consigneeContactName\": \"程旭\",\n" +
                "\"consigneeContactPhone\": \"136954213690\",\n" +
                "\"consigneeLocationName\": \"广东省深圳市龙岗区横岗街道红棉4路森城工业区A2栋\",\n" +
                "\"consigneeProvinceName\": \"广东省\"\n" +
                "},\n" +
                "\"shipper\": {},\n" +
                "\"customer\": {\n" +
                "\"companyCode\": \"0294873527\",\n" +
                "\"customerMonthlyCard\": \"7553000088\"\n" +
                "},\n" +
                "\"detail\": [{\n" +
                "\"erpOrderLineNum\": \"1\",\n" +
                "\"inventoryStatus\": \"10\",\n" +
                "\"packageUnitCode\": \"EA\",\n" +
                "\"quantity\": 1.0,\n" +
                "\"skuName\": \"如康整切眼肉牛排3片装【340g】\",\n" +
                "\"skuNo\": \"6970374185860\"\n" +
                "}],\n" +
                "\"addedService\": [{\n" +
                "\"serviceCode\": \"VA0021\",\n" +
                "\"value\": \"20000\"\n" +
                "},\n" +
                "{\n" +
                "\"serviceCode\": \"VA0003\",\n" +
                "\"value\": \"Y\"\n" +
                "参数名\n" +
                "类型\n" +
                "说明\n" +
                "code\n" +
                "字符(32)\n" +
                "接口状态 200 成功 其它异常\n" +
                "message\n" +
                "字符(500)\n" +
                "接口状态 200 成功 其它异常\n" +
                "items\n" +
                "对象\n" +
                "详细请见items\n" +
                "参数名\n" +
                "类型\n" +
                "说明\n" +
                "sfOrderNo\n" +
                "字符(64)\n" +
                "SF生成订单号 (失败不产生)\n" +
                "erpOrder\n" +
                "字符(64)\n" +
                "外部系统订单号(客户方系统订单号)\n" +
                "code\n" +
                "字符(32)\n" +
                "接口状态 200 成功 其它异常\n" +
                "errMsg\n" +
                "字符(128)\n" +
                "失败原因\n" +
                "返回参数说明\n" +
                "items对象\n" +
                "返回示例\n" +
                "},\n" +
                "{\n" +
                "\"serviceCode\": \"VA0041\",\n" +
                "\"value\": \"Y\"\n" +
                "}\n" +
                "],\n" +
                "\"erpOrder\": \"32367454718150251233445\",\n" +
                "\"orderTime\": \"2019-01-21 19:18:47\",\n" +
                "\"sfOrderType\": \"PO\",\n" +
                "\"warehouseCode\": \"010VB\",\n" +
                "\"deliveryDate\": \"2019-01-22 19:18:47\"\n" +
                "}";
        return request;
    }

    private static String expReceFilterOrderBsp() {
        String request = "{\n" +
                "\"erpOrder\": \"客户erp单号\",\n" +
                "\"sfOrderNo\": \"SF生成订单号\",\n" +
                "\"sourceCode\": \"SFTEST\"\n" +
                "}";
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
