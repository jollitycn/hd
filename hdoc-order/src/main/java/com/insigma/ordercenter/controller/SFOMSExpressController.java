package com.insigma.ordercenter.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.logistics.sf.qiao.Order;
import com.insigma.ordercenter.logistics.sf.qiao.OrderSearchReqDto;
import com.insigma.ordercenter.logistics.sf.qiao.OrderUpdate;
import com.insigma.ordercenter.logistics.sf.qiao.QuerySFRoute;
import com.insigma.ordercenter.service.sf.qiao.APIResponse;
import com.insigma.ordercenter.service.sf.qiao.EspServiceCode;
import com.insigma.ordercenter.service.sf.qiao.QiaoAPIService;
import com.insigma.ordercenter.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.*;


/**
 * <p>
 * 选择专业表 前端控制器
 * </p>
 *
 * @author Hongsifan
 * @since 2020-04-13
 */


@Api(tags = {"顺丰冷运接口"}, value = "SFOMSExpressController")
@RestController
@RequestMapping("/sfoms")
@Configuration
@Slf4j
public class SFOMSExpressController extends BaseController {

    @Autowired
    private RedisUtil redisUtil;
    //    @Autowired
//    private TestCallExpressNewAPIService testCallExpressNewAPIService;
    @PostMapping("/cb")
    @ApiOperation("cb")
    public String cb(HttpServletRequest request) throws Exception {
//        {"actualShipDateTime":1596452750000,"carrier":"CP","carrierProduct":"SE0113","companyCode":"0208255482","dataStatus":"100","erpOrder":"00200703840108","nakeExpress":"2","orderTypeCode":"PO","returnTracking":"","sfOrderNo":"OB569100647279728041-100","transactionId":"203SO010VB200803000075","userDef1":"","userDef2":",","userDef3":"","userDef4":"","userDef5":"","userDef6":"","userDef7":"0","userDef8":"0","warehouseCode":"010VB","wayBillNo":"SF1040276461418"}



        //  APIResponse response = QiaoAPIService.query(EspServiceCode.EXP_RECE_CREATE_ORDER, order);
        //  if (response.getApiResultCode() != null) {
        //      return Result.error(new CodeMsg(CodeMsg.API_FAILED.getRetCode(), response.toString()));
        //  } else {
        List<String> body = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            body  = IOUtils.readLines(reader);
            System.out.println("返回json数据");
            System.out.println(body);
            System.out.println("结束");
        }catch (Exception e){
            System.out.println("错误信息");
        }
        request.getParameterMap();
        HashMap<Object, Object> map = new HashMap<Object, Object>();
        HashMap parameterMap = new HashMap();
        parameterMap.putAll(request.getParameterMap());
        map.put("body", body);
        map.put("parameterMap", parameterMap);
        map.put("queryString", request.getQueryString());
        List<String> list = new ArrayList<String>();
        Vector v = new Vector();
        for (Enumeration e = request.getAttributeNames(); e.hasMoreElements();){
            v.add(e.nextElement());
        }

//        {"actualShipDateTime":1596519913000,"carrier":"CP","carrierProduct":"SE0113","companyCode":"0208255482","dataStatus":"900","erpOrder":"00200703840110","nakeExpress":"2","orderTypeCode":"PO","outboundContainer":[{"containerId":"SF1020000726965","containerNo":"101000052224375","containerType":"0","items":[{"actualQty":1.0,"erpOrderLineNum":"1","inventoryStatus":"10","lot":"","lotattdesc":"2019-01-18;2020-05-25;2019-01-18;;;;;N;;;;;","skuNo":"6950142400845","userDef1":"","userDef11":"2019-01-18","userDef2":"10","userDef3":"1","userDef4":"2019-01-18","userDef5":"2020-05-25","userDef6":"","userDef7":"0","userDef8":"1","weight":20.0,"weightUm":"KG"}],"userDef1":"","userDef11":"","userDef2":"","userDef3":"","userDef4":"","userDef5":"","userDef6":"","userDef7":"0","userDef8":"1","weight":20.0,"weightUm":"KG"}],"outboundDetail":[{"actualQty":1.0,"erpOrderLineNum":"1","shelflife":"493","skuNo":"6950142400845"}],"returnTracking":"","sfOrderNo":"OB569100866323069873-100","transactionId":"203SO010VB200804001463","userDef1":"","userDef2":",","userDef3":"","userDef4":"","userDef5":"","userDef6":"","userDef7":"0","userDef8":"0","warehouseCode":"010VB","wayBillNo":"SF1020000726965"}
        map.put("attributeNames", v);
        for (String reqStr: body) {
            if (!StringUtils.isEmpty(reqStr)) {
                JSONObject obj = JSON.parseObject(reqStr);
                Object wayBillNo = obj.get("wayBillNo");
                if(wayBillNo!=null) {
                    redisUtil.hset("sfoms.callback", wayBillNo.toString(), reqStr);
                }
            }
        }

        log.debug("cb result",map);
        return "{\"code\":200,\"message\":\"接受成功\"}";//Result.success(map);
        //  }
    }


    @GetMapping("/outboundConfirm/{wayBillNo}")
    @ApiOperation("outboundConfirm")
    public Result outboundConfirm(@PathVariable String wayBillNo) throws Exception {
        return Result.success(redisUtil.hget("sfoms.callback", wayBillNo));
    }
    @GetMapping("/outboundConfirms/")
    @ApiOperation("outboundConfirms")
    public Result outboundConfirms(HttpServletRequest request) throws Exception {
        return Result.success(redisUtil.hmget("sfoms.callback"));
    }

    @GetMapping("/outboundConfirmRemove/{wayBillNo}")
    @ApiOperation("outboundConfirmRemove")
    public Result outboundConfirmRemove(@PathVariable String wayBillNo) throws Exception {
        redisUtil.hdel("sfoms.callback" , wayBillNo);
        return Result.success();
    }

    //    @Autowired
//    private TestCallExpressNewAPIService testCallExpressNewAPIService;
    @PostMapping("/createOrder")
    @ApiOperation("下单")
    public Result createOrder(@Valid @RequestBody Order order) throws Exception {
//        SFExpressRequest request = new SFExpressRequest();
//        Map<String, String> params = new HashMap<>();
//        params.put(SFExpressRequest.ORDER_NO, info.getOrderNo());
//        //收件人
//        params.put("receiverName", info.getReceiverName());
//        //收件人电话
//        params.put("receiverMobile", info.getReceiverMobile());
//        //收件人详细地址
//        params.put("address", info.getAddress());
//        //商品名称
//        params.put("commodityName", info.getCommodityName());
//        //商品数量
//        params.put("orderNum", info.getOrderNum());

        APIResponse response = QiaoAPIService.query(EspServiceCode.EXP_RECE_CREATE_ORDER, order);
        if (response.getApiResultCode() != null) {
            return Result.error(new CodeMsg(CodeMsg.API_FAILED.getRetCode(), response.toString()));
        } else {
            return Result.success(response);
        }
    }

    @PostMapping("/orderSearchService")
    @ApiOperation("订单结果查询接口")
    public Result orderSearchService(@Valid @RequestBody OrderSearchReqDto dto) throws Exception {
        APIResponse response = QiaoAPIService.query(EspServiceCode.EXP_RECE_FILTER_ORDER_BSP, dto);
        if (response.getApiResultCode() != null) {
            return Result.error(new CodeMsg(CodeMsg.API_FAILED.getRetCode(), response.toString()));
        } else {
            return Result.success(response);
        }
    }

    @PostMapping("/updateOrder")
    @ApiOperation("订单确认/取消接口,客户在发货前取消订单")
    public Result updateOrder(@Valid @RequestBody OrderUpdate orderUpdate) throws Exception {
        APIResponse response = QiaoAPIService.query(EspServiceCode.EXP_RECE_UPDATE_ORDER, orderUpdate);
        if (response.getApiResultCode() != null) {
            return Result.error(new CodeMsg(CodeMsg.API_FAILED.getRetCode(), response.toString()));
        } else {
            return Result.success(response);
        }
    }

    @PostMapping("/searchRoutes")
    @ApiOperation("路由查询接口:" +
            "此路由查询接口支持三类查询方式:\n" +
            "\n" +
            "1)根据顺丰运单号查询：查询请求中提供接入编码与运单号，验证接入编码与所有请求运单号的归属关系，系统只返回具有正确归属关系的运单路由信息。\n" +
            "\n" +
            "2)根据客户订单号查询：查询请求中提供接入编码与订单号，验证接入编码与所有请求订单号的归属关系，对于归属关系正确的订单号，找到对应的运单号，然后返回订单对应运单号的路由信息。适用于通过下单的客户订单。\n" +
            "\n" +
            "3)根据运单号+运单对应的收寄人任一方电话号码后4位(参数checkPhoneNo中传入)查询,系统校验信息匹配将返回对应运单路由信息。")
    public Result searchRoutes(@Valid @RequestBody QuerySFRoute querySFRoute) throws Exception {
        APIResponse response = QiaoAPIService.query(EspServiceCode.EXP_RECE_SEARCH_ROUTES, querySFRoute);
        if (response.getApiResultCode() != null) {
            return Result.error(new CodeMsg(CodeMsg.API_FAILED.getRetCode(), response.toString()));
        } else {
            return Result.success(response);
        }
    }

    @PostMapping("/filterOrderBsp")
    @ApiOperation("订单筛选接口 1. 功能描述 客户系统通过此接口向顺丰系统发送主动的筛单请求,用于判断客户的收、派地址是否属于顺丰的收派范围。")
    public Result filterOrderBsp(@Valid @RequestBody QuerySFRoute querySFRoute) throws Exception {
        APIResponse response = QiaoAPIService.query(EspServiceCode.EXP_RECE_FILTER_ORDER_BSP, querySFRoute);
        if (response.getApiResultCode() != null) {
            return Result.error(new CodeMsg(CodeMsg.API_FAILED.getRetCode(), response.toString()));
        } else {
            return Result.success(response);
        }
    }
}
