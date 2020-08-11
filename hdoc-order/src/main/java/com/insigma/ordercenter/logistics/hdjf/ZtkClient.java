package com.insigma.ordercenter.logistics.hdjf;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class ZtkClient {
    private final ZtkProperties properties;

    public ZtkClient(ZtkProperties properties) {
        this.properties = properties;
    }

    public ZtkClient(String appKey, String appSecret) {
        this.properties = new ZtkProperties(appKey, appSecret);
    }

    public String execute(ZtkPublicRequest request) throws IOException {
        String jsonBody = request.getBody();
        if (jsonBody == null) {
            Map<String, String> params = request.getParams();
            StringBuilder queryBuilder = new StringBuilder();
            StringBuilder strToDigestBuilder = new StringBuilder();
            for (Map.Entry<String, String> e : params.entrySet()) {
                strToDigestBuilder.append(e.getKey()).append("=").append(e.getValue()).append("&");
                queryBuilder.append(ZtkDigestUtil.urlEncode(e.getKey())).append("=").append(ZtkDigestUtil.urlEncode(e.getValue())).append("&");
            }
            String queryString = queryBuilder.substring(0, queryBuilder.length() - 1);
            String strToDigest = strToDigestBuilder.substring(0, strToDigestBuilder.length() - 1);
            strToDigest = strToDigest + properties.getKey();
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("x-companyid", properties.getCompanyId());
            headers.put("x-datadigest", ZtkDigestUtil.digest(strToDigest));
//            System.out.println("url:" + request.getUrl());
//            System.out.println("headers:" + headers);
//            System.out.println("queryString:" + queryString);
            return HttpUtil.post(request.getUrl(), headers, queryString);
        } else {
            Map<String, String> headers = new HashMap<String, String>();
            String strToDigest = jsonBody + properties.getKey();
            headers.put("x-companyid", properties.getCompanyId());
            headers.put("x-datadigest", ZtkDigestUtil.digest(strToDigest));
            return HttpUtil.postJson(request.getUrl(), headers, jsonBody);
        }
    }


private static class ZtkDigestUtil {
    public static String digest(String str) {
        return Base64.encodeBase64String(DigestUtils.md5(str));
    }
    public static  String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }}


    private static class HttpUtil {
        private static final int DEFAULT_TIMEOUT = 3000;
        public static String post(String interfaceUrl, Map<String, String> headers, String queryString) throws IOException {
            URL url = new URL(interfaceUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            con.setDoOutput(true);
            con.setConnectTimeout(DEFAULT_TIMEOUT);
            con.setReadTimeout(DEFAULT_TIMEOUT);
            for (Map.Entry<String, String> e : headers.entrySet()) {
                con.setRequestProperty(e.getKey(), e.getValue());
            }
            DataOutputStream out = null;

            BufferedReader in = null;
            try {
                out = new DataOutputStream(con.getOutputStream());
                out.write(queryString.getBytes(Charset.forName("UTF-8")));
                out.flush();
                in = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "UTF-8"));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                return content.toString();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (Exception ignored) {
                    }
                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (Exception ignored) {
                    }
                }
            }
        }

        public static String postJson(String interfaceUrl, Map<String, String> headers, String json) throws IOException {
            URL url = new URL(interfaceUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            con.setDoOutput(true);
            con.setConnectTimeout(DEFAULT_TIMEOUT);
            con.setReadTimeout(DEFAULT_TIMEOUT);
            for (Map.Entry<String, String> e : headers.entrySet()) {
                con.setRequestProperty(e.getKey(), e.getValue());
            }
            DataOutputStream out = null;

            BufferedReader in = null;
            try {
                out = new DataOutputStream(con.getOutputStream());
                out.write(json.getBytes(Charset.forName("UTF-8")));
                out.flush();
                in = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "UTF-8"));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                return content.toString();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (Exception ignored) {
                    }
                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (Exception ignored) {
                    }
                }
            }
        }
    }
}
