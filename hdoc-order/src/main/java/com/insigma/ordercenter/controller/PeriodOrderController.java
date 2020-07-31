package com.insigma.ordercenter.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.PeriodOrder;
import com.insigma.ordercenter.entity.PeriodOrderDetail;
import com.insigma.ordercenter.entity.PeriodOrderOperationLog;
import com.insigma.ordercenter.entity.PeriodSendReceiveInfo;
import com.insigma.ordercenter.entity.dto.OrderDTO;
import com.insigma.ordercenter.entity.dto.PeriodOrderDTO;
import com.insigma.ordercenter.entity.dto.PeriodOrderStatuDTO;
import com.insigma.ordercenter.entity.vo.*;
import com.insigma.ordercenter.service.IPeriodOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import com.insigma.ordercenter.controller.BaseController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 预约订单 前端控制器
 * </p>
 *
 * @author AH
 * @since 2020-07-28
 */
@RestController
@RequestMapping("/period-order")
@Api(tags = {"预约订单管理"})
public class PeriodOrderController extends BaseController {

    @Resource
    private IPeriodOrderService periodOrderService;

    @PostMapping("/list")
    @ApiOperation(value = "预约订单列表", response = OrderListVO.class)
    public Result<?> list(@RequestBody PeriodOrderDTO periodOrderDTO) {

        Page<PeriodOrderVO> page = new Page<>(periodOrderDTO.getPageNum(), periodOrderDTO.getPageSize());

        IPage<PeriodOrderVO> result = periodOrderService.queryPeriodOrderListPage(page, periodOrderDTO);

        return Result.success(result);
    }


    @PutMapping("/updatePeriodOrderStatu")
    @ApiOperation(value = "审单查询商品，以及仓库列表信息")
    public Result<?> updatePeriodOrderStatu(@Valid PeriodOrderStatuDTO periodOrderStatuDTO) {
        Boolean aBoolean = periodOrderService.updatePeriodOrderStatu(periodOrderStatuDTO);
        return Result.success(aBoolean);
    }


    @GetMapping("/getPeriodOrderDetailList/{periodOderId}")
    @ApiOperation(value = "根据预约订单查询，预约订单明细列表信息",response = PeriodOrderDetail.class)
    public Result<?> getPeriodOrderDetailList(@Valid @PathVariable Long periodOderId) {
        List<PeriodOrderDetail> periodOrderDetails=periodOrderService.getPeriodOrderDetailList(periodOderId);
        return Result.success(periodOrderDetails);
    }


    @GetMapping("/getPeriodSendReceiveInfo/{periodOderId}")
    @ApiOperation(value = "根据预约订单号，查询订单的收发人信息",response = PeriodSendReceiveInfo.class)
    public Result<?> getPeriodSendReceiveInfo(@Valid @PathVariable Long periodOderId) {
        List<PeriodSendReceiveInfo> periodSendReceiveInfo = periodOrderService.getPeriodSendReceiveInfo(periodOderId);
        return Result.success(periodSendReceiveInfo);
    }


    @GetMapping("/getPeriodOrderOperationLog/{periodOderId}")
    @ApiOperation(value = "根据预约订单号，查询订单的操作日志信息",response = PeriodOrderOperationLogVO.class)
    public Result<?> getPeriodOrderOperationLog(@Valid @PathVariable Long periodOderId) {
        List<PeriodOrderOperationLogVO> periodOrderOperationLog = periodOrderService.getPeriodOrderOperationLog(periodOderId);
        return Result.success(periodOrderOperationLog);
    }


    @GetMapping("/getPeriodPayInfo/{periodOderId}")
    @ApiOperation(value = "根据预约订单号，查询订单的支付信息",response = PeriodOrderPayVO.class)
    public Result<?> getPeriodPayInfo(@Valid @PathVariable Long periodOderId) {
        List<PeriodOrderPayVO> periodPayInfo = periodOrderService.getPeriodPayInfo(periodOderId);
        return Result.success(periodPayInfo);
    }


    @GetMapping("/getPeriodShippingInfo/{periodOderId}")
    @ApiOperation(value = "根据预约订单号，查询订单的发货历史信息",response = PeriodOrderShippingVO.class)
    public Result<?> getPeriodShippingInfo(@Valid @PathVariable Long periodOderId) {
        List<PeriodOrderShippingVO> periodShippingInfo = periodOrderService.getPeriodShippingInfo(periodOderId);
        return Result.success(periodShippingInfo);
    }

}