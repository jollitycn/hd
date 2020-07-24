package com.insigma.ordercenter.logistics.best;

import cn.hutool.core.util.XmlUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.insigma.ordercenter.logistics.best.sdk.Client;
import com.insigma.ordercenter.logistics.best.sdk.getShippingOrderInfo.request.GetShippingOrderInfoReq;
import com.insigma.ordercenter.logistics.best.sdk.getShippingOrderInfo.request.ShippingOrders;
import com.insigma.ordercenter.logistics.best.sdk.getShippingOrderInfo.response.GetShippingOrderInfoRsp;
import com.insigma.ordercenter.logistics.best.sdk.tmsTraceQuery.request.TmsTraceQueryReq;
import com.insigma.ordercenter.logistics.best.sdk.tmsTraceQuery.response.TmsTraceQueryRsp;
import com.insigma.ordercenter.logistics.best.sdk.twCancelNotiry.request.TwCancelNotiryReq;
import com.insigma.ordercenter.logistics.best.sdk.twCancelNotiry.response.TwCancelNotiryRsp;
import com.insigma.ordercenter.logistics.best.sdk.twSoNotify.request.Item;
import com.insigma.ordercenter.logistics.best.sdk.twSoNotify.request.ItemList;
import com.insigma.ordercenter.logistics.best.sdk.twSoNotify.request.Receiver;
import com.insigma.ordercenter.logistics.best.sdk.twSoNotify.request.TwSoNotifyReq;
import com.insigma.ordercenter.logistics.best.sdk.twSoNotify.response.TwSoNotifyRsp;
import com.insigma.ordercenter.logistics.best.sdk.wmsSkuNotify.request.Product;
import com.insigma.ordercenter.logistics.best.sdk.wmsSkuNotify.request.Products;
import com.insigma.ordercenter.logistics.best.sdk.wmsSkuNotify.request.WmsSkuNotifyReq;
import com.insigma.ordercenter.logistics.best.sdk.wmsSkuNotify.response.WmsSkuNotifyRsp;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;

import javax.xml.xpath.XPathConstants;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

/**
 * 百世物流接口
 *
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/21 16:56
 */
 @Slf4j
public class BestUtil {

    public static void getSign(){
        String url = "http://183.129.172.49/eoms/api/process?serviceVersion=1.0&serviceType=SyncProductInfo&msgType=async&partnerKey=123456&sign=67ecefcc8b3398ccf4f7d7c25a9ccea1&msgId=368f237f-3663-4d29-bad1-a33abd648111&notifyUrl=&bizData=%3C%3Fxml+version%3D%221.0%22+encoding%3D%22UTF-8%22+standalone%3D%22yes%22%3F%3E%0A%3Crequest+xmlns%3Aloms%3D%22http%3A%2F%2Floms.800best.com%22%3E%0A++++%3CcustomerCode%3E85000231%3C%2FcustomerCode%3E%0A++++%3Cproducts%3E%0A++++++++%3Cproduct%3E%0A++++++++++++%3CskuCode%3ETEST888888%3C%2FskuCode%3E%0A++++++++++++%3CactionType%3EADD-OW%3C%2FactionType%3E%0A++++++++++++%3Cname%3E%E7%89%9B%E5%A5%B6%3C%2Fname%3E%0A++++++++++++%3CenglishName%3Emilk%3C%2FenglishName%3E%0A++++++++++++%3Ccategory%3E%E9%A3%9F%E5%93%81%3C%2Fcategory%3E%0A++++++++++++%3CbarCode%3ETEST9059595%3C%2FbarCode%3E%0A++++++++++++%3CserialNo%3E%3C%2FserialNo%3E%0A++++++++++++%3Cproperty%3E%E7%9B%92%E8%A3%85%3C%2Fproperty%3E%0A++++++++++++%3Cvolume%3E0.25%3C%2Fvolume%3E%0A++++++++++++%3Clength%3E20%3C%2Flength%3E%0A++++++++++++%3Cwidth%3E10%3C%2Fwidth%3E%0A++++++++++++%3Cheight%3E30%3C%2Fheight%3E%0A++++++++++++%3Cweight%3E0.25%3C%2Fweight%3E%0A++++++++++++%3Cunit%3E%E7%9B%92%3C%2Funit%3E%0A++++++++++++%3CpackageSpec%3E24%E7%9B%92%E4%B8%80%E7%AE%B1%3C%2FpackageSpec%3E%0A++++++++%3C%2Fproduct%3E%0A++++%3C%2Fproducts%3E%0A%3C%2Frequest%3E%0A&partnerId=WANLINIU&";
        String response = HttpUtil.post(url, new HashMap<>(0));
        log.info(response);

        Document read= XmlUtil.readXML(response);
        Object flag = XmlUtil.getByXPath("//response/flag", read, XPathConstants.STRING);

        if (!"SUCCESS".equals(flag)) {
            log.info("调用签名失败：{}", flag);
            return;
        }

        Object bizDataBObj = XmlUtil.getByXPath("//response/bizData", read, XPathConstants.STRING);
        String bizData= null;
        try {
            bizData = URLDecoder.decode(bizDataBObj.toString(),"UTF-8");
            log.info("调用成功，bizData={}", bizData);
        } catch (UnsupportedEncodingException e) {
            log.info("bizDataObj解析异常：{}", bizDataBObj);
            e.printStackTrace();
            return;
        }

        Document bizDataDoc = XmlUtil.readXML(bizData);

        Object value2 = XmlUtil.getByXPath("//flag", bizDataDoc, XPathConstants.STRING);
        Object value3 = XmlUtil.getByXPath("//note", bizDataDoc, XPathConstants.STRING);

        log.info("解析bizData.flag = {}", value2);
        log.info("解析bizData.note = {}", value3);

    }

    public static void twCancelNotiry(){
        Client client = new Client("http://183.129.172.49/ecapi/api/process", "FX-CS", "FXCS202005080001","json");
        TwCancelNotiryReq req = new TwCancelNotiryReq();
        req.setCustomerCode("FXNN");
        req.setOperationTypeCode("WDO");
        req.setOrderCode("CK123456");
        TwCancelNotiryRsp executed = client.executed(req);
        log.info("TwCancelNotiryRsp = {}", executed);
    }

    public static void twSoNotify(){
        Client client = new Client("http://183.129.172.49/ecapi/api/process", "FX-CS", "FXCS202005080001","json");
        TwSoNotifyReq req = new TwSoNotifyReq();
        req.setOperationFlag("W");
        req.setCustomerCode("FXNN");
        req.setOrderCode("TEST0001");
        req.setWarehouseCode("QIMEN");
        req.setActionType("ADD");
        req.setOperationTypeCode("WDO");

        Receiver receiver = new Receiver();
        receiver.setName("pjc");
        receiver.setProvince("江西省");
        receiver.setCity("赣州市");
        receiver.setDistrict("章贡区");
        receiver.setAddress("长征第一渡");
        req.setReceiver(receiver);

        ItemList itemList = new ItemList();
        List<Item> items = Lists.newArrayList();
        Item item = new Item();
        item.setLineNo(1);
        item.setItemSkuCode("WATER-01");
        item.setItemName("恒大冰泉");
        item.setItemQuantity(20);
        items.add(item);
        itemList.setItem(items);
        req.setItemList(itemList);

        TwSoNotifyRsp executed = client.executed(req);
        log.info("TwSoNotifyRep = {}", executed);
    }

    public static void wmsSkuNotify(){
        Client client = new Client("http://183.129.172.49/ecapi/api/process", "FX-CS", "FXCS202005080001","json");
        WmsSkuNotifyReq req = new WmsSkuNotifyReq();
        req.setProviderCode("FXNN");

        Products products = new Products();
        List<Product> productList = Lists.newArrayList();
        Product product = new Product();
        product.setItemSkuCode("WATER-01");
        product.setItemName("恒大冰泉");
        product.setActionType("ADD-OW");
        productList.add(product);
        products.setProduct(productList);
        req.setProducts(products);

        WmsSkuNotifyRsp executed = client.executed(req);
        log.info("WmsSkuNotifyRsp = {}", executed);
    }

    public static void tmsTraceQuery(){
        Client client = new Client("http://183.129.172.49/ecapi/api/process", "FX-CS", "FXCS202005080001","json");
        GetShippingOrderInfoReq req = new GetShippingOrderInfoReq();
        req.setCustomerCode("FXNN");
        req.setWarehouseCode("QIMEN");
        List<String> shippingOrderList = Lists.newArrayList();
        ShippingOrders shippingOrders = new ShippingOrders();
        shippingOrderList.add("PDD202007230008");
        shippingOrderList.add("PDD202007230007");
        shippingOrders.setShippingOrder(shippingOrderList);
        req.setShippingOrders(shippingOrders);


        log.info("入参GetShippingOrderInfoReq = {}", JSONUtil.toJsonStr(req));
        GetShippingOrderInfoRsp executed = client.executed(req);
        log.info("GetShippingOrderInfoRsp = {}", executed);
    }

    public static void main(String[] args) {
//        twCancelNotiry();
//        twSoNotify();
//        wmsSkuNotify();
            tmsTraceQuery();
    }
}
