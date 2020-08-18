package com.insigma.ordercenter.logistics;

import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.dto.CommonConsigneeDTO;
import com.insigma.ordercenter.entity.dto.CommonConsignorDTO;
import com.insigma.ordercenter.entity.dto.CommonProductDTO;
import com.insigma.ordercenter.entity.vo.ProductDetailVO;
import com.insigma.ordercenter.logistics.best.BestApi;
import com.insigma.ordercenter.logistics.best.sdk.getShippingOrderInfo.request.GetShippingOrderInfoReq;
import com.insigma.ordercenter.logistics.best.sdk.getShippingOrderInfo.request.ShippingOrders;
import com.insigma.ordercenter.logistics.best.sdk.getShippingOrderInfo.response.GetShippingOrderInfoRsp;
import com.insigma.ordercenter.logistics.best.sdk.twSoNotify.request.*;
import com.insigma.ordercenter.logistics.best.sdk.twSoNotify.response.TwSoNotifyRsp;
import com.insigma.ordercenter.logistics.sf.qiao.*;
import com.insigma.ordercenter.logistics.zjs.ZjsApi;
import com.insigma.ordercenter.logistics.zjs.express.ZjsRequestData;
import com.insigma.ordercenter.logistics.zjs.express.ZjsReuslt;
import com.insigma.ordercenter.service.IBestService;
import com.insigma.ordercenter.service.IExpressCancelService;
import com.insigma.ordercenter.service.IJingdongServer;
import com.insigma.ordercenter.service.sf.qiao.APIResponse;
import com.insigma.ordercenter.service.sf.qiao.EspServiceCode;
import com.insigma.ordercenter.service.sf.qiao.QiaoAPIService;
import com.insigma.ordercenter.utils.SpringContextUtils;
import com.jd.open.api.sdk.request.etms.LdopReceiveTraceGetRequest;
import com.jd.open.api.sdk.response.etms.LdopReceiveTraceGetResponse;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: hdoc-parent
 * @description: 物流中心
 * @author: XuChao
 * @create: 2020-08-03 15:30
 **/
public class LogisticsCentre {

    private static IJingdongServer iJingdongServer;

    private static IBestService iBestService;

    private static IExpressCancelService expressCancelService;

    static {
        iJingdongServer = SpringContextUtils.getBean(IJingdongServer.class);
        iBestService = SpringContextUtils.getBean(IBestService.class);
        expressCancelService = SpringContextUtils.getBean(IExpressCancelService.class);
    }

    /**
         * 统一下单接口
         * @param shippingOrderNo 发货单单号
         * @param commonProduct 通用货物封装类
         * @param commonConsignee 通用收件人封装类
         * @param commonConsignor 通用发件人封装类
         * @param logisticsType 1顺丰速运 2百世汇通 3宅急送 4京东
         * @return
         */
    public static Result generateLogistics(String shippingOrderNo,
                                           CommonProductDTO commonProduct,
                                           CommonConsigneeDTO commonConsignee ,
                                           CommonConsignorDTO commonConsignor,
                                           int logisticsType) throws Exception {
        //判断物流类型，调用对应接口下单
        switch (logisticsType){
            case 1:
                //转换参数
                Order sfParam=transformationSfParam(shippingOrderNo,commonProduct,commonConsignee,commonConsignor);

                //执行顺丰下单接口
                APIResponse apiresponse=QiaoAPIService.query(EspServiceCode.EXP_RECE_CREATE_ORDER,sfParam);

                //格式化返回结果
                String resultStr=(String) apiresponse.getApiResultData();
                OrderFilterResponse sfResult=JSONUtil.toBean(resultStr,OrderFilterResponse.class);

                if(sfResult.isSuccess()){
                    //如果成功，返回顺丰订单号
                    return Result.success(sfResult.getMsgData().getWaybillNoInfoList().get(0).getWaybillNo());
                }else{
                    //将发货单置为异常状态，并记录异常原因
                    return Result.error(CodeMsg.CREATE_LOGISTICS_ERROR,sfResult.getErrorMsg());
                }

            case 2:
                //转换参数
                TwSoNotifyReq twSoNotifyReq=transformationBestParam(shippingOrderNo,commonProduct,commonConsignee,commonConsignor);

                //执行百世下单接口
                TwSoNotifyRsp executed= BestApi.twSoNotify(twSoNotifyReq);

                System.out.println(executed);

                //TODO 调用一直失败
                if(executed.isResult()){
                    //如果成功，返回百世订单号
                    return Result.success("333");
                }else{
                    //将发货单置为异常状态，并记录异常原因
                    return Result.error(CodeMsg.CREATE_LOGISTICS_ERROR,executed.getErrorDescription());
                }

            case 3:
                //获取ZJS下单单号
                String zjsOrder=ZjsApi.queryOrder();

                //宅急送参数转换
                ZjsRequestData zjsRequestData=transformationZjsParam(shippingOrderNo,commonProduct,commonConsignee,commonConsignor);
                zjsRequestData.setMailNo(zjsOrder);

                //宅急送下单
                ZjsReuslt zjsReuslt=ZjsApi.createOrder(zjsRequestData);

                //下单成功则回填单号
                if(20000==zjsReuslt.getState()){
                    return Result.success(zjsOrder);
                }else{
                    //下单异常处理
                    return Result.error(CodeMsg.CREATE_LOGISTICS_ERROR,zjsReuslt.getReason());
                }

            default:


                break;
        }



        return null;
    }

    /**
     * 取消物流订单接口
     * @param shippingOrderId 物流单号
     * @param logisticsType 物流类型1顺丰速运 2百世汇通 3宅急送 4京东
     * @return
     */
    public static Result cancelLogistics(Long shippingOrderId,int logisticsType) throws Exception {
        //实现对快递单的取消功能
        return expressCancelService.cancelLogistics(shippingOrderId, logisticsType);
    }

    /**
     * 查询物流订单接口
     * @param expressNo 物流单号
     * @param logisticsType 物流类型1顺丰速运 2百世汇通 3宅急送 4京东
     * @return
     */
    public static Result queryLogistics(String expressNo,int logisticsType){

        // 匹配物流公司，查询不同物流公司的物流单接口
        switch (logisticsType){
            case 1:
                // 组装参数
                QuerySFRoute route = searchRoutesParam(expressNo);

                // 执行顺丰查询物流单接口
                try {
                    APIResponse query = QiaoAPIService.query(EspServiceCode.EXP_RECE_SEARCH_ROUTES, route);

                    // 返回结果
                    return Result.success(query);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                break;

            case 2:

                // 组装参数
                GetShippingOrderInfoReq infoReq = synTraceQueryParam(expressNo);

               // 执行百世汇通查询物流单接口
                GetShippingOrderInfoRsp infoRsp = iBestService.synTraceQuery(infoReq);

                // 返回结果处理
                return Result.success(infoRsp);

               // break;
            case 3:

                break;


            case 4:
                // 组装参数
                LdopReceiveTraceGetRequest getRequest = getParam(expressNo);

                // 执行京东查询物流单接口
                LdopReceiveTraceGetResponse response = iJingdongServer.get(getRequest);

                // 返回结果处理
                return Result.success(response);
               // break;

            default:

                break;
        }



        //TODO
        return null;
    }

    private static QuerySFRoute searchRoutesParam(String expressNo) {
        QuerySFRoute route = new QuerySFRoute();
        List<String> list = Lists.newArrayList();
        list.add(expressNo);
        route.setTrackingNumber(list);
        return route;
    }

    private static LdopReceiveTraceGetRequest getParam(String shippingOrderNo) {
        LdopReceiveTraceGetRequest request = new LdopReceiveTraceGetRequest();
        request.setCustomerCode("020L2238");
        request.setWaybillCode(shippingOrderNo);

        return request;
    }

    private static GetShippingOrderInfoReq synTraceQueryParam(String shippingOrderNo) {
        GetShippingOrderInfoReq infoReq = new GetShippingOrderInfoReq();
        infoReq.setCustomerCode("FXNN");
        infoReq.setWarehouseCode("QIMEN");

        List<String> shippingOrderList = Lists.newArrayList();
        ShippingOrders shippingOrders = new ShippingOrders();
        shippingOrderList.add(shippingOrderNo);
        shippingOrders.setShippingOrder(shippingOrderList);
        infoReq.setShippingOrders(shippingOrders);
        return infoReq;
    }

    /*
    两位区域代码
        11北京市 12天津市 13河北省 14山西省 15内蒙古自治区
        21辽宁省 22吉林省 23黑龙江省
        31上海市 32江苏省 33浙江省 34安徽省 35福建省 36江西省 37山东省
        41河南省 42湖北省 43湖南省 44广东省 45广西壮族自治区 46海南省
        50重庆市 51四川省 52贵州省 53云南省 54西藏自治区
        61陕西省 62甘肃省 63青海省 64宁夏回族自治区 65新疆维吾尔自治区
        71台湾省 81香港特别行政区 82澳门特别行政区
    */
    /**
     * 顺丰下单参数转换
     * @param shippingOrderNo
     * @param commonProduct
     * @param commonConsignee
     * @param commonConsignor
     * @return
     */
    private static Order transformationSfParam(String shippingOrderNo,
                                        CommonProductDTO commonProduct,
                                        CommonConsigneeDTO commonConsignee ,
                                        CommonConsignorDTO commonConsignor){

        Order sfParam=new Order();
        sfParam.setLanguage("zh-CN");
        sfParam.setOrderId(shippingOrderNo);

        //货物信息
        List<CargoDetail> cargoDetails=new ArrayList<>();
        List<ProductDetailVO>productDetailVOList= commonProduct.getProductList();
        for (ProductDetailVO productDetailVO:productDetailVOList) {
            CargoDetail cargoDetail=new CargoDetail();
            cargoDetail.setName(productDetailVO.getProductName());
            cargoDetail.setCount(productDetailVO.getUnitQuantity());
            cargoDetail.setUnit(productDetailVO.getUnit());
            cargoDetail.setWeight(productDetailVO.getShipWeight());
            cargoDetail.setAmount(productDetailVO.getProductPrice().doubleValue());
            cargoDetail.setCurrency("RMB");
            cargoDetail.setSourceArea("CHN");
            cargoDetails.add(cargoDetail);
        }
        sfParam.setCargoDetails(cargoDetails);

        //寄件人信息
        List<ContactInfo> contactInfoList=new ArrayList<>();
        ContactInfo contactInfo1=new ContactInfo();
        contactInfo1.setContactType(1);
        contactInfo1.setContact(commonConsignor.getReceiveName());
        contactInfo1.setTel(commonConsignor.getMobilePhone());
//        contactInfo1.setCountry("44");
        contactInfo1.setAddress(commonConsignor.getAddress());
        contactInfoList.add(contactInfo1);

        //收件人信息
        ContactInfo contactInfo=new ContactInfo();
        contactInfo.setContactType(2);
        contactInfo.setContact(commonConsignee.getReceiveName());
        contactInfo.setTel(commonConsignee.getMobilePhone());
//        contactInfo.setCountry("44");
        contactInfo.setAddress(commonConsignee.getAddress());
        contactInfoList.add(contactInfo);
        sfParam.setContactInfoList(contactInfoList);

        //快件类型-1为 顺丰标快
        sfParam.setExpressTypeId(1);

        //支付付款方式，支持以下值：1:寄方付 2:收方付 3:第三方付
        sfParam.setPayMethod(1);

        return sfParam;
    }

    /**
     * 百世下单参数转换
     * @param shippingOrderNo
     * @param commonProduct
     * @param commonConsignee
     * @param commonConsignor
     * @return
     */
    private static TwSoNotifyReq transformationBestParam(String shippingOrderNo,
                                               CommonProductDTO commonProduct,
                                               CommonConsigneeDTO commonConsignee ,
                                               CommonConsignorDTO commonConsignor){

        TwSoNotifyReq req = new TwSoNotifyReq();
        req.setOperationFlag("W");
        req.setCustomerCode("FXNN");
        req.setOrderCode(shippingOrderNo);
        req.setWarehouseCode("QIMEN");
        req.setActionType("ADD");
        req.setOperationTypeCode("WDO");

        //寄件人信息
        Sender sender=new Sender();
        sender.setName(commonConsignor.getReceiveName());
        sender.setAddress(commonConsignor.getAddress());
        req.setSender(sender);

        //收件人信息
        Receiver receiver = new Receiver();
        receiver.setName(commonConsignee.getReceiveName());
        receiver.setProvince(commonConsignee.getProvince());
        receiver.setCity(commonConsignee.getLocationCity());
//        receiver.setDistrict("章贡区");
        receiver.setAddress(commonConsignee.getAddress());
        receiver.setPhone(commonConsignee.getMobilePhone());
        req.setReceiver(receiver);

        //货物信息
        ItemList itemList = new ItemList();
        List<Item> items = Lists.newArrayList();
        List<ProductDetailVO>productDetailVOList= commonProduct.getProductList();
        for (ProductDetailVO productDetailVO:productDetailVOList) {
            int i=1;
            Item item = new Item();
            item.setLineNo(productDetailVO.getProductId().intValue());
            item.setItemSkuCode("aa"+i);
            item.setItemName(productDetailVO.getProductName());
            item.setItemQuantity(productDetailVO.getUnitQuantity());
            items.add(item);
            i++;
        }
        itemList.setItem(items);
        req.setItemList(itemList);

        return req;
    }


    /**
     * 宅急送参数转换
     * @param shippingOrderNo
     * @param commonProduct
     * @param commonConsignee
     * @param commonConsignor
     * @return
     */
    private static ZjsRequestData transformationZjsParam(String shippingOrderNo,
                                                         CommonProductDTO commonProduct,
                                                         CommonConsigneeDTO commonConsignee ,
                                                         CommonConsignorDTO commonConsignor){
        List<ProductDetailVO> productList=commonProduct.getProductList();


        ZjsRequestData zjsRequestData=new ZjsRequestData();
        zjsRequestData.setClientFlag("test");
        zjsRequestData.setOrderNo(shippingOrderNo);
        zjsRequestData.setBusType("1");
        zjsRequestData.setGoodsName(productList.get(0).getProductName());
        zjsRequestData.setGoodsNum(productList.get(0).getUnitQuantity().toString());
        zjsRequestData.setGoodsWeight(productList.get(0).getShipWeight().toString());
        zjsRequestData.setSendName(commonConsignor.getReceiveName());
        zjsRequestData.setSendAddress(commonConsignor.getAddress());
        zjsRequestData.setSendMobile(commonConsignor.getMobilePhone());
        zjsRequestData.setReceiveName(commonConsignee.getReceiveName());
        zjsRequestData.setReceivePro(commonConsignee.getProvince());
        zjsRequestData.setReceiveCity(commonConsignee.getLocationCity());
        zjsRequestData.setReceiveDistrict("宝安区");
        zjsRequestData.setReceiveAddress(commonConsignee.getAddress());
        zjsRequestData.setReceiveMobile(commonConsignee.getMobilePhone());

        return zjsRequestData;
    }
//    public static void main(String[] args)throws Exception {
//
//        Result result = queryLogistics(1290198859296362498L);
//
//        System.out.println(result);
//
//    }
//        Order sfParam=new Order();
//        sfParam.setLanguage("zh-CN");
//        sfParam.setOrderId("FH"+IdUtil.objectId());
//
//        //货物信息
//        List<CargoDetail> cargoDetails=new ArrayList<>();
//        CargoDetail cargoDetail=new CargoDetail();
//        cargoDetail.setName("箱子");
//        cargoDetail.setCount(3);
//        cargoDetail.setUnit("个");
//        cargoDetail.setWeight(2);
//        cargoDetail.setAmount(100);
//        cargoDetail.setCurrency("RMB");
//        cargoDetail.setSourceArea("CHN");
//        cargoDetails.add(cargoDetail);
//        sfParam.setCargoDetails(cargoDetails);
//
//        //收寄双方信息
//        List<ContactInfo> contactInfoList=new ArrayList<>();
//        ContactInfo contactInfo1=new ContactInfo();
//        contactInfo1.setContactType(1);
//        contactInfo1.setContact("李四");
//        contactInfo1.setTel("13112347891");
//        contactInfo1.setCountry("44");
//        contactInfo1.setAddress("广东省深圳市南山区南山街道长虹大厦1201");
//        contactInfoList.add(contactInfo1);
//
//        ContactInfo contactInfo=new ContactInfo();
//        contactInfo.setContactType(2);
//        contactInfo.setContact("张三");
//        contactInfo.setTel("13112347894");
//        contactInfo.setCountry("44");
//        contactInfo.setAddress("广东省深圳市南山区南山街道长虹大厦1101");
//        contactInfoList.add(contactInfo);
//        sfParam.setContactInfoList(contactInfoList);
//
//        sfParam.setExpressTypeId(1);
//        sfParam.setPayMethod(1);
//        APIResponse apiresponse=QiaoAPIService.query(EspServiceCode.EXP_RECE_CREATE_ORDER,sfParam);
//
//        System.out.println(apiresponse);
//    }


}
