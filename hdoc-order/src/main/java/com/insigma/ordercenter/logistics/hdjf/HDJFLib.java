package com.insigma.ordercenter.logistics.hdjf;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

public class HDJFLib {

	private YTLib ytLib =  new YTLib();
	private YTLib getYT(String userId,  String appKey,String appSecret) {
		ytLib = new YTLib(userId, appKey, appSecret);
		return ytLib;
	}

	public class YTLib{
		private String userId;
		private String appSecret;
		private String appKey;

		public YTLib(String userId,  String appKey,String appSecret) {
			this.userId = userId;
			this.appSecret = appSecret;
			this.appKey = appKey;
		}

		public YTLib() {

		}

		public String executeRoute(String url,String data) throws IOException {
//        private String appkey = "7670dec6";// (正式时让EDB分配)
//        private String appscret = "4b950f6e31494b9cbf398884e266133b";// (正式时让EDB分配)
			YTOClient client = new YTOClient(userId, appSecret);
			YTOPublicRequest request = new YTOPublicRequest();
			Map<String, String> params = new TreeMap<String, String>();
			request.setParams(params);
//      params.put("clientId", "TEST");
//        String source = "<ufinterface><Result><WaybillProcessInfo><Waybill_No>YT3114371303870</Waybill_No><Upload_Time>2020-03-04 14:08:04</Upload_Time><ProcessInfo>【广西省南宁市东葛公司】 已收件 取件人: 梁家磊 (18778052808)</ProcessInfo></WaybillProcessInfo><WaybillProcessInfo><Waybill_No>YT3114371303870</Waybill_No><Upload_Time>2020-03-05 06:08:00</Upload_Time><ProcessInfo>【南宁转运中心公司】 已收入</ProcessInfo></WaybillProcessInfo><WaybillProcessInfo><Waybill_No>YT3114371303870</Waybill_No><Upload_Time>2020-03-05 06:24:50</Upload_Time><ProcessInfo>【南宁转运中心公司】 已打包</ProcessInfo></WaybillProcessInfo><WaybillProcessInfo><Waybill_No>YT3114371303870</Waybill_No><Upload_Time>2020-03-05 06:25:12</Upload_Time><ProcessInfo>【南宁转运中心】 已发出 下一站 【成都转运中心公司】</ProcessInfo></WaybillProcessInfo></Result></ufinterface>";
//      params.put("logistics_interface",  source);
//       params.put("data_digest", YTODigestUtil.digest(source+"1QLlIZ"));
//      params.put("type", "online");
			// request.setBody("{\"data\":{\"content\":{\"branchId\":\"\",\"buyer\":\"\",\"collectMoneytype\":\"CNY\",\"collectSum\":\"12.00\",\"freight\":\"10.00\",\"id\":\"xfs2018031500002222333\",\"orderSum\":\"0.00\",\"orderType\":\"1\",\"otherCharges\":\"0.00\",\"packCharges\":\"1.00\",\"premium\":\"0.50\",\"price\":\"126.50\",\"quantity\":\"2\",\"receiver\":{\"address\":\"育德路XXX号\",\"area\":\"501022\",\"city\":\"四川省,XXX,XXXX\",\"company\":\"XXXX有限公司\",\"email\":\"yyj@abc.com\",\"id\":\"130520142097\",\"im\":\"yangyijia-abc\",\"mobile\":\"136*****321\",\"name\":\"XXX\",\"phone\":\"010-222***89\",\"zipCode\":\"610012\"},\"remark\":\"请勿摔货\",\"seller\":\"\",\"sender\":{\"address\":\"华新镇华志路XXX号\",\"area\":\"310118\",\"city\":\"上海,上海市,青浦区\",\"company\":\"XXXXX有限公司\",\"email\":\"ll@abc.com\",\"endTime\":1369033200000,\"id\":\"131*****010\",\"im\":\"1924656234\",\"mobile\":\"1391***5678\",\"name\":\"XXX\",\"phone\":\"021-87***321\",\"startTime\":1369022400000,\"zipCode\":\"610012\"},\"size\":\"12,23,11\",\"tradeId\":\"2701843\",\"type\":\"1\",\"typeId\":\"\",\"weight\":\"0.753\"},\"datetime\":\"2018-06-13 13:26:00\",\"partner\":\"test\",\"verify\":\"ZTO123\"}}");
			request.setUrl(url);
			// params.put("sign", "F0A6B40ED6BBC50D3572F46C61F02658");
			params.put("app_key", appKey);
//        params.put("Secret_Key", "1QLlIZ");
			params.put("format", "JSON");
			params.put("method", "yto.Marketing.WaybillTrace");
			params.put("timestamp", DateUtil.formatDateTime(new Date()));
			params.put("user_id", userId);
			params.put("v", "1");
			params.put("param",data);
			//  params.put("param", "<?xml  version=\"1.0\"?><ufinterface><Result><WaybillCode><Number>1111111111</Number></WaybillCode></Result></ufinterface>");

		return client.executeRoute(request);
		}
	}
	private EdbLib edbLib =  new EdbLib();

	public HDJFLib(String dbhost, String appkey, String appscret, String token, String serviceUrl) {
		edbLib.dbhost = dbhost;
		edbLib.appkey = appkey;
		edbLib.appscret = appscret;
		edbLib.token = token;
		edbLib.serviceUrl = serviceUrl;
	}

	public String edbRequstPost(EdbTradeGet edbTradeGet) {
		Map<String, String> params = edbLib.edbGetCommonParams("edbTradeGet");
		params.put("date_type", edbTradeGet.getDateType());
		params.put("begin_time", edbTradeGet.getBeginTime());
		params.put("end_time", edbTradeGet.getEndTime());
		params.put("import_mark", edbTradeGet.getImportMark());
		params.put("proce_Status", edbTradeGet.getProceStatus());
		params.put("page_no", edbTradeGet.getPageNo());
		params.put("order_status", edbTradeGet.getOrderStatus());
		params.put("page_size", edbTradeGet.getPageSize());
		params.put("payment_status", edbTradeGet.getPaymentStatus());
		params.put("productInfo_type", edbTradeGet.getProductInfoType());
		params.put("fields", "storage_id,tid,is_bill,invoice_name,taobao_delivery_status,taobao_delivery_method,order_process_time,is_break,breaker,break_time,break_explain,plat_send_status,plat_type,is_adv_sale,provinc_code,city_code,area_code,express_code,last_returned_time,last_refund_time,deliver_centre,deliver_station,is_pre_delivery_notice,jd_delivery_time,Sorting_code,cod_settlement_vouchernumber,total_num,big_marker,three_codes,distributing_centre_name,send_site_name,:child_storage_id,:child_tid,:child_out_tid,:child_pro_detail_code,:child_pro_name,:child_specification,:child_barcode,:child_combine_barcode,:child_iscancel,:child_isscheduled,:child_stock_situation,:child_isbook_pro,:child_iscombination,:child_isgifts,:child_gift_num,:child_book_storage,:child_pro_num,:child_send_num,:child_refund_num,:child_refund_renum,:child_inspection_num,:child_timeinventory,:child_cost_price,:child_sell_price");
		String res = edbLib.edbRequstPost(params);
		return res;
	}

	public String edbRequstPost(EdbTradeAdd bean) {
		Map<String, String> params = edbLib.edbGetCommonParams("edbTradeAdd");
		params.put("xmlValues", JSON.toJSONString(bean));
		String res = edbLib.edbRequstPost(params);
		return res;
	}

	public String edbRequstPost(EdbTradeCancel bean) {
//		EdbLib edb = new EdbLib();
		Map<String, String> params = edbLib.edbGetCommonParams("edbTradeCancel");
		params.put("xmlValues", JSON.toJSONString(bean));
		String res = edbLib.edbRequstPost(params);
		return res;
	}

	public String edbRequstPost(EdbTradeAudit bean) {
//		EdbLib edb = new EdbLib();
		Map<String, String> params = edbLib.edbGetCommonParams("edbTradeAudit");
		params.put("xmlValues", JSON.toJSONString(bean));
		String res = edbLib.edbRequstPost(params);
		return res;
	}

	private class EdbLib{
	public EdbLib(String dbhost, String appkey, String appscret, String token, String serviceUrl) {
		this.dbhost = dbhost;
		this.appkey = appkey;
		this.appscret = appscret;
		this.token = token;
		this.serviceUrl = serviceUrl;
	}

	private String dbhost = "edb_a87897";// 主账号(正式时让EDB分配)
	private String appkey = "7670dec6";// (正式时让EDB分配)
	private String appscret = "4b950f6e31494b9cbf398884e266133b";// (正式时让EDB分配)
	private String token = "0b34169b82824b05aa269fd74219027f";// (正式时让EDB分配)
	private String serviceUrl ="http://vip3013.edb09.net/rest/index.aspx"; //"http://59.111.98.152/rest/index.aspx";// 服务器地址(正式时让EDB分配)

	public EdbLib() {

	}
	//访问E店宝接口用到的信息如何获取？ --> http://vip13.edb08.com.cn/mongolog/EAOPHandler.ashx?_id=94b0473980374bfb9c8d395aec73dc67
	/**
	 * 获取公共参数
	 *
	 * @param method
	 *            接口名称
	 * @return 公共参
	 */
	public Map<String, String> edbGetCommonParams(String method) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("method", method);// 接口名称
		map.put("dbhost", this.dbhost);
		map.put("appkey", this.appkey);
//		map.put("format", "JSON");// 返回的数据格式
		map.put("format", "XML");// 返回的数据格式
		map.put("timestamp", new SimpleDateFormat("yyyyMMddHHmm").format(new Date()));// timestamp
//		map.put("timestamp", "201612251416");// timestamp
		// 全小写
		map.put("v", "2.0");// 版本号
		map.put("slencry", "1");//
		map.put("ip", "192.168.1.153");// 本机ip
		return map;
	}

	/**
	 * 开始请求
	 *
	 * @param params
	 *            参数(不要包含appscret和token)
	 * @return 服务回应
	 */
	public String edbRequstPost(Map<String, String> params) {
		HttpURLConnection connection = null;
		try {
			StringBuilder builder = new StringBuilder();
			for (String key : params.keySet()) {
				String val="xmlValues".equalsIgnoreCase(key)?URLEncoder.encode(params.get(key), "UTF-8"):params.get(key);
				builder.append(String.format("%1$s=%2$s&", key,  URLEncoder.encode(val, "UTF-8")));
			}
			builder.append("sign=" + edbSignature(params));
			System.out.println("服务地址 :" + this.serviceUrl);
			System.out.println("请求数据 :" + URLDecoder.decode(URLDecoder.decode(builder.toString(), "utf-8"), "utf-8"));
			System.out.println("实际发送 :" + builder);
			connection = (HttpURLConnection) new URL(this.serviceUrl).openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			connection.connect();
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
			out.write(builder.toString());
			out.flush();
			out.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			builder.setLength(0);
			String line = "";
			while ((line = reader.readLine()) != null)
				builder.append(line);
			reader.close();
			System.out.println("回应数据 :" + builder);
			return builder.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			if (connection != null)
				connection.disconnect();
		}
	}


	/**
	 * 签名
	 *
	 * @param params
	 *            参数
	 * @return 签名结果
	 */
	private String edbSignature(Map<String, String> params) {
		Map<String, String> treeMap = new TreeMap<String, String>(comparator);
		treeMap.putAll(params);
		treeMap.put("appscret", this.appscret);
		treeMap.put("token", this.token);
		// 拼接要签名的字符串
		StringBuilder builder = new StringBuilder(this.appkey);
		for (String key : treeMap.keySet()) {
			if ("".equals(key) || "".equals(treeMap.get(key)))
				continue;
			builder.append(key).append(treeMap.get(key));
		}
		System.out.println("签名明文:" + builder);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(builder.toString().getBytes("utf-8"));
			builder.setLength(0);
			for (byte b : bytes) {
				String hx = Integer.toHexString(b & 0XFF);
				builder.append(hx.length() == 1 ? "0" + hx : hx);
			}
			return builder.toString().toUpperCase();
		} catch (Exception e) {
			return "签名异常";
		}
	}



	/**
	 * 比较器
	 */
	private  Comparator<String> comparator = new Comparator<String>() {
		@Override
		public int compare(String k1, String k2) {
			return k1.compareToIgnoreCase(k2);
		}
	};}
}
