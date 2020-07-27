package com.insigma.ordercenter.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.dto.OrderDTO;
import com.insigma.ordercenter.entity.dto.UpdateOrderStatuDTO;
import com.insigma.ordercenter.entity.dto.AddShippingOrderResultDTO;
import com.insigma.ordercenter.entity.vo.*;
import com.insigma.ordercenter.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-16
 */
@RestController
@RequestMapping("/order")
@Api(tags = {"订单管理"})
public class OrderController extends BaseController{

    @Resource
    private IOrderService orderService;

    @PostMapping("/list")
    @ApiOperation(value = "订单列表")
    public Result<?> list(@Valid @RequestBody OrderDTO orderDTO) {

        Page<OrderListVO> page = new Page<>(orderDTO.getPageNum(), orderDTO.getPageSize());

        IPage<OrderListVO> result = orderService.queryOrderListPage(page, orderDTO);

        return Result.success(result);
    }

    @PostMapping("/addOrder")
    @ApiOperation(value = "新增订单")
    public Result<?> addOrder(@Valid @RequestBody SendReceiveInfoVO sendReceiveInfoVO) {
        return orderService.addOrder(sendReceiveInfoVO);
    }

    @PutMapping("/updateOrderStatu")
    @ApiOperation(value = "修改订单状态")
    public Result<?> updateOrderStatu(UpdateOrderStatuDTO updateOrderStatuDTO) {
        Boolean aBoolean = orderService.updateOrderStatu(updateOrderStatuDTO);
        return Result.success(aBoolean);
    }

    @GetMapping("/getOrderDetailList/{orderId}")
    @ApiOperation(value = "审单查询商品，以及仓库列表信息")
    public Result<?> getOrderDetailList(@Valid @PathVariable Long orderId) {
        List<OrderDetailExamineVO> orderDetailExamineVOS = orderService.queryOrderDetailList(orderId);
        return Result.success(orderDetailExamineVOS);
    }

    @GetMapping("/queryExpressCompanyList/{warehouseId}")
    @ApiOperation(value = "审单查询商品，以及仓库列表信息")
    public Result<?> queryExpressCompanyList(@Valid @PathVariable Long warehouseId) {
        List<ExpressCompanyVO> expressCompanyVOS = orderService.queryExpressCompany(warehouseId);
        return Result.success(expressCompanyVOS);
    }

    @PostMapping("/addShippingOrder")
    @ApiOperation(value = "订单分派发仓库，生成发货单")
    public Result<?> addShippingOrder(@Valid @RequestBody AddShippingOrderResultDTO addShippingOrderResultDTO) {
        return orderService.addShippingOrder(addShippingOrderResultDTO);
    }

    @PostMapping("/cancelOrder/{orderId}")
    @ApiOperation(value = "取消订单操作")
    public Result<?> cancelOrder(@Valid @PathVariable Long orderId) {
        //取消订单，1、根据订单号，查询所有的发货单列表
        //判断发货单是否有冻结状态的发货单，有，则订单状态改为冻结；如果全是取消状态，则可以取消
        //发货单是拒收、异常、冻结状态，那么订单就是冻结状态
        return  orderService.cancelOrder(orderId);
    }

    @GetMapping("/queryOrderById/{orderId}")
    @ApiOperation(value = "点击订单列表查询发货单tab信息")
    public Result<?> queryOrderById(@Valid @PathVariable Long orderId) {
        OrderListVO orderListVO = orderService.queryOrderById(orderId);
        return Result.success(orderListVO);
    }

    @GetMapping("/queryOriginalOrder/{orderId}")
    @ApiOperation(value = "点击订单列表查询合单tab信息")
    public Result<?> queryOriginalOrder(@Valid @PathVariable Long orderId) {
        List<OriginalOrderVO> originalOrderVOS = orderService.queryOriginalOrderList(orderId);
        return Result.success(originalOrderVOS);
    }

}
