package com.insigma.ordercenter.service.sf.qiao;


import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSONObject;
import com.insigma.ordercenter.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Request")
@Slf4j
public  class  SFExpressRequest extends ExpressRequest {
        public static final String ORDER_NO = "orderNo";//接口url


        private static final long serialVersionUID = 1L;
        @Value("${express.sf.url}")
        //  private String url = "http://bsp-oisp.sf-express.com/bsp-oisp/sfexpressService";
        private String url = "https://sfapi-sbox.sf-express.com/std/service";


        @Value("${express.sf.checkWord}")
        private String checkWord = "u1UQHYQpLhSmurqiTbVnTmIDERsMHKOf";
        @Value("${express.sf.clientCode}")
        private String clientCode = "YvLec24Y";

        /**
         * 顺丰接口
         *
         * @param params
         * @param type   1-下订单接口  2-订单结果查询接口 3-路由查询接口
         * @return
         */
        public SFExpressResponse sfExpressMethod(Map<String, String> params, int type) throws Exception {
                log.info("进入顺丰接口:params={},type={}", JSONObject.toJSONString(params), type);
                if (type < 1) {
                        log.warn("调用接口类型传错");
                        return null;
                }
                String requestXml = "";
                String methodName = "";
                if (type == 1) {
                        //1.获取下单xml
                        requestXml = getOrderServiceRequestXml(params);
                        methodName = "orderSerivce";
                } else if (type == 2) {
                        //1.获取订单结果查询xml
                        requestXml = getOrderSearchServiceRequestXml(params);
                        methodName = "orderSearchService";
                } else if (type == 3) {
                        //1.获取订单物流路由
                        requestXml = getRouteServiceRequestXml(params);
                        methodName = "routeService";
                }
//
//                {"cargoDetails":[{"count":2.365,"unit":"个","weight":6.1,
//                        "amount":100.5111,"currency":"HKD","name":"护肤品1","sourceArea":"CHN"}],
//                        "contactInfoList":[{"address":"广东省深圳市南山区软件产业基地11栋","contact":"小曾",
//                        "contactType":1,"country":"CN","postCode":"580058","tel":"4006789888"},
//                        {"address":"广东省广州市白云区湖北大厦","company":"顺丰速运","contact":"小邱",
//                                "contactType":2,"country":"CN","postCode":"580058","tel":"18688806057"}],
//                        "language":"zh_CN","orderId":"OrderNum20200612223"}


                //2.xml+checkWord
                String verifyCode = requestXml + checkWord;
                //3.MD5加密 + Base64编码
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                md5.update(verifyCode.getBytes("utf8"));
                verifyCode = Base64.encode(md5.digest());
                //4.post 请求
                Map<String, String> toHttpParams = new HashMap<>();
                toHttpParams.put("xml", requestXml);
                toHttpParams.put("verifyCode", verifyCode);

                log.info("请求顺丰接口" + methodName, toHttpParams.toString());
                String resultStr = HttpClientUtil.doPost(url, toHttpParams);
                log.info("请求顺丰接口" + methodName + ",result=" + resultStr);
                SFExpressResponse response = JSONObject.parseObject(resultStr, SFExpressResponse.class);
                return response;
        }


        /**
         * 获取顺丰路由查询接口xml
         *
         * @param params
         * @return
         */
        private String getRouteServiceRequestXml(Map<String, String> params) {
                String orderNo = params.get("orderNo");
                StringBuilder strBuilder = new StringBuilder();
                strBuilder.append("<Request service='RouteService' lang='zh-CN'>");
                strBuilder.append("<Head>" + clientCode + "</Head>");
                strBuilder.append("<Body>");
                strBuilder.append("<RouteRequest").append(" ");
                strBuilder.append("tracking_type='2'").append(" ");
                strBuilder.append("tracking_number='" + orderNo.toString().trim() + "" + "'").append(" >");
                strBuilder.append("</RouteRequest>");
                strBuilder.append("</Body>");
                strBuilder.append("</Request>");
                return strBuilder.toString();
        }

        /**
         * 获取顺丰订单结果查询接口xml
         *
         * @param params
         * @return
         */
        private String getOrderSearchServiceRequestXml(Map<String, String> params) {
                String orderNo = params.get("orderNo");
                StringBuilder strBuilder = new StringBuilder();
                strBuilder.append("<Request service='OrderSearchService' lang='zh-CN'>");
                strBuilder.append("<Head>" + clientCode + "</Head>");
                strBuilder.append("<Body>");
                strBuilder.append("<OrderSearch").append(" ");
                strBuilder.append("orderid='" + orderNo.toString().trim() + "" + "'").append(" > ");
                strBuilder.append("</OrderSearch>");
                strBuilder.append("</Body>");
                strBuilder.append("</Request>");
                return strBuilder.toString();
        }

        private void testRequest() throws Exception {
                String reqURL = "http://bsp-oisp.sf-express.com/bsp-oisp/sfexpressService";
                String reqXml = "<Request service = \"OrderService\" lang = \"zh-CN\" > " +
                        "<Head>SLKJ2019</Head>" +
                        "<Body>" +
                        "  <Order " +
                        "	orderid=\"SFKD-20160219000019\" " +
                        "	j_company=\"深圳宝龙达信息技术股份有限公司\" " +
                        "	j_contact=\"邓丽君\" " +
                        "	j_tel=\"15323233432\" " +
                        "	j_mobile=\"15322234342\" " +
                        "	j_province=\"广东省\" " +
                        "	j_city=\"深圳市\"" +
                        "	j_county=\"南山区\"" +
                        "	j_address=\"广东省深圳市南山区西丽镇塘朗同富裕工业城7栋\" " +
                        "	d_contact=\"四海\" d_tel=\"15023434543\" " +
                        "	d_mobile=\"15423456545\" " +
                        "	d_province=\"广东省\" " +
                        "	d_city=\"深圳市\" " +
                        "	d_county=\"南山区\" " +
                        "	d_address=\"科技园软件产业基地\" " +
                        "	express_type=\"1\" " +
                        "	pay_method=\"1\" " +
                        "	custid=\"7551234567\" " +
                        "	parcel_quantity=\"1\" " +
                        "	is_docall=\"0\" " +
                        "	sendstarttime=\"\"  " +
                        "	remark=\"电子产品 笔记本+显卡\"" +
                        "        is_unified_waybill_no=\"1\">" +
                        "</Order>" +
                        "</Body> " +
                        "</Request>";//接口请求对应得xml报文

                String myReqXML = reqXml.replaceAll("SLKJ2019", clientCode);
                //2.xml+checkWord
                String verifyCode = myReqXML + checkWord;
                //3.MD5加密 + Base64编码
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                verifyCode = Base64.encode(md5.digest());
                Map map = new LinkedHashMap();
                map.put("xml", myReqXML);
                map.put("verifyCode", verifyCode);

                String response = HttpClientUtil.doPost(reqURL, map);
                System.out.println(response);
        }

        private String j_company = "深圳宝龙达信息技术股份有限公司";
        private String j_contact = "邓丽君";
        private String j_tel = "15323233432";
        private String j_mobile = "15323233432";
        private String j_province = "广东省";
        private String j_city = "深圳市";
        private String j_county = "南山区";
        private String j_address = "广东省深圳市南山区西丽镇塘朗同富裕工业城7栋";
        private String d_contact = "四海";
        private String d_company = "深圳宝龙达信息技术股份有限公司";

        /**
         * 获取顺丰下订单接口xml
         *
         * @param params
         * @return
         */
        private String getOrderServiceRequestXml(Map<String, String> params) {
                //订单号
                String orderNo = params.get("orderNo");
                //收件人
                String receiverName = params.get("receiverName");
                //收件人电话
                String receiverMobile = params.get("receiverMobile");
                //收件人详细地址
                String receiverAddress = params.get("address");
                //商品名称
                String commodityName = params.get("commodityName");
                //商品数量
                String orderNum = params.get("orderNum");


                StringBuilder strBuilder = new StringBuilder();
                strBuilder.append("<Request service='OrderService' lang='zh-CN'>");
                strBuilder.append("<Head>" + clientCode + "</Head>");
                strBuilder.append("<Body>");
                strBuilder.append("<Order").append(" ");
                strBuilder.append("orderid='" + orderNo.toString().trim() + "" + "'").append(" ");
                //返回顺丰运单号
                strBuilder.append("is_gen_bill_no='1'").append(" ");
                //寄件方信息
                strBuilder.append("j_company='" + j_company + "'").append(" ");
                strBuilder.append("j_contact='" + j_contact + "'").append(" ");
                strBuilder.append("j_tel='" + j_tel + "'").append(" ");
                strBuilder.append("j_address='" + j_province + j_city + j_county + j_address + "'").append(" ");
                //收件方信息
                strBuilder.append("d_company='" + d_company + "'").append(" ");
                strBuilder.append("d_contact='" + receiverName.toString().trim() + "'").append(" ");
                strBuilder.append("d_tel='" + receiverMobile.toString().trim() + "'").append(" ");
                strBuilder.append("d_address='" + receiverAddress.toString().trim() + "'").append(" ");
                strBuilder.append(" > ");
                //货物信息
                strBuilder.append("<Cargo").append(" ");
                strBuilder.append("name='" + commodityName + "'").append(" ");
                strBuilder.append("count='" + orderNum.toString() + "'").append(" ");
                strBuilder.append("unit='双'").append(">");
                strBuilder.append("</Cargo>");

                strBuilder.append("</Order>");
                strBuilder.append("</Body>");
                strBuilder.append("</Request>");

                return strBuilder.toString();
        }

        public static void main(String args[]) throws Exception {
                SFExpressRequest request = new SFExpressRequest();
                Map<String, String> map = new HashMap<>();
                map.put(ORDER_NO, "testOrder");
                request.sfExpressMethod(map, 1);
        }
}