package com.insigma.ordercenter.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.logistics.hdjf.edb.EdbTradeAdd;
import com.insigma.ordercenter.logistics.hdjf.edb.EdbTradeCancel;
import com.insigma.ordercenter.logistics.hdjf.edb.EdbTradeGet;
import com.insigma.ordercenter.logistics.sf.qiao.Order;
import com.insigma.ordercenter.logistics.sf.qiao.OrderSearchReqDto;
import com.insigma.ordercenter.logistics.sf.qiao.OrderUpdate;
import com.insigma.ordercenter.logistics.sf.qiao.QuerySFRoute;
import com.insigma.ordercenter.service.hdjf.edb.EdbTradeAudit;
import com.insigma.ordercenter.service.hdjf.edb.HDJFEDBService;
import com.insigma.ordercenter.service.sf.qiao.APIResponse;
import com.insigma.ordercenter.service.sf.qiao.EspServiceCode;
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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


/**
 * <p>
 * 选择专业表 前端控制器
 * </p>
 *
 * @author Hongsifan
 * @since 2020-04-13
 */


@Api(tags = {"汉达捷丰e店宝接口"}, value = "HDJFEDBExpressController")
@RestController
@RequestMapping("/hdjf/edb")
@Configuration
@Slf4j
public class HDJFEDBExpressController extends BaseController {
    @Autowired
private HDJFEDBService hdjfedbService;



    //    @Autowired
//    private TestCallExpressNewAPIService testCallExpressNewAPIService;
    @PostMapping("/createOrder")
    @ApiOperation("下单")
    public Result createOrder(@Valid @RequestBody EdbTradeAdd order) throws Exception {
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

        String response = hdjfedbService.edbTradeAdd(order);
        return Result.success(response);
//        if (response.getApiResultCode() != null) {
//            return Result.error(new CodeMsg(CodeMsg.API_FAILED.getRetCode(), response.toString()));
//        } else {
//            return Result.success(response);
//        }
    }

    @PostMapping("/orderSearchService")
    @ApiOperation("订单结果查询接口")
    public Result orderSearchService(@Valid @RequestBody EdbTradeGet dto) throws Exception {
        String response = hdjfedbService.edbTradeGet(  dto);
        return Result.success(response);
    }

    @PostMapping("/edbTradeAudit")
    @ApiOperation("订单确认")
    public Result edbTradeAudit(@Valid @RequestBody EdbTradeAudit bean) throws Exception {
        String response = hdjfedbService.edbTradeAudit(  bean);
        return Result.success(response);
    }

    @PostMapping("/cancelOrder")
    @ApiOperation("订单取消接口")
    public Result cancelOrder(@Valid @RequestBody EdbTradeCancel bean) throws Exception {
        String response = hdjfedbService.edbTradeCancel(  bean);
        return Result.success(response);
    }

//    @PostMapping("/searchRoutes")
//    @ApiOperation("路由查询接口:" +
//            "此路由查询接口支持三类查询方式:\n" +
//            "\n" +
//            "1)根据顺丰运单号查询：查询请求中提供接入编码与运单号，验证接入编码与所有请求运单号的归属关系，系统只返回具有正确归属关系的运单路由信息。\n" +
//            "\n" +
//            "2)根据客户订单号查询：查询请求中提供接入编码与订单号，验证接入编码与所有请求订单号的归属关系，对于归属关系正确的订单号，找到对应的运单号，然后返回订单对应运单号的路由信息。适用于通过下单的客户订单。\n" +
//            "\n" +
//            "3)根据运单号+运单对应的收寄人任一方电话号码后4位(参数checkPhoneNo中传入)查询,系统校验信息匹配将返回对应运单路由信息。")
//    public Result searchRoutes(@Valid @RequestBody QuerySFRoute querySFRoute) throws Exception {
//        APIResponse response = HDJFEDBService.query(EspServiceCode.EXP_RECE_SEARCH_ROUTES, querySFRoute);
//        if (response.getApiResultCode() != null) {
//            return Result.error(new CodeMsg(CodeMsg.API_FAILED.getRetCode(), response.toString()));
//        } else {
//            return Result.success(response);
//        }
//    }

}
