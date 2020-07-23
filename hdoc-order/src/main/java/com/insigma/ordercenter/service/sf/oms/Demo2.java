package com.insigma.ordercenter.service.sf.oms;

import java.io.IOException;
import java.net.URLEncoder;

import com.insigma.ordercenter.logistics.sf.oms.AESCipher;
import com.insigma.ordercenter.logistics.sf.oms.HmacSha512CoderFactory;
import com.insigma.ordercenter.logistics.sf.oms.RequestBean;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

/**
 * 顺丰订单接口调用DEMO
 * 
 * @author 01377259 
 * 2018年9月27日
 */
public class Demo2 {

	public static final int TIMEOUT = 30000;
	public static final String CHARSET = "UTF-8";
	public static final String AES256_KEY = "alcTaVtXvKtuOfc9ZyC8rEc8j6Hzogmg";// key
	public static final String MACSHA_512 = "W0b4XVyNzMaO9u0QZ3KwilcVbeAv6sN6";// 盐

	//public static final String REQUES_URL = "http://scs-oms2-bspwms.sit.sf-express.com/index.do?appId=111111&method=inbound&source=jialefuapptoken&appToken=jialefuapptoken&v=1.0&timestamp=123456789&signMethod=md5&sign=223&deviceToken=444&userToken=jialefu&format=json";
	public static final String REQUES_URL = "https://scs-oms2-bspwms.sit.sf-express.com:45316/index.do?appId=111111&method=transport&source=yunnanxianhua&appToken=yunnanxianhuaapptoken&v=1.0&timestamp=123456789&userToken=yunnanxianhuatoken";
	//public static final String REQUES_URL = "http://localhost:8080/index.do?appId=111111&method=inbound&source=jialefuapptoken&appToken=jialefuapptoken&v=1.0&timestamp=123456789&signMethod=md5&sign=223&deviceToken=444&userToken=jialefu&format=json";
	/**
	 * 示例
	 * 
	 * @param source 业务报文
	 * @throws IOException
	 */
	public static void demo(String source) throws IOException {
		
		// STEP.1 业务报文urlencode
		String urlSource = URLEncoder.encode(source, "UTF-8");
		
		// STEP.2 业务报文加密 [注:AESCipher非线程安全]
		AESCipher aesciphe = new AESCipher(AES256_KEY.getBytes(CHARSET));
		String encrySource = aesciphe.getEncryptedMessage(urlSource);
		
		System.out.println("加密报文:"+encrySource);

		// STEP.3 生成摘要
		String sourceDiges = HmacSha512CoderFactory.getHmacSha512Coder(MACSHA_512, encrySource);

		// STEP.4 报文及摘要再次转码
		String encrySourceEncode = URLEncoder.encode(encrySource, CHARSET);
		String sourceDigesEncode = URLEncoder.encode(sourceDiges, CHARSET);

		// STEP.5 准备参数报文
		RequestBean request = new RequestBean(new RequestBean.Request(encrySourceEncode, sourceDigesEncode));

		String json = new Gson().toJson(request);

		// STEP.5 HTTP调用顺丰接口 [注:响应为明文]
		String response = post(REQUES_URL, json);

		System.out.println("远程接口响应:"+response);
	}

	/**
	 * HTTP -> POST
	 * 
	 * @param url
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, String param) {
		String result = null;
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			httpclient = HttpClients.createDefault();

			HttpPost postmethod = new HttpPost(url);

			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(TIMEOUT).setConnectTimeout(TIMEOUT).setSocketTimeout(TIMEOUT)
					.build();
			postmethod.setConfig(requestConfig);
			postmethod.addHeader("content-type", "application/json");
			postmethod.setEntity(new StringEntity(param, CHARSET));

			response = httpclient.execute(postmethod);

			int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		String source = "{\r\n" +
				"	\"orderItems\": [{\r\n" +
//				"		\"monthlyAccount\": \"7550612539\",\r\n" +
				"		\"temperatureLevelName\": \"0至10\",\r\n" +
				"		\"remark\": \"这是备注\",\r\n" +
				"		\"skuName\": \"维生素C咀嚼片\",\r\n" +
				"		\"quantity\": \"50\",\r\n" +
				"		\"grossWeight\": \"12\",\r\n" +
				"		\"volume\": \"12\"\r\n" +
				"	}],\r\n" +
				"	\"erpOrder\": \"1233\",\r\n" +
//				"	\"monthlyAccount\": \"7550612539\",\r\n" +
				"	\"consigneeProvinceName\": \"广东省\",\r\n" +
				"	\"paymentTypeCode\": \"PR_ACCOUNT\",\r\n" +
				"	\"shipperLocationName\": \"宝安M17大厦A栋07\",\r\n" +
				"	\"shipperProvinceName\": \"广东省\",\r\n" +
				"	\"shipperContactName\": \"奥特曼\",\r\n" +
				"	\"shipperCityName\": \"深圳市\",\r\n" +
				"	\"consigneeLocationName\": \"广东省广州市南山区深圳南山深南大道58号\",\r\n" +
				"	\"extenSystemOrderNo\": \"A00000002\",\r\n" +
				"	\"shipperName\": \"M17星制药\",\r\n" +
				"	\"consigneeCityName\": \"广州市\",\r\n" +
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

		source = CallExpressServiceTools.packageMsgData(OMSServiceCode.TRANSPORT);
		Demo2.demo(source);		
	}
}

