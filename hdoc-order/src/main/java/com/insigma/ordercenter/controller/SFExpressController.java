package com.insigma.ordercenter.controller;

import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.logistics.sf.qiao.Order;
import com.insigma.ordercenter.logistics.sf.qiao.OrderSearchReqDto;
import com.insigma.ordercenter.logistics.sf.qiao.OrderUpdate;
import com.insigma.ordercenter.logistics.sf.qiao.QuerySFRoute;
import com.insigma.ordercenter.service.sf.qiao.APIResponse;
import com.insigma.ordercenter.service.sf.qiao.EspServiceCode;
import com.insigma.ordercenter.service.sf.qiao.QiaoAPIService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * 选择专业表 前端控制器
 * </p>
 *
 * @author Hongsifan
 * @since 2020-04-13
 */


@Api(tags = {"顺丰速运接口"}, value = "SFExpressController")
@RestController
@RequestMapping("/sf")
@Configuration
public class SFExpressController extends BaseController {

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


    //    @Autowired
//    private TestCallExpressNewAPIService testCallExpressNewAPIService;
    @PostMapping("/cb")
    @ApiOperation("cb")
    public Result cb(HttpServletRequest request) throws Exception {

        //  APIResponse response = QiaoAPIService.query(EspServiceCode.EXP_RECE_CREATE_ORDER, order);
        //  if (response.getApiResultCode() != null) {
        //      return Result.error(new CodeMsg(CodeMsg.API_FAILED.getRetCode(), response.toString()));
        //  } else {
        request.getParameterMap();
        Map<Object, Object> map = new HashMap<Object, Object>() ;
        map.put("parameterMap", request.getParameterMap());
        map.put("queryString", request.getQueryString());
        map.put("attributeNames", request.getAttributeNames());
        return Result.success(map);
        //  }
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
            "此路由查询接口支持三类查询方式: \n" +
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
