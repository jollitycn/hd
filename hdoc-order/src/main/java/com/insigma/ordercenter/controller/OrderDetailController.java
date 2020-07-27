package com.insigma.ordercenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.OrderDetail;
import com.insigma.ordercenter.entity.vo.AddOrderDetailVO;
import com.insigma.ordercenter.service.IOrderDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 订单明细 前端控制器
 * </p>
 *
 * @author AH
 * @since 2020-07-22
 */
@RestController
@RequestMapping("/order-detail")
@Api(tags = {"订单详情"})
public class OrderDetailController extends BaseController {


    @Resource
    private IOrderDetailService orderDetailService;

    @GetMapping("/orderDerailList/{orderId}")
    @ApiOperation(value = "订单详情列表")
    public Result<?> orderDerailList(@Valid @PathVariable Long orderId) {
        QueryWrapper<OrderDetail> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("order_id",orderId);
        List<OrderDetail> list = orderDetailService.list(queryWrapper);
        return Result.success(list);
    }


    @PutMapping("/orderDerailUpdate/{orderDetailId}")
    @ApiOperation(value = "订单详情编辑")
    public Result<?> orderDerailUpdate(@Valid @PathVariable Long orderDetailId) {
        QueryWrapper<OrderDetail> updateWrapper=new QueryWrapper<>();
        updateWrapper.eq("order_detail_id",orderDetailId);
        boolean update = orderDetailService.update(updateWrapper);
        if(update){
            return Result.success();
        }
        return Result.error(CodeMsg.DATA_UPDATE_ERROR);
    }

    @DeleteMapping("/orderDerailDelete/{orderDetailId}")
    @ApiOperation(value = "订单详情移除")
    public Result<?> orderDerailDelete(@Valid @PathVariable Long orderDetailId) {
        QueryWrapper<OrderDetail> updateWrapper=new QueryWrapper<>();
        updateWrapper.eq("order_detail_id",orderDetailId);
        boolean update = orderDetailService.remove(updateWrapper);
        if(update){
            return Result.success();
        }
        return Result.error(CodeMsg.DATA_DELETE_ERROR);
    }

    @DeleteMapping("/getOrderDerail/{orderId}")
    @ApiOperation(value = "查询赠品中的订单详情列表信息")
    public Result<?> getOrderDerail(@Valid @PathVariable Long orderId) {
        List<OrderDetail> orderDetail = orderDetailService.getOrderDetail(orderId);
        return Result.success(orderDetail);
    }

    @PostMapping("/addOrderDerail")
    @ApiOperation(value = "查询赠品中的订单详情列表信息")
    public Result<?> addOrderDerail(@Valid AddOrderDetailVO addOrderDetailVO) {
        Boolean aBoolean = orderDetailService.addOrderDerail(addOrderDetailVO);
        return Result.success(aBoolean);
    }

}
