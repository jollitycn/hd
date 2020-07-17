package com.insigma.ordercenter.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.dto.OrderSourceDTO;
import com.insigma.ordercenter.entity.vo.OrderSourceListVO;
import com.insigma.ordercenter.service.IOrderSourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.insigma.ordercenter.controller.BaseController;

import javax.annotation.Resource;

/**
 * <p>
 * 订单来源定义表 前端控制器
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-16
 */
@RestController
@RequestMapping("/order-source")
@Api(tags = {"货主信息管理"})
public class OrderSourceController extends BaseController {

    @Resource
    private IOrderSourceService orderSourceService;

    @GetMapping("/list")
    @ApiOperation(value = "货主信息列表", response = OrderSourceListVO.class)
    public Result<?> list(OrderSourceDTO orderSourceDTO) {

        Page<OrderSourceListVO> page = new Page<>();

        IPage<OrderSourceListVO> result = orderSourceService.getOrderSourceList(page, orderSourceDTO);

        return Result.success(result);
    }

//    @GetMapping("/detail/{productId}")
//    @ApiOperation(value = "获取商品详情",
}
