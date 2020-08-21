package com.insigma.ordercenter.controller;


import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.vo.OrderDetailExamineVO;
import com.insigma.ordercenter.entity.vo.OrderPayAllVO;
import com.insigma.ordercenter.entity.vo.OrderpayVO;
import com.insigma.ordercenter.service.IOrderPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.insigma.ordercenter.controller.BaseController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author AH
 * @since 2020-07-27
 */
@RestController
@RequestMapping("/order-pay")
@Api(tags = {"订单管理"})
public class OrderPayController extends BaseController {

    @Resource
    private IOrderPayService orderPayService;

    @GetMapping("/queryOrderPayInfo/{orderId}")
    @ApiOperation(value = "根据订单号查询支付信息")
    public Result<?> queryOrderPayInfo(@Valid @PathVariable Long orderId) {
        OrderPayAllVO orderPayAllVO = orderPayService.queryOrderPayInfo(orderId);
        return Result.success(orderPayAllVO);
    }

}
