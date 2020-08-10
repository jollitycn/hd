package com.insigma.ordercenter.service.hdjf.edb;

import com.alibaba.fastjson.JSON;
import com.insigma.ordercenter.logistics.hdjf.edb.EdbTradeAdd;
import com.insigma.ordercenter.logistics.hdjf.edb.EdbTradeCancel;
import com.insigma.ordercenter.logistics.hdjf.edb.EdbTradeGet;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
public class HDJFEDBService {
	/**
	 * 获取订单
	 */
	public  String edbTradeGet(EdbTradeGet edbTradeGet) {
		//date_type、begin_time、end_time三个配合使用，比如 date_type=发货日期，begin_time=2017-05-01 00:00:00,end_time=2017-07-01 00:00:00 表示查询发货日期在2017-05-01 00:00:00与2017-07-01 00:00:00之间的订单

		//查询时符合条件的单据可能有很多条，为了获取所有符合条件的单据:
		//page_no从1开始查，若返回的单据个数（json的话item数组的长度；xml的话Rows标签的个数）等于page_size,则递增page_no接着查，直到返回的订单个数小于page_size


		EdbLib edb = new EdbLib();
		Map<String, String> params = edb.edbGetCommonParams("edbTradeGet");
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
		String res = edb.edbRequstPost(params);
		return res;
	}

	/**
	 * 添加订单
	 */
	public  String  edbTradeAdd(EdbTradeAdd bean) {
		EdbLib edb = new EdbLib();
		Map<String, String> params = edb.edbGetCommonParams("edbTradeAdd");
		params.put("xmlValues", JSON.toJSONString(bean));
		String res = edb.edbRequstPost(params);
		return res;
	}

	/**
	 * 订单作废
	 */
	public  String edbTradeCancel(EdbTradeCancel bean) {
		EdbLib edb = new EdbLib();
		Map<String, String> params = edb.edbGetCommonParams("edbTradeCancel");
		params.put("xmlValues", JSON.toJSONString(bean));
		String res = edb.edbRequstPost(params);
		return res;
	}

	/**
	 * 订单作废
	 */
	public  String edbTradeAudit(EdbTradeAudit bean) {
		EdbLib edb = new EdbLib();
		Map<String, String> params = edb.edbGetCommonParams("edbTradeAudit");
		params.put("xmlValues", JSON.toJSONString(bean));
		String res = edb.edbRequstPost(params);
		return res;
	}

// }
////	/**
////	 * 订单更新
////	 */
////	public static void edbTradeUpdate(  ) {
////		EdbLib edb = new EdbLib();
////		Map<String, String> params = edb.edbGetCommonParams("edbTradeUpdate");
////		StringBuilder builder = new StringBuilder();
////		builder.append("<info>");
////		builder.append("<orderInfo>");
////		builder.append("<tid>S1611110013488</tid>");
////		builder.append("<express>圆通快递</express>");
////		builder.append("<express_no>809304336767</express_no>");
////		builder.append("<printer>edb_a80258</printer>");
////		builder.append("<print_time>2016-11-12 10:50:55</print_time>");
////		builder.append("<inspecter>edb_a80258</inspecter>");
////		builder.append("<inspect_time>2016-11-12 10:50:55</inspect_time>");
////		builder.append("<delivery_operator>edb_a80258</delivery_operator>");
////		builder.append("<delivery_time>2016-11-12 10:50:55</delivery_time>");
////		builder.append("<GrossWeight>1.0</GrossWeight>");
////		builder.append("</orderInfo>");
////
////		builder.append("<product_info>");
////		builder.append("<product_item>");
////		builder.append("<tid>S1611110013488</tid>");
////		builder.append("<barCode>34210531064402</barCode>");
////		builder.append("<inspection_num>1</inspection_num>");
////		builder.append("</product_item>");
////
////		builder.append("<product_item>");
////		builder.append("<tid>S1611110013488</tid>");
////		builder.append("<barCode>44210530960602</barCode>");
////		builder.append("<inspection_num>1</inspection_num>");
////		builder.append("</product_item>");
////		builder.append("</product_info>");
////		builder.append("</info>");
////
////		params.put("xmlValues", builder.toString());
////		String res = edb.edbRequstPost(params);
////		// String res = edb.name(params);
////	}
//	/**
//	 * 订单批量发货
//	 */
//	public static void edbTradeDeliveryBatch() {
//		EdbLib edb = new EdbLib();
//		Map<String, String> params = edb.edbGetCommonParams("edbTradeDeliveryBatch");
//		StringBuilder builder = new StringBuilder();
//		builder.append("<info>");
//		//第1个订单
//		builder.append("<orderInfo>");
//		builder.append("<OrderCode>S1611110013488</OrderCode>");
//		builder.append("<express>圆通快递</express>");
//		builder.append("<express_no>809304336767</express_no>");
//		builder.append("<delivery_time>2016-11-12 10:50:55</delivery_time>");
//		builder.append("<weight>1.0</weight>");
//		builder.append("</orderInfo>");
//		//第2个订单
//		builder.append("<orderInfo>");
//		builder.append("<OrderCode>S1611110013489</OrderCode>");
//		builder.append("<express>圆通快递</express>");
//		builder.append("<express_no>809304336768</express_no>");
//		builder.append("<delivery_time>2016-11-12 10:50:55</delivery_time>");
//		builder.append("<weight>1.0</weight>");
//		builder.append("</orderInfo>");
//
//
//		builder.append("</info>");
//
//		params.put("xmlValues", builder.toString());
//		String res = edb.edbRequstPost(params);
//	}
//	/**
//	 * 添加盘点单
//	 */
//	public static void edbInventoryAdd() {
//		EdbLib edb = new EdbLib();
//		Map<String, String> params = edb.edbGetCommonParams("edbInventoryAdd");
//		StringBuilder builder = new StringBuilder();
//		builder.append("<info>");
//		builder.append("<orderInfo>");
//		builder.append("<checkOrderCode>PD23659</checkOrderCode>");
//		builder.append("<storage_no>30</storage_no>");
//		builder.append("<checkTime>2016-11-12 10:50:55</checkTime>");
//		builder.append("<checkType>年中盘点</checkType>");
//		builder.append("<remark>盘点说明</remark>");
//		builder.append("</orderInfo>");
//
//		builder.append("<product_info>");
//		builder.append("<product_item>");
//		builder.append("<checkOrderCode>PD23659</checkOrderCode>");
//		builder.append("<barCode>ALB015A</barCode>");
//		builder.append("<quantity>1</quantity>");
//		builder.append("</product_item>");
//
//		builder.append("<product_item>");
//		builder.append("<checkOrderCode>PD23659</checkOrderCode>");
//		builder.append("<barCode>ALB016A</barCode>");
//		builder.append("<quantity>1</quantity>");
//		builder.append("</product_item>");
//		builder.append("</product_info>");
//		builder.append("</info>");
//
//		params.put("xmlValues", builder.toString());
//		String res = edb.edbRequstPost(params);
//		// String res = edb.name(params);
//	}
//	/**
//	 * 快递更新（已过时）
//	 */
//	public static void edbExpressUpdate() {
//		EdbLib edb = new EdbLib();
//		Map<String, String> params = edb.edbGetCommonParams("edbExpressUpdate");
//		StringBuilder builder = new StringBuilder();
//		builder.append("<info>");
//		builder.append("<orderInfo>");
//		builder.append("<Orderid>S1707270002564</Orderid>");
//		builder.append("<Express>2</Express>");
//		builder.append("<storage_id>10</storage_id>");
//		builder.append("<Csmemo>大猫测试</Csmemo>");
//		builder.append("</orderInfo>");
//		builder.append("</info>");
//
//		params.put("xmlValues", builder.toString());
//		String res = edb.edbRequstPost(params);
//		// String res = edb.name(params);
//	}

//
//	/**
//	 * 获取退货订单信息
//	 */
//	public static void edbTradReturnGet() {
//		EdbLib edb = new EdbLib();
//		Map<String, String> params = edb.edbGetCommonParams("edbTradReturnGet");
//		// params.put("date_type", "退货到货日期");
//		params.put("start_time", "20160101");
//		params.put("end_time", "20160928");
//		// params.put("import_mark", "未导入");
//		// params.put("process_status", "处理中");
//		// params.put("return_status", "退货中");
//		// params.put("refund_status", "未退款");
//		params.put("page_no", "1");
//		params.put("page_size", "5");
//		String res = edb.edbRequstPost(params);
//	}
//	/**
//	 * 获取发票信息
//	 */
//	public static void edbInvoiceGet() {
//		EdbLib edb = new EdbLib();
//		Map<String, String> params = edb.edbGetCommonParams("edbInvoiceGet");
//		//params.put("begin_time", "2015-07-29 23:24:40");
//		//params.put("end_time", "2015-07-29 23:24:40");
//		//params.put("page_no", "1");
//		//params.put("page_size", "5");
//		String res = edb.edbRequstPost(params);
//	}
//	/**
//	 * 增加产品明细信息
//	 */
//	public static void edbProductDetailAdd() {
//		EdbLib edb = new EdbLib();
//		Map<String, String> params = edb.edbGetCommonParams("edbProductDetailAdd");
//		StringBuilder builder = new StringBuilder();
//		builder.append("<info>");
//		builder.append("<orderInfo>");
//		builder.append("<brand_name>麦富迪</brand_name>");
//		builder.append("<sort_name>猫湿粮SUNNY PATTAYA阳光芭堤亚</sort_name>");
//		builder.append("<supplier>山东麦富迪贸易发展有限公司</supplier>");
//		//builder.append("<productNo>LWGW00010</productNo>");
//		builder.append("<product_name>阳光芭堤亚浓汁吞拿鱼+鸡肉</product_name>");
//		builder.append("<market_price>0</market_price>");
//		builder.append("<retail_price>0</retail_price>");
//		builder.append("<product_intro>1</product_intro>");
//		builder.append("<factory_item>1</factory_item>");
//		builder.append("<wfpid>192.168.0.10</wfpid>");
//		builder.append("</orderInfo>");
//
//		builder.append("<detailInfo>");
//		builder.append("<detail_item>");
//		builder.append("<bar_code>6958862109529</bar_code>");
//		builder.append("<specification>60g</specification>");
//		//builder.append("<color>袋</color>");
//		builder.append("<size>1.0</size>");
//		builder.append("<unit>袋</unit>");
//		builder.append("<weight>0.06</weight>");
//		builder.append("<contrast_purchase_price>1.0</contrast_purchase_price>");
//		//builder.append("<product_status>正常</product_status>");
//		builder.append("<sell_price>0</sell_price>");
//
//		builder.append("</detail_item>");
//		builder.append("</detailInfo>");
//		builder.append("</info>");
//
//		params.put("xmlValues", builder.toString());
//		String res = edb.edbRequstPost(params);
//
//	}
	/**
	 * 获取产品基本产品信息 根据产品启用的开始结束时间和产品条码，编号等信息获取产品基本信息，不包括库存
	 */
	public static void edbProductBaseInfoGet() {
		EdbLib edb = new EdbLib();
		Map<String, String> params = edb.edbGetCommonParams("edbProductBaseInfoGet");
		params.put("BarCode", "Edb0092");

		String res = edb.edbRequstPost(params);
		String s = "";
	}
	/**
//	 * 获取E店宝启用的品牌信息
//	 */
//	public static void edbProductBrandGet() {
//		EdbLib edb = new EdbLib();
//		Map<String, String> params = edb.edbGetCommonParams("edbProductBrandGet");
//		//params.put("brand_code", "W60508");
//		//params.put("brand_name", "W60508");
//		//params.put("enable", "W60508");
//
//		String res = edb.edbRequstPost(params);
//		String s = "";
//	}
//	/**
//	 * 获取产品分类信息
//	 */
//	public static void edbProductClassGet() {
//		EdbLib edb = new EdbLib();
//		Map<String, String> params = edb.edbGetCommonParams("edbProductClassGet");
//		//params.put("class_code", "98");//EDB系统分类编号
//		params.put("class_name", "食品生鲜");
//		//params.put("sort_type", "产品");
//		//params.put("is_suit", "0");
//		//params.put("store_name", "ETOP顺丰仓");
//		//params.put("get_child_nodes", "true");
//		String res = edb.edbRequstPost(params);
//		String s = "";
//	}
//	/**
//	 * 获取产品库存信息
//	 * 没有按库存变动时间查询的方法。一般业务下每隔5~10分钟需要查询一遍所有商品的库存
//	 */
//	public static void edbProductGet() {
//		EdbLib edb = new EdbLib();
//		Map<String, String> params = edb.edbGetCommonParams("edbProductGet");
//		params.put("date_type", "建档日期");//这个参数影响begin_time 和  end_time
//		params.put("begin_time", "2012-01-01 00:00"); //date_type 大于等于begin_time
//		params.put("end_time", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));//date_type 大于等于end_time
//		//params.put("product_no", "PN000036");
//		//params.put("brand_no", "28");	//EDB系统品牌编号
//		//params.put("standard", "规格");
//		params.put("bar_code", "SKYWORTH-00000030");
//		//params.put("sort_no", "96");	//	EDB系统分类编号
//		//params.put("store_id", "1,2,3");
////		params.put("isuit", "0");
////		params.put("iscut_store", "1");
//		params.put("page_no", "1");
//		params.put("page_size", "10");
//		String res = edb.edbRequstPost(params);
//		String s = "";
//	}
//	/**
//	 * 更新主产品信息
//	 */
//	public static void edbProductBaseInfoUpdate() {
//		EdbLib edb = new EdbLib();
//		Map<String, String> params = edb.edbGetCommonParams("edbProductBaseInfoUpdate");
//		params.put("productId", "436");
//		params.put("brand_name", "天元");
//		params.put("productNo", "LWH-0006");
//		params.put("product_name", "软底鞋");
//		String res = edb.edbRequstPost(params);
//		String s = "";
//	}
//	/**
//	 * 更新单据导入标记为 已导入
//	 */
//	public static void edbTradeImportStatusUpdate() {
//		EdbLib edb = new EdbLib();
//		Map<String, String> params = edb.edbGetCommonParams("edbTradeImportStatusUpdate");
//		params.put("num_id", "S1512020000001,S1512020000002:批注");
//		params.put("tid_type", "Order");
//
//		String res = edb.edbRequstPost(params);
//		String s = "";
//	}
//	/**
//	 * 	更新物流信息
//	 */
//	public static void edbLogisticsCompaniesUpdate() {
//		EdbLib edb = new EdbLib();
//		Map<String, String> params = edb.edbGetCommonParams("edbLogisticsCompaniesUpdate");
//		params.put("tid", "S1512020000001");//E店宝订单号
//		params.put("express", "圆通快递");
//		params.put("express_deliveryno", "809173667727");
//		params.put("delivery_time", "2016-11-13 18:20:10");
//		params.put("weight", "0");
//
//		String res = edb.edbRequstPost(params);
//		String s = "";
////	}
//	/**
//	 * 增加一个产品分类
//	 */
//	public static void edbProductClassAdd(){
//		EdbLib edb = new EdbLib();
//		Map<String, String> params = edb.edbGetCommonParams("edbProductClassAdd");
//		StringBuilder builder = new StringBuilder();
//		builder.append("<info>");
//		builder.append("<orderInfo>");
//		builder.append("<sort_name>终端品</sort_name>");
//		builder.append("<OutSortNO>HC-037</OutSortNO>");
//		builder.append("<sort_type>产品</sort_type>");
//		builder.append("</orderInfo>");
//		builder.append("</info>");
//
//		params.put("xmlValues", builder.toString());
//		String res = edb.edbRequstPost(params);
//		String s = "";
//	}
//	/**
//	 * 增加一个产品品牌
//	 */
//	public static void edbProductBrandAdd(){
//		EdbLib edb = new EdbLib();
//		Map<String, String> params = edb.edbGetCommonParams("edbProductBrandAdd");
//		StringBuilder builder = new StringBuilder();
//		builder.append("<order>");
//		builder.append("<orderInfo>");
//		builder.append("<brand_name>Zimmerli齐穆里2016</brand_name>");//品牌名称
//		builder.append("<OutBrandNO>Z005</OutBrandNO>");
//		builder.append("<Cdescript></Cdescript>");
//		builder.append("<Cremark></Cremark>");
//		builder.append("<Is_active>1</Is_active>");
//		builder.append("</orderInfo>");
//		builder.append("</order>");
//
//		params.put("xmlValues", builder.toString());
//		String res = edb.edbRequstPost(params);
//		String s = "";
//	}
//	/**
//	 * 更新产品分类
//	 */
//	public static void edbProductClassUpdate() {
//		EdbLib edb = new EdbLib();
//		Map<String, String> params = edb.edbGetCommonParams("edbProductClassUpdate");
//		params.put("sort_name", "S1512020000001");
//		params.put("sort_no", "10");
//		// params.put("parent_sort_no", "Order");
//		params.put("packing_charges", "1");
//		params.put("rough_weight_ratio", "0");
//		params.put("sort_type ", "产品");
//		params.put("storeId ", "2");
//		params.put("is_pack ", "0");
//		params.put("is_suit ", "0");
//		String res = edb.edbRequstPost(params);
//		String s = "";
//	}
//	/**
//	 * 添加入库单
//	 */
//	public static void edbInStoreAdd() {
//		EdbLib edb = new EdbLib();
//		Map<String, String> params = edb.edbGetCommonParams("edbInStoreAdd");
//		StringBuilder builder = new StringBuilder();
//		builder.append("<info>");
//		builder.append("<orderInfo>");
//		builder.append("<instorage_no>20549778</instorage_no>");
//		builder.append("<instorage_type>正常入库</instorage_type>");
//		builder.append("<instorage_time>2016-10-20 09:54</instorage_time>");
//		builder.append("<storage_no>1</storage_no>");
//		builder.append("<supplier_no>1</supplier_no>");
//		builder.append("<delivery_no>EMS1265489</delivery_no>");
//		builder.append("<cost>20</cost>");
//		builder.append("<procure_cost>10</procure_cost>");
//		builder.append("<other_cost>5</other_cost>");
//		builder.append("<pact_totalAmount>100</pact_totalAmount>");
//		builder.append("<out_pactNo>cs390233837</out_pactNo>");
//		builder.append("<WL_company>无菌物流</WL_company>");
//		builder.append("<express_no>1235345345</express_no>");
//		builder.append("</orderInfo>");
//
//		builder.append("<orderInfo>");
//		builder.append("<instorage_no>20549779</instorage_no>");
//		builder.append("<instorage_type>正常入库</instorage_type>");
//		builder.append("<instorage_time>2016-10-20 09:54</instorage_time>");
//		builder.append("<storage_no>1</storage_no>");
//		builder.append("<supplier_no>1</supplier_no>");
//		builder.append("<delivery_no>EMS1265489</delivery_no>");
//		builder.append("<cost>20</cost>");
//		builder.append("<procure_cost>10</procure_cost>");
//		builder.append("<other_cost>5</other_cost>");
//		builder.append("<pact_totalAmount>100</pact_totalAmount>");
//		builder.append("<out_pactNo>cs390233837</out_pactNo>");
//		builder.append("<WL_company>无菌物流</WL_company>");
//		builder.append("<express_no>1235345345</express_no>");
//		builder.append("</orderInfo>");
//
//		builder.append("<product_info>");
//		builder.append("<product_item>");
//		builder.append("<instorage_no>20549778</instorage_no>");
//		builder.append("<productItem_no>1</productItem_no>");
//		builder.append("<instorage_num>10</instorage_num>");
//		builder.append("<storage_no>1</storage_no>");
//		builder.append("<batch>10000</batch>");
//		builder.append("<expire_Time>2017-06-20</expire_Time>");
//		builder.append("</product_item>");
//
//		builder.append("<product_item>");
//		builder.append("<instorage_no>20549779</instorage_no>");
//		builder.append("<productItem_no>2</productItem_no>");
//		builder.append("<instorage_num>2</instorage_num>");
//		builder.append("<storage_no>1</storage_no>");
//		builder.append("<batch>100001</batch>");
//		builder.append("<expire_Time>2017-06-01</expire_Time>");
//		builder.append("</product_item>");
//
//		builder.append("</product_info>");
//		builder.append("</info>");
//
//		params.put("xmlValues", builder.toString());
//		String res = edb.edbRequstPost(params);
//
//	}
//	/**
//	 * 退货单导入接口
//	 */
//	public static void edbReturnStoreAdd() {
//		EdbLib edb = new EdbLib();
//		Map<String, String> params = edb.edbGetCommonParams("edbReturnStoreAdd");
//		StringBuilder builder = new StringBuilder();
//		builder.append("<corder>");
//		builder.append("<corderInfo>");
//		builder.append("<ordernum>20549778</ordernum>");
//		builder.append("<wresingnum>89642354865122</wresingnum>");
//		builder.append("<stoaffirm>0</stoaffirm>");
//		builder.append("<expressnum>981632</expressnum>");
//		builder.append("<expresscom>圆通</expresscom>");
//		builder.append("<retime>2017-06-10 10:00</retime>");
//		builder.append("</corderInfo>");
//
//		builder.append("<corderInfo>");
//		builder.append("<ordernum>20549779</ordernum>");
//		builder.append("<wresingnum>89642354865123</wresingnum>");
//		builder.append("<stoaffirm>0</stoaffirm>");
//		builder.append("<expressnum>981632</expressnum>");
//		builder.append("<expresscom>圆通</expresscom>");
//		builder.append("<retime>2017-06-10 10:00</retime>");
//		builder.append("</corderInfo>");
//
//		builder.append("<rproductInfo>");
//		builder.append("<rproduct_item>");
//		builder.append("<barcode>20549778</barcode>");
//		builder.append("<wresingnum>89642354865122</wresingnum>");
//		builder.append("<pronum>1</pronum>");
//		builder.append("<reamount>100.00</reamount>");
//		builder.append("</rproduct_item>");
//
//		builder.append("<rproduct_item>");
//		builder.append("<barcode>20549778</barcode>");
//		builder.append("<wresingnum>89642354865123</wresingnum>");
//		builder.append("<pronum>1</pronum>");
//		builder.append("<reamount>120.00</reamount>");
//		builder.append("</rproduct_item>");
//
//		builder.append("</rproductInfo>");
//		builder.append("</corder>");
//
//		params.put("xmlValues", builder.toString());
//		String res = edb.edbRequstPost(params);
//
//	}
//	/**
//	 * 确认入库单
//	 */
//	public static void edbInStoreConfirm() {
//		EdbLib edb = new EdbLib();
//		Map<String, String> params = edb.edbGetCommonParams("edbInStoreConfirm");
//		params.put("inStorage_no", "7026899788-702689996");
//		params.put("freight", "5.00");
//
//		String res = edb.edbRequstPost(params);
//		String s = "";
//	}
//	/**
//	 * 订单导入待发货
//	 */
//	public static void edbReadySynDeliveries() {
//		EdbLib edb = new EdbLib();
//		Map<String, String> params = edb.edbGetCommonParams("edbReadySynDeliveries");
//		StringBuilder builder = new StringBuilder();
//		builder.append("<order>");
//		builder.append("<orderInfo>");
//		builder.append("<order_no>SO1708070002056</order_no>");
//		builder.append("<express>韵达</express>");
//		builder.append("<express_no>SO1708070002056</express_no>");
//		builder.append("<consignee>王菁雯</consignee>");
//		builder.append("<delivery_time>2017-08-08 10:00</delivery_time>");
//		builder.append("<package_num>1</package_num>");
//		builder.append("</orderInfo>");
//
////		builder.append("<orderInfo>");
////		builder.append("<order_no>SO1708080001778</order_no>");
////		builder.append("<express>韵达</express>");
////		builder.append("<express_no>SO1708080001778</express_no>");
////		builder.append("<consignee>王菁雯</consignee>");
////		builder.append("<delivery_time>2017-08-08 14:00</delivery_time>");
////		builder.append("<package_num>1</package_num>");
////		builder.append("</orderInfo>");
//
//		builder.append("</order>");
//
//		params.put("xmlValues", builder.toString());
//		String res = edb.edbRequstPost(params);
//
//	}
//	/*
//	 * 查询待发货订单信息
//	 */
//	public static void edbGetSynDeliveries() {
//		//查询时符合条件的单据可能有很多条，为了获取所有符合条件的单据:
//		//page_no从1开始查，若返回的单据个数（json的话item数组的长度；xml的话Rows标签的个数）等于page_size,则递增page_no接着查，直到返回的订单个数小于page_size
//
//		EdbLib edb = new EdbLib();
//		Map<String, String> params = edb.edbGetCommonParams("edbGetSynDeliveries");
//		params.put("begin_time", "2017-01-01");
//		params.put("end_time", "2017-08-15");
//		params.put("page_no", "1");
//		params.put("page_size", "200");
//		//params.put("order_nos", "S1512020000110,S1702080001139");//EDB单号(总长度2000个字符)EDB单号与平台单号都传时只取EDB单号
//		params.put("out_order_nos", "99998887776777,170206-356617403521");//平台单号(总长度2000个字符)EDB单号与平台单号都传时只取EDB单号
//		//params.put("fields", "order_no,out_order_no");//指定返回字段
//		String res = edb.edbRequstPost(params);
//
//	}
//	/// <summary>
//	/// 通知订单
//	/// </summary>
//	private static void edbNotifyTrade()//EdbNotifyTrade bean)
//	{
//		EdbLib edb = new EdbLib();
//		Map<String, String>params = edb.edbGetCommonParams("edbNotifyTrade");
//		StringBuilder builder = new StringBuilder();
//		builder.append("<info>");
//		builder.append("<orderInfo>");
//		builder.append("<bizOrderId>WZ201804270001</bizOrderId>");//好衣库单号
//		builder.append("<bizOrderPushType>部分退款</bizOrderPushType>");//订单推送类型
//		builder.append("</orderInfo>");
//		//第2单
//		builder.append("<orderInfo>");
//		builder.append("<bizOrderId>WZ201804270002</bizOrderId>");//好衣库单号
//		builder.append("<bizOrderPushType>地址变更</bizOrderPushType>");//订单推送类型
//		builder.append("</orderInfo>");
//		builder.append("</info>");
//		params.put("xmlValues", builder.toString());
//		String res = edb.edbRequstPost(params);
//	}
}
