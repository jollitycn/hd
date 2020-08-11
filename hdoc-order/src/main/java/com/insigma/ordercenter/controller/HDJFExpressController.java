package com.insigma.ordercenter.controller;

import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.logistics.sf.qiao.QuerySFRoute;
import com.insigma.ordercenter.service.IHDJFService;
import com.insigma.ordercenter.service.impl.ZtoRequest;
import hdjf.EdbTradeAdd;
import hdjf.EdbTradeAudit;
import hdjf.EdbTradeCancel;
import hdjf.EdbTradeGet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * <p>
 * 选择专业表 前端控制器
 * </p>
 *
 * @author Hongsifan
 * @since 2020-04-13
 */


@Api(tags = {"汉达捷丰接口"}, value = "HDJFExpressController")
@RestController
@RequestMapping("/hdjf")
@Configuration
@Slf4j
public class HDJFExpressController extends BaseController {
    @Autowired
    private IHDJFService hdjfedbService;


    //    @Autowired
//    private TestCallExpressNewAPIService testCallExpressNewAPIService;
    @PostMapping("/createOrder")
    @ApiOperation("下单")
    public Result createOrder( @Valid @RequestBody EdbTradeAdd order ) throws Exception {
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
//        if(response.getApiResultCode() != null) {
//            return Result.error(new CodeMsg(CodeMsg.API_FAILED.getRetCode(), response.toString()));
//        } else {
//            return Result.success(response);
//        }

    }
    @PostMapping("/order" +
            "SearchService")
    @ApiOperation("订单结果查询接口")
    public Result orderSearchService( @Valid @RequestBody EdbTradeGet dto ) throws Exception {
        String response = hdjfedbService.edbTradeGet(dto);
        return Result.success(response);
    }

    @PostMapping("/edbTradeAudit")
    @ApiOperation("订单确认")
    public Result edbTradeAudit( @Valid @RequestBody EdbTradeAudit bean ) throws Exception {
        String response = hdjfedbService.edbTradeAudit(bean);
        return Result.success(response);
    }

    @PostMapping("/cancelOrder")
    @ApiOperation("订单取消接口")
    public Result cancelOrder( @Valid @RequestBody EdbTradeCancel bean ) throws Exception {
        String response = hdjfedbService.edbTradeCancel(bean);
        return Result.success(response);
    }

    @PostMapping("/searchZTKDRoutes")
    @ApiOperation("中通快递路由查询接口:" +
            "此路由查询接口支持三类查询方式:\n" +
            "\n" +
            "1)根据顺丰运单号查询：查询请求中提供接入编码与运单号，验证接入编码与所有请求运单号的归属关系，系统只返回具有正确归属关系的运单路由信息。\n" +
            "\n" +
            "2)根据客户订单号查询：查询请求中提供接入编码与订单号，验证接入编码与所有请求订单号的归属关系，对于归属关系正确的订单号，找到对应的运单号，然后返回订单对应运单号的路由信息。适用于通过下单的客户订单。\n" +
            "\n" +
            "3)根据运单号+运单对应的收寄人任一方电话号码后4位(参数checkPhoneNo中传入)查询,系统校验信息匹配将返回对应运单路由信息。")
    public Result searchZTKDRoutes( @Valid @RequestBody String querySFRoute ) throws Exception {
        String response = hdjfedbService.searchZTKDRoutes(querySFRoute);
        return Result.success(response);
    }

    @PostMapping("/searchZTKYRoutes")
    @ApiOperation("中通快运路由查询接口:"+
            "此路由查询接口支持三类查询方式:\n" +
            "\n" +
            "1)根据顺丰运单号查询：查询请求中提供接入编码与运单号，验证接入编码与所有请求运单号的归属关系，系统只返回具有正确归属关系的运单路由信息。\n" +
            "\n" +
            "2)根据客户订单号查询：查询请求中提供接入编码与订单号，验证接入编码与所有请求订单号的归属关系，对于归属关系正确的订单号，找到对应的运单号，然后返回订单对应运单号的路由信息。适用于通过下单的客户订单。\n" +
            "\n" +
            "3)根据运单号+运单对应的收寄人任一方电话号码后4位(参数checkPhoneNo中传入)查询,系统校验信息匹配将返回对应运单路由信息。")
    public Result searchZTKYRoutes( @Valid @RequestBody ZtoRequest querySFRoute) throws Exception {
        String response = hdjfedbService.searchZTKYRoutes(querySFRoute);
        return Result.success(response);
    }

    @PostMapping("/searchYTRoutes")
    @ApiOperation("圆通路由查询接口:" +
            "此路由查询接口支持三类查询方式:\n" +
            "\n" +
            "1)根据顺丰运单号查询：查询请求中提供接入编码与运单号，验证接入编码与所有请求运单号的归属关系，系统只返回具有正确归属关系的运单路由信息。\n" +
            "\n" +
            "2)根据客户订单号查询：查询请求中提供接入编码与订单号，验证接入编码与所有请求订单号的归属关系，对于归属关系正确的订单号，找到对应的运单号，然后返回订单对应运单号的路由信息。适用于通过下单的客户订单。\n" +
            "\n" +
            "3)根据运单号+运单对应的收寄人任一方电话号码后4位(参数checkPhoneNo中传入)查询,系统校验信息匹配将返回对应运单路由信息。")
    public Result searchYTRoutes( @Valid @RequestBody String number ) throws Exception {
        String response = hdjfedbService.searchYTRoutes(number);
        return Result.success(response);
    }

}
