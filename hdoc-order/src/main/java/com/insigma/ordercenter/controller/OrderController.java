package com.insigma.ordercenter.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.LoginUser;
import com.insigma.ordercenter.entity.dto.OrderDTO;
import com.insigma.ordercenter.entity.dto.UpdateOrderStatuDTO;
import com.insigma.ordercenter.entity.dto.AddShippingOrderResultDTO;
import com.insigma.ordercenter.entity.dto.UpdateShippingOrderStatuDTO;
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

    @PostMapping("/orderList")
    @ApiOperation(value = "订单列表")
    public Result<?> orderList(@RequestBody OrderDTO orderDTO) {

        LoginUser loginUser = redisUser();
        Page<OrderListVO> page = new Page<>(orderDTO.getPageNum(), orderDTO.getPageSize());
        orderDTO.setUserId(loginUser.getUserId());
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
        if(aBoolean){
            return Result.success();
        }else{
            return Result.error(CodeMsg.DATA_UPDATE_ERROR);
        }
    }

    @PutMapping("/shippingOrderStatuChange")
    @ApiOperation(value = "发货单状态改变回调接口")
    public Result<?> shippingOrderStatuChange(UpdateShippingOrderStatuDTO updateOrderStatuDTO) {
        Boolean aBoolean = orderService.shippingOrderStatuChange(updateOrderStatuDTO);
        if(aBoolean){
            return Result.success();
        }else{
            return Result.error(CodeMsg.DATA_UPDATE_ERROR);
        }
    }


    @GetMapping("/getOrderDetailList/{orderId}")
    @ApiOperation(value = "审单查询商品，以及仓库列表信息",response = OrderDetailExamineVO.class)
    public Result<?> getOrderDetailList(@Valid @PathVariable Long orderId) {
        List<OrderDetailExamineVO> orderDetailExamineVOS = orderService.queryOrderDetailList(orderId);
        return Result.success(orderDetailExamineVOS);
    }

    @GetMapping("/queryExpressCompanyList/{warehouseId}")
    @ApiOperation(value = "审单查询商品，以及仓库列表信息",response = ExpressCompanyVO.class )
    public Result<?> queryExpressCompanyList(@Valid @PathVariable Long warehouseId) {
        List<ExpressCompanyVO> expressCompanyVOS = orderService.queryExpressCompany(warehouseId);
        return Result.success(expressCompanyVOS);
    }

    @PostMapping("/addShippingOrder")
    @ApiOperation(value = "订单分派发仓库，生成发货单")
    public Result<?> addShippingOrder(@Valid @RequestBody AddShippingOrderResultDTO addShippingOrderResultDTO) {
        LoginUser loginUser = redisUser();
        return orderService.addShippingOrder(addShippingOrderResultDTO,loginUser);
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
    @ApiOperation(value = "点击订单列表查询发货单tab信息",response = OrderListVO.class)
    public Result<?> queryOrderById(@Valid @PathVariable Long orderId) {
        OrderListVO orderListVO = orderService.queryOrderById(orderId);
        return Result.success(orderListVO);
    }

    @GetMapping("/queryOriginalOrder/{orderId}")
    @ApiOperation(value = "点击订单列表查询合单tab信息",response = OriginalOrderVO.class)
    public Result<?> queryOriginalOrder(@Valid @PathVariable Long orderId) {
        List<OriginalOrderVO> originalOrderVOS = orderService.queryOriginalOrderList(orderId);
        return Result.success(originalOrderVOS);
    }

    @GetMapping("/queryRefundInfo/{orderId}")
    @ApiOperation(value = "点击订单列表查询退换货tab信息",response = RefundInfoVO.class)
    public Result<?> queryRefundInfo(@Valid @PathVariable Long orderId) {
        List<RefundInfoVO> refundInfoVOS = orderService.queryRefundInfo(orderId);
        return Result.success(refundInfoVOS);
    }

    @GetMapping("/queryOrderOperationLogInfo/{orderId}")
    @ApiOperation(value = "点击订单列表查询操作日志tab信息", response = OrderOperationLogVO.class)
    public Result<?> queryOrderOperationLogInfo(@Valid @PathVariable Long orderId) {
        List<OrderOperationLogVO> orderOperationLogVOS = orderService.queryOrderOperationLogInfo(orderId);
        return Result.success(orderOperationLogVOS);
    }

    @GetMapping("/deleteOrder/{orderId}")
    @ApiOperation(value = "删除订单信息（新建状态）")
    public Result<?> deleteOrder(@Valid @PathVariable Long orderId) {
        Boolean aBoolean = orderService.deleteOrder(orderId);
        if(aBoolean){
            return Result.success();
        }else{
            return Result.error(CodeMsg.DATA_DELETE_ERROR);
        }
    }


    @GetMapping("/queryExpressInfo/{shippingOrderNo}")
    @ApiOperation(value = "点击发货信息中的发货单号，查询物流信息")
    public Result<?> queryExpressInfo(@Valid @PathVariable Long shippingOrderNo) {
        List<?> ts = orderService.queryExpressInfo(shippingOrderNo);
        return Result.success(ts);
    }



}
