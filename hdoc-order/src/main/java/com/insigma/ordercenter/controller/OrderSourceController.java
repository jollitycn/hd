package com.insigma.ordercenter.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.dto.OrderSourceAddDTO;
import com.insigma.ordercenter.entity.dto.OrderSourceDTO;
import com.insigma.ordercenter.entity.dto.OrderSourceEditDTO;
import com.insigma.ordercenter.entity.vo.OrderSourceListVO;
import com.insigma.ordercenter.service.IOrderSourceService;
import com.insigma.ordercenter.service.impl.MyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import com.insigma.ordercenter.controller.BaseController;

import javax.annotation.Resource;

/**
 * <p>
 * 订单来源定义表 前端控制器
 * </p>
 *
 * @author Jason
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

        Page<OrderSourceListVO> page = new Page<>(orderSourceDTO.getPageNum(), orderSourceDTO.getPageSize());

        IPage<OrderSourceListVO> result = orderSourceService.getOrderSourceList(page, orderSourceDTO);

        return Result.success(result);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增货主")
    public Result<?> add(OrderSourceAddDTO orderSourceAddDTO) {

        boolean status = false;
        try {
            status = orderSourceService.add(orderSourceAddDTO, redisUser());
        } catch (MyException e) {
            return Result.error(e.getCodeMsg());
        }

        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_INSERT_ERROR);
        }
    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑货主")
    public Result<?> edit(OrderSourceEditDTO orderSourceEditDTO) {

        boolean status = false;
        try {
            status = orderSourceService.edit(orderSourceEditDTO);
        } catch (MyException e) {
            return Result.error(e.getCodeMsg());
        }

        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_UPDATE_ERROR);
        }
    }

    @DeleteMapping("/delete/{orderSourceId}")
    @ApiOperation(value = "删除货主")
    public Result<?> delete(@PathVariable Long orderSourceId) {

        boolean status = orderSourceService.delete(orderSourceId);

        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_DELETE_ERROR);
        }
    }

    @PutMapping("/block/{orderSourceId}")
    @ApiOperation(value = "停用货主")
    public Result<?> block(@PathVariable Long orderSourceId) {

        boolean status = orderSourceService.block(orderSourceId);

        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_UPDATE_ERROR);
        }
    }

    @PutMapping("/unblock/{orderSourceId}")
    @ApiOperation(value = "启用货主")
    public Result<?> unblock(@PathVariable Long orderSourceId) {

        boolean status = orderSourceService.unblock(orderSourceId);

        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_UPDATE_ERROR);
        }
    }
}
