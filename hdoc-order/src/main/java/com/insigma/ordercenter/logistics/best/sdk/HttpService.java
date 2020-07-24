package com.insigma.ordercenter.logistics.best.sdk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

public class HttpService {
    public static String sendPost(String url, Map<String, String> map) throws Exception {
        StringBuffer buffer = new StringBuffer(); //用来拼接参数
        StringBuffer result = new StringBuffer(); //用来接受返回值
        //创建URL
        URL httpUrl = new URL(url);
        //建立连接
        URLConnection connection = httpUrl.openConnection();
        connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        connection.setRequestProperty("connection", "keep-alive");
        connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
        String request = "";
        if(map != null && map.size()>0){
            for(String str : map.keySet()){
                buffer.append(str).append("=").append(URLEncoder.encode(map.get(str), "utf-8")).append("&");
            }
            //去掉最后一个&并urlencode
            request = buffer.toString().substring(0,buffer.toString().length() - 1);
        }
        printWriter.print(request);
        printWriter.flush();
        connection.connect();
        //接受连接返回参数
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }
        bufferedReader.close();
        return result.toString();
    }

    public static String sendPost(String url, Map<String, String> map, String body) throws Exception {
        StringBuffer buffer = new StringBuffer(); //用来拼接参数
        StringBuffer result = new StringBuffer(); //用来接受返回值
        String request = "";
        if(map != null && map.size()>0){
            for(String str : map.keySet()){
                buffer.append(str).append("=").append(URLEncoder.encode(map.get(str), "utf-8")).append("&");
            }
            //去掉最后一个&并urlencode
            request = buffer.toString().substring(0,buffer.toString().length() - 1);
        }

        //创建URL
        URL httpUrl = new URL(url + "?" + request);
        //建立连接
        URLConnection connection =  httpUrl.openConnection();
        connection.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
        connection.setDoOutput(true);
        connection.setDoInput(true);

        PrintWriter printWriter = new PrintWriter(connection.getOutputStream());

        printWriter.print(body);
        printWriter.flush();
        connection.connect();
        //接受连接返回参数
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }
        bufferedReader.close();
        return result.toString();
    }

}
