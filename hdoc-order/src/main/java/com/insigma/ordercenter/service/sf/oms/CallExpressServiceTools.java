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
                request = transport();
                break;
            case INBOUND:
                request = inbound();
                break;
            case OUTBOUND:
                request = outbound();
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
                request = cancelOutbound();
                break;
            case CANCEL_OUTBOUND:
                request = cancelOutbound();
                break;
            case COMMODITY_INFO:
                request = cancelOutbound();
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
                "\"userDef3\": \"\",\"userDef4\": \"\",\n" +
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

    private static String transport() {
String request= "{\n" +
        "\"orderItems\": [{\n" +
        "\"temperatureLevelName\": \"0至10\",\n" +
        "\"remark\": \"这是备注\",\n" +
        "\"skuName\": \"维生素C咀嚼片\",\n" +
        "\"quantity\": \"50\",\n" +
        "\"grossWeight\": \"12\",\n" +
        "\"volume\": \"12\"\n" +
        "}],\n" +
        "\"erpOrder\": \"1233\",\n" +
        "\"monthlyAccount\": \"\",\n" +
        "\"consigneeProvinceName\": \"广东省\",\n" +
        "\"paymentTypeCode\": \"PR_ACCOUNT\",\n" +
        "\"shipperLocationName\": \"宝安M17大厦A栋07\",\n" +
        "\"shipperProvinceName\": \"广东省\",\n" +
        "\"shipperContactName\": \"奥特曼\",\n" +
        "\"shipperCityName\": \"深圳市\",\n" +
        "\"consigneeLocationName\": \"广东省深圳市南山区深圳南山深南大道58号\",\n" +
        "\"extenSystemOrderNo\": \"A00000002\",\n" +
        "\"shipperName\": \"M17星制药\",\n" +
        "\"consigneeCityName\": \"深圳市\",\n" +
        "\"remark\": \"这是备注\",\n" +
        "\"consigneeName\": \"顺丰物流公司\",\n" +
        "\"consigneeContactName\": \"李生\",\n" +
        "\"consigneeContactTel\": \"13924222888\",\n" +
        "\"consigneeDistrictName\": \"宝安区\",\n" +
        "\"shipperContactTel\": \"13700000002\",\n" +
        "\"shipperDistrictName\": \"福田区\",\n" +
        "\"productCode\": \"SE0059\",\n" +
        "\"temperatureLevelCode\": \"3\",\n" +
        "\"sourceCode\": \"demo-sysrem\",\n" +
        "\"orderTime\": \"2018-01-01 12:12:12\",\n" +
        "\"transportType\": \"LAND\",\n" +
        "\"orderServices\": [{\n" +
        "\"serviceValue\": \"\",\n" +
        "\"serviceCode\": \"VA0003\"\n" +
        "},\n" +
        "{\n" +
        "\"serviceValue\": \"3000\",\n" +
        "\"serviceCode\": \"VA0021\"\n" +
        "},\n" +
        "{\n" +
        "\"serviceValue\": \"\",\n" +
        "\"serviceCode\": \"VA0059\"\n" +
        "},\n" +
        "{\n" +
        "\"serviceValue\": \"\",\n" +
        "\"serviceCode\": \"VA0058\"\n" +
        "}\n" +
        "]\n" +
        "}";

        return request;
    }

    private static String inbound() {
//        {"code":"200","message":"操作成功","model":[{"code":"200","errMsg":null,"sfOrderNo":"IB569100677344501338-100","extenSystemOrderNo":"11000853401258","transactionId":null}],"success":true}
        String request = "{\"supplierCode\": \"CARREFOUR\",\n" +
                "\"warehouseCode\": \"P024CSB\",\n" +
                "\"sfOrderType\": \"PI\",\n" +
                "\"licensePlateNumber\": \"221\",\n" +
                "\"orderTime\": \"2018-09-20 15:50:50\",\n" +
                "\"distributionType\": \"Y\",\n" +
                "\"tradePlatform\": \"JD\",\n" +
                "\"erpOrder\": \"11000853401258\",\n" +
                "\"userDef1\": \"\",\n" +
                "\"item\": [{\n" +
                "\"lotatt02\": \"2018-09-20 15:50:50\",\n" +
                "\"qtyUm\": \"EA\",\n" +
                "\"lotatt03\": \"2018-09-20 15:50:50\",\n" +
                "\"lotatt01\": \"2018-09-20 15:50:50\",\n" +
                "\"usetItemDef4\": \"\",\n" +
                "\"usetItemDef3\": \"\",\n" +
                "\"usetItemDef2\": \"\",\n" +
                "\"inventoryStatus\": \"10\",\n" +
                "\"lotatt05\": \"1234\",\n" +
                "\"usetItemDef1\": \"\",\n" +
                "\"remark\": \"\",\n" +
                "\"erpOrderLineNum\": \"123123\",\n" +
                "\"lot\": \"123\",\n" +
                "\"expirationTime\": 6,\n" +
                "\"price\": 11,\n" +
                "\"qty\": \"2616\",\n" +
                "\"skuNo\": \"LF15050041001\",\n" +
                "\"usetItemDef8\": \"\",\n" +
                "\"usetItemDef7\": \"\",\n" +
                "\"usetItemDef6\": \"\",\n" +
                "\"usetItemDef5\": \"\"\n" +
                "}],\n" +
                "\"tradeOrder\": \"111\",\n" +
                "\"originalNo\": \"\",\n" +
                "\"userDef8\": \"\",\n" +
                "\"userDef6\": \"\",\n" +
                "\"requirement\": \"\",\n" +
                "\"userDef7\": \"\",\n" +
                "\"userDef4\": \"\",\n" +
                "\"userDef5\": \"\",\n" +
                "\"userDef2\": \"\",\n" +
                "\"buyer\": \"\",\n" +
                "\"driverCalls\": \"\",\n" +
                "\"erpOrderType\": \"10\",\n" +
                "\"userDef3\": \"\",\n" +
                "\"buyerPhone\": \"\",\n" +
                "\"driver\": \"\",\n" +
                "\"expectDate\": \"2018-09-20 15:50:50\",\n" +
                "\"customer\": {\n" +
                "\"companyCode\": \"7550057640\",\n" +
                "\"customerMonthlyCard\": \"7550057640\"\n" +
                "}\n" +
                "}";
        return request;
    }

    private static String outbound() {
        String request = "{" +
                "    \"detail\": [" +
                "        {" +
                "            \"isBom\": \"N\"," +
                "            \"skuName\": \"羊腿礼盒\"," +
                "            \"skuNo\": \"6950142400845\"," +
                "            \"packageUnitCode\": \"EA\"," +
                "            \"quantity\": \"1\"," +
                "            \"erpOrderLineNum\": 1" +
                "        }" +
                "    ]," +
                "    \"consignee\": {" +
                "        \"consigneeContactTel\": \"13552179716\"," +
                "        \"consigneeLocationName\": \"北京北京市大兴区原生墅41-3\"," +
                "        \"consigneeCityName\": \"北京市\"," +
                "        \"consigneeContactPhone\": \"13552179716\"," +
                "        \"consigneeContactName\": \"刘素芳\"," +
                "\"consigneeDistrictName\": \"大兴区\"," +
                "        \"consigneeCountryName\": \"中国\"," +
                "        \"consigneeProvinceName\": \"北京\"" +
                "    }," +
                "    \"customer\": {" +
                "        \"customerMonthlyCard\": \"0208255482\"," +
                "        \"companyCode\": \"0208255482\"" +
                "    }," +
                "    \"warehouseCode\": \"010VB\"," +
                "    \"orgErpOrder\": \"00200703840107\"," +
                "    \"priorityCode\": \"3\"," +
                "    \"sfOrderType\": \"PO\"," +
                "    \"carrierServiceType\": \"SE0113\"," +
                "    \"shipper\": {}," +
                "    \"completeDelivery\": \"Y\"," +
                "    \"orgTradeOrder\": \"200723131756699\"," +
                "    \"orderTime\": \"2020-07-23 13:17:56\"," +
                "    \"carrierCode\": \"CP\"," +
                "    \"addedService\": [" +
                "        {}" +
                "    ]," +
                "    \"paymentDistrict\": \"大兴区\"," +
                "    \"erpOrder\": \"00200703840110\"" +
                "}";
        return request;
    }

    private static String expReceFilterOrderBsp() {
        String request = "{" +
                "\"erpOrder\": \"客户erp单号\"," +
                "\"sfOrderNo\": \"SF生成订单号\"," +
                "\"sourceCode\": \"SFTEST\"" +
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

    private static String cancelOutbound() {
        CancelOutbound dto = new CancelOutbound();
        dto.setErpOrder("00200703840108");
     //   cob.setReceiptId("00200703840108");
        dto.setCompanyCode("0208255482");
        dto.setWarehouserCode("010VB");
        String request = "{\n" +
                "\"erpOrder\": \"00200703840108\",\n" +
                "\"receiptId\": \"200\",\n" +
                "\"companyCode\": \"0208255482\",\n" +
                "\"warehouserCode\": \"010VB\"\n" +
                "}";
        request = JSON.toJSONString(dto);
        return request;
    }

    public static String getMsgDigest(String msgData, String timeStamp, String md5Key) throws UnsupportedEncodingException {
        String msgDigest = VerifyCodeUtil.md5EncryptAndBase64(URLEncoder.encode(msgData + timeStamp + md5Key, "UTF-8"));
        return msgDigest;
    }

    //===返回结果：{"apiErrorMsg":"","apiResponseID":"000173744CA0EE3FEFF1EEFC90D1E13F","apiResultCode":"A1000","apiResultData":"{\"success\":true,\"errorCode\":\"S0000\",\"errorMsg\":null,\"msgData\":{\"orderId\":\"QIAO-20200618-004\",\"originCode\":\"755\",\"destCode\":\"020\",\"filterResult\":2,\"remark\":\"\",\"url\":null,\"paymentLink\":null,\"isUpstairs\":null,\"isSpecialWarehouseService\":null,\"mappingMark\":null,\"agentMailno\":null,\"returnExtraInfoList\":null,\"waybillNoInfoList\":[{\"waybillType\":1,\"waybillNo\":\"SF7444407670710\"}],\"routeLabelInfo\":[{\"code\":\"1000\",\"routeLabelData\":{\"waybillNo\":\"SF7444407670710\",\"sourceTransferCode\":\"\",\"sourceCityCode\":\"755\",\"sourceDeptCode\":\"755\",\"sourceTeamCode\":\"\",\"destCityCode\":\"020\",\"destDeptCode\":\"020\",\"destDeptCodeMapping\":\"\",\"destTeamCode\":\"\",\"destTeamCodeMapping\":\"\",\"destTransferCode\":\"020\",\"destRouteLabel\":\"020\",\"proName\":\"顺丰标快\",\"cargoTypeCode\":\"C201\",\"limitTypeCode\":\"T4\",\"expressTypeCode\":\"B1\",\"codingMapping\":\"\",\"codingMappingOut\":\"\",\"xbFlag\":\"0\",\"printFlag\":\"000000000\",\"twoDimensionCode\":\"MMM={'k1':'020','k2':'020','k3':'','k4':'T4','k5':'SF7444407670710','k6':'','k7':'fadf0aa3'}\",\"proCode\":\"T4\",\"printIcon\":\"00000000\",\"abFlag\":\"\",\"destPortCode\":\"\",\"destCountry\":\"\",\"destPostCode\":\"\",\"goodsValueTotal\":\"\",\"currencySymbol\":\"\",\"cusBatch\":\"\",\"goodsNumber\":\"\",\"errMsg\":\"\",\"checkCode\":\"fadf0aa3\",\"proIcon\":\"\",\"fileIcon\":\"\",\"fbaIcon\":\"\",\"icsmIcon\":\"\",\"destGisDeptCode\":\"020\",\"newIcon\":null},\"message\":\"SF7444407670710:\"}],\"contactInfoList\":null}}"}
}
