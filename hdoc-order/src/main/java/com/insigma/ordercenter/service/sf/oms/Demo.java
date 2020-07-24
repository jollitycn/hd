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
public class Demo {

	public static final int TIMEOUT = 30000;
	public static final String CHARSET = "UTF-8";
	public static final String AES256_KEY = "uG9NgFmNFH7BHqtHAFFZiTtsrWYD6HxK";// key
	public static final String MACSHA_512 = "Gmnf7y2TKMhCZ6lDWmsCOid3wRK3UMNp";// 盐
	public static final String REQUES_URL = "http://scs-oms2-bspwms.sit.sf-express.com/index.do?appId=111111&method=inbound&source=jialefuapptoken&appToken=jialefuapptoken&v=1.0&timestamp=123456789&signMethod=md5&sign=223&deviceToken=444&userToken=jialefu&format=json";
	//public static final String REQUES_URL = "http://localhost:8080/index.do?appId=111111&method=inbound&source=jialefuapptoken&appToken=jialefuapptoken&v=1.0&timestamp=123456789&signMethod=md5&sign=223&deviceToken=444&userToken=jialefu&format=json";
	/**
	 * 示例
	 * 
	 * @param source 业务报文
	 * @throws IOException
	 */
	public static void demo(String source,OMSServiceCode method) throws IOException {

		// STEP.1 业务报文urlencode
		String urlSource = URLEncoder.encode(source, "UTF-8");

		// STEP.2 业务报文加密 [注:AESCipher非线程安全]
		AESCipher aesciphe = new AESCipher(AES256_KEY.getBytes(CHARSET));
		String encrySource = aesciphe.getEncryptedMessage(urlSource);

		System.out.println("加密报文:" + encrySource);

		// STEP.3 生成摘要
		String sourceDiges = HmacSha512CoderFactory.getHmacSha512Coder(MACSHA_512, encrySource);

		// STEP.4 报文及摘要再次转码
		String encrySourceEncode = URLEncoder.encode(encrySource, CHARSET);
		String sourceDigesEncode = URLEncoder.encode(sourceDiges, CHARSET);

		// STEP.5 准备参数报文
		RequestBean request = new RequestBean(new RequestBean.Request(encrySourceEncode, sourceDigesEncode));

		String json = new Gson().toJson(request);

		System.err.println(json);

		// STEP.5 HTTP调用顺丰接口 [注:响应为明文]
		String response = post(REQUES_URL, json);
		System.out.println("远程接口响应:" + response);
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
		String source = "hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容:hello world!!@#$%^&*我中中文内容: page:";
		Demo.demo(source,OMSServiceCode.TRANSPORT);
	}
}

