package com.insigma.ordercenter.logistics.hdjf;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class YTOClient {
    private final YTOProperties properties;

    public YTOClient(YTOProperties properties) {
        this.properties = properties;
    }

    public YTOClient(String appKey, String appSecret) {
        this.properties = new YTOProperties(appKey, appSecret);
    }

    public String executeRoute(YTOPublicRequest request) throws IOException {
        String jsonBody = request.getBody();
        Map<String, String> params = request.getParams();

        StringBuilder queryBuilder = new StringBuilder();
        // StringBuilder strToDigestBuilder = new StringBuilder();
        StringBuilder strSignBuilder = new StringBuilder();
        for (Map.Entry<String, String> e : params.entrySet()) {
            if ("timestamp".equals(e.getKey())) {
                queryBuilder.append(e.getKey()).append("=").append(e.getValue()).append("&");
            } else {
                queryBuilder.append(urlEncode(e.getKey())).append("=").append(urlEncode(e.getValue())).append("&");
            }
            if (!"param".equals(e.getKey())) {
                strSignBuilder.append(e.getKey()).append(e.getValue());
            } else {
            }


        }

        String b = properties.getKey() + strSignBuilder.toString();
        System.out.println("b :" + b);

        System.out.println("urlDecode :" + urlDecode(" %5B%7B%22Number%22%3A%221111111111%22%7D%5D"));

        String c = DigestUtils.md5Hex(b);
        String sign = c.toUpperCase();

        String queryString = queryBuilder.substring(0, queryBuilder.length() - 1);
//            String strToDigest = strToDigestBuilder.substring(0, strToDigestBuilder.length() - 1);
//            strToDigest = strToDigest + properties.getKey();
        Map<String, String> headers = new HashMap<String, String>();
//            headers.put("clientId", properties.getCompanyId());
//            headers.put("data_digest", YTODigestUtil.digest(strToDigest));
//// headers.put("logistics_interface", strToDigest);
//            headers.put("type", "online");
        queryString = "sign=" + sign + "&" + queryString;
        System.out.println("url:" + request.getUrl());
        System.out.println("headers:" + headers);
        System.out.println("queryString:" + queryString);
        return  HttpUtil.post(request.getUrl(), headers, queryString);
    }

    public String execute(YTOPublicRequest request) throws IOException {
        String jsonBody = request.getBody();
        if (jsonBody == null) {
            Map<String, String> params = request.getParams();

            StringBuilder queryBuilder = new StringBuilder();
            StringBuilder strToDigestBuilder = new StringBuilder();
            for (Map.Entry<String, String> e : params.entrySet()) {
                strToDigestBuilder.append(e.getKey()).append("=").append(e.getValue()).append("&");
                queryBuilder.append(urlEncode(e.getKey())).append("=").append(urlEncode(e.getValue())).append("&");
            }
            String queryString = queryBuilder.substring(0, queryBuilder.length() - 1);
            String strToDigest = strToDigestBuilder.substring(0, strToDigestBuilder.length() - 1);
            strToDigest = strToDigest + properties.getKey();
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("clientId", properties.getCompanyId());
            headers.put("data_digest", YTODigestUtil.digest(strToDigest));
// headers.put("logistics_interface", strToDigest);
            headers.put("type", "online");
            return HttpUtil.post(request.getUrl(), headers, queryString);
        } else {
            Map<String, String> headers = new HashMap<String, String>();
            String strToDigest = jsonBody + properties.getKey();
            headers.put("clientId", properties.getCompanyId());
            headers.put("data_digest", YTODigestUtil.digest(strToDigest));
            headers.put("logistics_interface", YTODigestUtil.digest(strToDigest));
            headers.put("type", "online");
//            logistics_interface=%3CRequestOrder%3E%3CclientID%3ETEST%3C%2FclientID%3E%3CcodSplitFee%3E1.0%3C%2FcodSplitFee%3E%3CcustomerId%3Ewxxs%3C%2FcustomerId%3E%3Cflag%3E1%3C%2Fflag%3E%3CgoodsValue%3E1.0%3C%2FgoodsValue%3E%3CinsuranceValue%3E1.0%3C%2FinsuranceValue%3E%3Citems%3E%3Citem%3E%3CitemName%3E%E5%95%86%E5%93%81%3C%2FitemName%3E%3CitemValue%3E0.0%3C%2FitemValue%3E%3Cnumber%3E2%3C%2Fnumber%3E%3C%2Fitem%3E%3C%2Fitems%3E%3ClogisticProviderID%3EYTO%3C%2FlogisticProviderID%3E%3CorderType%3E0%3C%2ForderType%3E%3Creceiver%3E%3Cname%3E%E6%94%B6%E4%BB%B6%E4%BA%BA%E5%A7%93%E5%90%8D%3C%2Fname%3E%3CpostCode%3E0%3C%2FpostCode%3E%3Cphone%3E021-12345678%3C%2Fphone%3E%3Cmobile%3E18112345678%3C%2Fmobile%3E%3Cprov%3E%E4%B8%8A%E6%B5%B7%E5%B8%82%3C%2Fprov%3E%3Ccity%3E%E4%B8%8A%E6%B5%B7%E5%B8%82%2C%E9%9D%92%E6%B5%A6%E5%8C%BA%3C%2Fcity%3E%3Caddress%3E%E5%8D%8E%E5%BE%90%E5%85%AC%E8%B7%AF3029%E5%BC%8428%E5%8F%B7%3C%2Faddress%3E%3C%2Freceiver%3E%3CsendEndTime%3E2020-08-10+10%3A24%3A08%3C%2FsendEndTime%3E%3CsendStartTime%3E2020-08-10+10%3A24%3A08%3C%2FsendStartTime%3E%3Csender%3E%3Cname%3E%E5%8F%91%E4%BB%B6%E4%BA%BA%E5%A7%93%E5%90%8D%3C%2Fname%3E%3CpostCode%3E526238%3C%2FpostCode%3E%3Cphone%3E021-12345678%3C%2Fphone%3E%3Cmobile%3E18112345678%3C%2Fmobile%3E%3Cprov%3E%E4%B8%8A%E6%B5%B7%E5%B8%82%3C%2Fprov%3E%3Ccity%3E%E4%B8%8A%E6%B5%B7%E5%B8%82%2C%E9%9D%92%E6%B5%A6%E5%8C%BA%3C%2Fcity%3E%3Caddress%3E%E5%8D%8E%E5%BE%90%E5%85%AC%E8%B7%AF3029%E5%BC%8428%E5%8F%B7%3C%2Faddress%3E%3C%2Fsender%3E%3CserviceType%3E8%3C%2FserviceType%3E%3Cspecial%3E1%3C%2Fspecial%3E%3CtotalServiceFee%3E1.0%3C%2FtotalServiceFee%3E%3CtxLogisticID%3ELP07082300225709%3C%2FtxLogisticID%3E%3C%2FRequestOrder%3E
//                &data_digest=Dqs6Wot2XgodnPW31bQprw%3D%3D&
//                clientId=TEST&type=online



            return HttpUtil.postJson(request.getUrl(), headers, jsonBody);
        }
    }


    private String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }private String urlDecode(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }


}
