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
import springfox.documentation.spring.web.json.Json;

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
            url = OMSUtil.CALL_URL_BOX;
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
////            case TRANSPORT:
////                request = transport();
//                break;
            case INBOUND:
                request = inbound();
                break;
            case OUTBOUND:
                request = outbound();
                break;
//            case OUTBOUND_CONFIRM:
//                request = outboundConfirm();
//                break;
//            case CANCEL_TRANSPORT:
//              //  request = expReceFilterOrderBsp();
//                break;
            case ROUTE_QUERY:
                request = expReceSearchRoutes();
                break;
//            case QUERY_WAYBILL:
//                request = expReceGetSubMailno();
//                break;
            case CANCEL_INBOUND:
                request = cancelInbound();
                break;
            case INBOUND_QUERY:
                request = cancelOutbound();
                break;
                case CANCEL_OUTBOUND:
                request = cancelOutbound();
                break;

//            case COMMODITY_INFO:
//                request = cancelOutbound();
//                break;
            default:
                break;
        }

        return request;
    }

    private static String outboundConfirm() {
        String request ="{" +
                "\"actualShipDateTime\": 1559715069000," +
                "\"carrier\": \"CP\"," +
                "\"carrierProduct\": \"SE0030\"," +
                "\"companyCode\": \"SUBWAY886\"," +
                "\"dataStatus\": \"900\"," +
                "\"erpOrder\": \"3236745471812081\"," +
                "\"outboundContainer\": [{" +
                "\"containerNo\": \"101000042296013\"," +
                "\"containerType\": \"0\"," +
                "\"items\": [{" +
                "\"actualQty\": 1.0," +
                "\"erpOrderLineNum\": \"1\"," +
                "\"inventoryStatus\": \"10\"," +
                "\"lot\": \"\"," +
                "\"lotattdesc\": \";;2019-04-22;;;;;N;;;;;\"," +
                "\"skuNo\": \"WMSTESTID3\"," +
                "\"userDef1\": \"\"," +
                "\"userDef11\": \"\"," +
                "\"userDef2\": \"10\"," +
                "\"userDef3\": \"1\"," +
                "\"userDef4\": \"\"," +
                "\"userDef5\": \"\"," +
                "\"userDef6\": \"\"," +
                "\"userDef7\": \"0\"," +
                "\"userDef8\": \"1\"," +
                "\"weight\": 2.0," +
                "\"weightUm\": \"KG\"" +
                "}]," +
                "\"userDef1\": \"\"," +
                "\"userDef2\": \"\"," +
                "\"userDef3\": \"\",\"userDef4\": \"\"," +
                "\"userDef5\": \"\"," +
                "\"userDef6\": \"\"," +
                "\"userDef7\": \"0\"," +
                "\"userDef8\": \"1\"," +
                "\"weight\": 2.0," +
                "\"weightUm\": \"KG\"" +
                "}]," +
                "\"outboundDetail\": [{" +
                "\"actualQty\": 1.0," +
                "\"erpOrderLineNum\": \"1\"," +
                "\"skuNo\": \"WMSTESTID3\"" +
                "}]," +
                "\"returnTracking\": \"\"," +
                "\"sfOrderNo\": \"OB412459453353034489-100\"," +
                "\"transactionId\": \"203SO190605000009\"," +
                "\"userDef1\": \"\"," +
                "\"userDef2\": \"000001308000*1,\"," +
                "\"userDef3\": \"\"," +
                "\"userDef4\": \"\"," +
                "\"userDef5\": \"\"," +
                "\"userDef6\": \"\"," +
                "\"userDef7\": \"0\"," +
                "\"userDef8\": \"0\"," +
                "\"warehouseCode\": \"010VB\"," +
                "\"wayBillNo\": \"SF7001001199534\"" +
                "}";
        return request;
    }

    private static String transport() {
String request= "{" +
        "\"orderItems\": [{" +
        "\"temperatureLevelName\": \"0至10\"," +
        "\"remark\": \"这是备注\"," +
        "\"skuName\": \"维生素C咀嚼片\"," +
        "\"quantity\": \"50\"," +
        "\"grossWeight\": \"12\"," +
        "\"volume\": \"12\"" +
        "}]," +
        "\"erpOrder\": \"1233\"," +
        "\"monthlyAccount\": \"\"," +
        "\"consigneeProvinceName\": \"广东省\"," +
        "\"paymentTypeCode\": \"PR_ACCOUNT\"," +
        "\"shipperLocationName\": \"宝安M17大厦A栋07\"," +
        "\"shipperProvinceName\": \"广东省\"," +
        "\"shipperContactName\": \"奥特曼\"," +
        "\"shipperCityName\": \"深圳市\"," +
        "\"consigneeLocationName\": \"广东省深圳市南山区深圳南山深南大道58号\"," +
        "\"extenSystemOrderNo\": \"A00000002\"," +
        "\"shipperName\": \"M17星制药\"," +
        "\"consigneeCityName\": \"深圳市\"," +
        "\"remark\": \"这是备注\"," +
        "\"consigneeName\": \"顺丰物流公司\"," +
        "\"consigneeContactName\": \"李生\"," +
        "\"consigneeContactTel\": \"13924222888\"," +
        "\"consigneeDistrictName\": \"宝安区\"," +
        "\"shipperContactTel\": \"13700000002\"," +
        "\"shipperDistrictName\": \"福田区\"," +
        "\"productCode\": \"SE0059\"," +
        "\"temperatureLevelCode\": \"3\"," +
        "\"sourceCode\": \"demo-sysrem\"," +
        "\"orderTime\": \"2018-01-01 12:12:12\"," +
        "\"transportType\": \"LAND\"," +
        "\"orderServices\": [{" +
        "\"serviceValue\": \"\"," +
        "\"serviceCode\": \"VA0003\"" +
        "}," +
        "{" +
        "\"serviceValue\": \"3000\"," +
        "\"serviceCode\": \"VA0021\"" +
        "}," +
        "{" +
        "\"serviceValue\": \"\"," +
        "\"serviceCode\": \"VA0059\"" +
        "}," +
        "{" +
        "\"serviceValue\": \"\"," +
        "\"serviceCode\": \"VA0058\"" +
        "}" +
        "]" +
        "}";

        return request;
    }

    private static String inbound() {
//        {"code":"200","message":"操作成功","model":[{"code":"200","errMsg":null,"sfOrderNo":"IB569100677344501338-100","extenSystemOrderNo":"11000853401258","transactionId":null}],"success":true}
        String request = "{\"supplierCode\": \"CARREFOUR\"," +
                "\"warehouseCode\": \"P024CSB\"," +
                "\"sfOrderType\": \"PI\"," +
                "\"licensePlateNumber\": \"221\"," +
                "\"orderTime\": \"2018-09-20 15:50:50\"," +
                "\"distributionType\": \"Y\"," +
                "\"tradePlatform\": \"JD\"," +
                "\"erpOrder\": \""+System.currentTimeMillis()+"\"," +
                "\"userDef1\": \"\"," +
                "\"item\": [{" +
                "\"lotatt02\": \"2018-09-20 15:50:50\"," +
                "\"qtyUm\": \"EA\"," +
                "\"lotatt03\": \"2018-09-20 15:50:50\"," +
                "\"lotatt01\": \"2018-09-20 15:50:50\"," +
                "\"usetItemDef4\": \"\"," +
                "\"usetItemDef3\": \"\"," +
                "\"usetItemDef2\": \"\"," +
                "\"inventoryStatus\": \"10\"," +
                "\"lotatt05\": \"1234\"," +
                "\"usetItemDef1\": \"\"," +
                "\"remark\": \"\"," +
                "\"erpOrderLineNum\": \"123123\"," +
                "\"lot\": \"123\"," +
                "\"expirationTime\": 6," +
                "\"price\": 11," +
                "\"qty\": \"2616\"," +
                "\"skuNo\": \"LF15050041001\"," +
                "\"usetItemDef8\": \"\"," +
                "\"usetItemDef7\": \"\"," +
                "\"usetItemDef6\": \"\"," +
                "\"usetItemDef5\": \"\"" +
                "}]," +
                "\"tradeOrder\": \"111\"," +
                "\"originalNo\": \"\"," +
                "\"userDef8\": \"\"," +
                "\"userDef6\": \"\"," +
                "\"requirement\": \"\"," +
                "\"userDef7\": \"\"," +
                "\"userDef4\": \"\"," +
                "\"userDef5\": \"\"," +
                "\"userDef2\": \"\"," +
                "\"buyer\": \"\"," +
                "\"driverCalls\": \"\"," +
                "\"erpOrderType\": \"10\"," +
                "\"userDef3\": \"\"," +
                "\"buyerPhone\": \"\"," +
                "\"driver\": \"\"," +
                "\"expectDate\": \"2018-09-20 15:50:50\"," +
                "\"customer\": {" +
                "\"companyCode\": \"7550057640\"," +
                "\"customerMonthlyCard\": \"7550057640\"" +
                "}" +
                "}";
        return request;
    }

    private static String outbound() {
        Outbound  outbound = new Outbound();
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
                "    \"erpOrder\": \"00200703840113\"" +
                "}";


        return request;
    }

    private static String cancelInbound() {
        String request = "";
        List<CancelInbound> cancelInbounds = new ArrayList<CancelInbound>();
        String companyCode = "200";
        String warehouseCode = "200";
        String erpOrder = "200";
        String sfOrderNo = "接收成功";
        cancelInbounds.add(new CancelInbound(companyCode, warehouseCode, erpOrder, sfOrderNo));
//        String request = "[{" +
//                "\"companyCode\": \"200\"," +
//                "\"warehouseCode\": \"200\"," +
//                "\"erpOrder\": \"200\"," +
//                "\"sfOrderNo\": \"接收成功\"" +
//                "}]";
        request = JSON.toJSONString(cancelInbounds);
        return request;
    }

    private static String expReceSearchRoutes() {
        String request = "";
        RouteQuery dto = new RouteQuery();
        dto.setOrderType("1");
        dto.setErpOrder("00200703840110");
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
        String request = "{" +
                "\"erpOrder\": \"00200703840108\"," +
                "\"receiptId\": \"200\"," +
                "\"companyCode\": \"0208255482\"," +
                "\"warehouserCode\": \"010VB\"" +
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
