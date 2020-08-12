package com.insigma.ordercenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.insigma.ordercenter.logistics.sf.qiao.QuerySFRoute;
import com.insigma.ordercenter.service.IHDJFService;
import hdjf.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class HDJFServiceImpl   implements IHDJFService {
	private String dbhost = "edb_a87897";// 主账号(正式时让EDB分配)
	private String appkey = "7670dec6";// (正式时让EDB分配)
	private String appscret = "4b950f6e31494b9cbf398884e266133b";// (正式时让EDB分配)
	private String token = "0b34169b82824b05aa269fd74219027f";// (正式时让EDB分配)
	private String serviceUrl ="http://vip3013.edb09.net/rest/index.aspx"; //"http://59.111.98.152/rest/index.aspx";// 服务器地址(正式时让EDB分配)

private HDJFLib edb = new HDJFLib(dbhost,appkey,appscret,token,serviceUrl);
	@Override
	/**
	 * 获取订单
	 */
	public  String edbTradeGet( EdbTradeGet edbTradeGet) {
		String  res = edb.edbRequstPost(edbTradeGet);
		return res;
	}
	@Override
	/**
	 * 添加订单
	 */
	public  String  edbTradeAdd( EdbTradeAdd bean) {
		String  res = edb.edbRequstPost(bean);
		return res;
	}
	@Override
	/**
	 * 订单作废
	 */
	public  String edbTradeCancel( EdbTradeCancel bean) {
		String  res = edb.edbRequstPost(bean);
		return res;
	}
	@Override
	/**
	 * 订单确认
	 */
	public  String edbTradeAudit(EdbTradeAudit bean) {
		String res = edb.edbRequstPost(bean);
		return res;
	}

	@Override
	public String searchZTKDRoutes ( String   number ) throws IOException {
		String companyId = "620c6cd377d944c79281d39665cc9fe5";
		ZopClient client = new ZopClient(companyId, "502f2527b3c6");
		ZopPublicRequest request = new ZopPublicRequest();
		Map<String, String> parameters = new HashMap<>();
		parameters.put("data", "['"+number+"']");
		parameters.put("company_id", companyId);
		parameters.put("msg_type", "NEW_TRACES");
		request.setParams(parameters);
		//   request.setBody("{\"data\":{\"content\":{\"branchId\":\"\",\"buyer\":\"\",\"collectMoneytype\":\"CNY\",\"collectSum\":\"12.00\",\"freight\":\"10.00\",\"id\":\"xfs2018031500002222333\",\"orderSum\":\"0.00\",\"orderType\":\"1\",\"otherCharges\":\"0.00\",\"packCharges\":\"1.00\",\"premium\":\"0.50\",\"price\":\"126.50\",\"quantity\":\"2\",\"receiver\":{\"address\":\"育德路XXX号\",\"area\":\"501022\",\"city\":\"四川省,XXX,XXXX\",\"company\":\"XXXX有限公司\",\"email\":\"yyj@abc.com\",\"id\":\"130520142097\",\"im\":\"yangyijia-abc\",\"mobile\":\"136*****321\",\"name\":\"XXX\",\"phone\":\"010-222***89\",\"zipCode\":\"610012\"},\"remark\":\"请勿摔货\",\"seller\":\"\",\"sender\":{\"address\":\"华新镇华志路XXX号\",\"area\":\"310118\",\"city\":\"上海,上海市,青浦区\",\"company\":\"XXXXX有限公司\",\"email\":\"ll@abc.com\",\"endTime\":1369033200000,\"id\":\"131*****010\",\"im\":\"1924656234\",\"mobile\":\"1391***5678\",\"name\":\"XXX\",\"phone\":\"021-87***321\",\"startTime\":1369022400000,\"zipCode\":\"610012\"},\"size\":\"12,23,11\",\"tradeId\":\"2701843\",\"type\":\"1\",\"typeId\":\"\",\"weight\":\"0.753\"},\"datetime\":\"2018-06-13 13:26:00\",\"partner\":\"test\",\"verify\":\"ZTO123\"}}");
		request.setUrl("https://japi-test.zto.com/traceInterfaceNewTraces");
		return client.execute(request);
	}

	@Override
	public String searchZTKYRoutes ( ZtoRequest querySFRoute ) throws IOException {
		ZtkClient client = new ZtkClient("zto", "zto123456");
		ZtkPublicRequest request = new ZtkPublicRequest();
		request.setUrl("http://183.134.7.6:18281/100020.zto");
		Map<String, String> params = new TreeMap<String, String>();
		String data = JSON.toJSONString(querySFRoute);
		params.put("data", data);
		params.put("msg_type", "QUERY_EWB_INFO_FS");
		request.setParams(params);
		return client.execute(request);
	}

	@Override
	public String searchYTRoutes ( String number ) throws IOException {

//        private String appkey = "7670dec6";// (正式时让EDB分配)
		YTOClient client = new YTOClient("YTOTEST", "1QLlIZ");
		YTOPublicRequest request = new YTOPublicRequest();
		Map<String, String> params = new TreeMap<String, String>();
		request.setParams(params);
//      params.put("clientId", "TEST");
//        String source = "<ufinterface><Result><WaybillProcessInfo><Waybill_No>YT3114371303870</Waybill_No><Upload_Time>2020-03-04 14:08:04</Upload_Time><ProcessInfo>【广西省南宁市东葛公司】 已收件 取件人: 梁家磊 (18778052808)</ProcessInfo></WaybillProcessInfo><WaybillProcessInfo><Waybill_No>YT3114371303870</Waybill_No><Upload_Time>2020-03-05 06:08:00</Upload_Time><ProcessInfo>【南宁转运中心公司】 已收入</ProcessInfo></WaybillProcessInfo><WaybillProcessInfo><Waybill_No>YT3114371303870</Waybill_No><Upload_Time>2020-03-05 06:24:50</Upload_Time><ProcessInfo>【南宁转运中心公司】 已打包</ProcessInfo></WaybillProcessInfo><WaybillProcessInfo><Waybill_No>YT3114371303870</Waybill_No><Upload_Time>2020-03-05 06:25:12</Upload_Time><ProcessInfo>【南宁转运中心】 已发出 下一站 【成都转运中心公司】</ProcessInfo></WaybillProcessInfo></Result></ufinterface>";
//      params.put("logistics_interface",  source);
//       params.put("data_digest", YTODigestUtil.digest(source+"1QLlIZ"));
//      params.put("type", "online");
		// request.setBody("{\"data\":{\"content\":{\"branchId\":\"\",\"buyer\":\"\",\"collectMoneytype\":\"CNY\",\"collectSum\":\"12.00\",\"freight\":\"10.00\",\"id\":\"xfs2018031500002222333\",\"orderSum\":\"0.00\",\"orderType\":\"1\",\"otherCharges\":\"0.00\",\"packCharges\":\"1.00\",\"premium\":\"0.50\",\"price\":\"126.50\",\"quantity\":\"2\",\"receiver\":{\"address\":\"育德路XXX号\",\"area\":\"501022\",\"city\":\"四川省,XXX,XXXX\",\"company\":\"XXXX有限公司\",\"email\":\"yyj@abc.com\",\"id\":\"130520142097\",\"im\":\"yangyijia-abc\",\"mobile\":\"136*****321\",\"name\":\"XXX\",\"phone\":\"010-222***89\",\"zipCode\":\"610012\"},\"remark\":\"请勿摔货\",\"seller\":\"\",\"sender\":{\"address\":\"华新镇华志路XXX号\",\"area\":\"310118\",\"city\":\"上海,上海市,青浦区\",\"company\":\"XXXXX有限公司\",\"email\":\"ll@abc.com\",\"endTime\":1369033200000,\"id\":\"131*****010\",\"im\":\"1924656234\",\"mobile\":\"1391***5678\",\"name\":\"XXX\",\"phone\":\"021-87***321\",\"startTime\":1369022400000,\"zipCode\":\"610012\"},\"size\":\"12,23,11\",\"tradeId\":\"2701843\",\"type\":\"1\",\"typeId\":\"\",\"weight\":\"0.753\"},\"datetime\":\"2018-06-13 13:26:00\",\"partner\":\"test\",\"verify\":\"ZTO123\"}}");
		request.setUrl("http://opentestapi.yto.net.cn/service/waybill_query/v1/Ut6CRr");
		// params.put("sign", "F0A6B40ED6BBC50D3572F46C61F02658");
		params.put("app_key", "sF1Jzn");
//        params.put("Secret_Key", "1QLlIZ");
		params.put("format", "JSON");
		params.put("method", "yto.Marketing.WaybillTrace");
		params.put("timestamp", DateUtils.dateToString(new Date()));
		params.put("user_id", "YTOTEST");
		params.put("v", "1");
		params.put("param", "[{\"Number\":\""+number+"\"}]");
		//  params.put("param", "<?xml  version=\"1.0\"?><ufinterface><Result><WaybillCode><Number>1111111111</Number></WaybillCode></Result></ufinterface>");

	 return client.executeRoute(request);
	}

//	@Override
//	public String route(QuerySFRoute querySFRoute) {
//		return null;
//	}

//	@Override
	/**
	 * 获取路由信息
	 */
	public  String route( EdbTradeAudit bean) {
		String res = edb.edbRequstPost(bean);
		return res;
	}

}
