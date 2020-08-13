package com.insigma.ordercenter.controller;


import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.LoginUser;
import com.insigma.ordercenter.entity.OrderOperationLog;
import com.insigma.ordercenter.entity.vo.OrderOperationLogVO;
import com.insigma.ordercenter.entity.vo.SendReceiveInfoVO;
import com.insigma.ordercenter.service.IOrderOperationLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 订单操作日志表 前端控制器
 * </p>
 *
 * @author AH
 * @since 2020-07-27
 */
@RestController
@RequestMapping("/order-operation-log")
public class OrderOperationLogController extends BaseController {

    @Resource
    private IOrderOperationLogService orderOperationLogService;

    @PostMapping("/addOrderOperationLog")
    @ApiOperation(value = "新增订单操作日志")
    public Result<?> addOrderOperationLog(@Valid @RequestBody OrderOperationLog orderOperationLog) {
        LoginUser loginUser = redisUser();
        return orderOperationLogService.addOrderOperationLog(orderOperationLog,loginUser);
    }

}
