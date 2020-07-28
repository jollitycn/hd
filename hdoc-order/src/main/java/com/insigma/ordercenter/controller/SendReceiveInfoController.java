package com.insigma.ordercenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.OrderDetail;
import com.insigma.ordercenter.entity.SendReceiveInfo;
import com.insigma.ordercenter.entity.vo.OrderSourceListVO;
import com.insigma.ordercenter.entity.vo.SendReceiveInfoVO;
import com.insigma.ordercenter.entity.vo.UpdateSendReceiveInfoVO;
import com.insigma.ordercenter.service.ISendReceiveInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import com.insigma.ordercenter.controller.BaseController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 收发人信息 前端控制器
 * </p>
 *
 * @author AH
 * @since 2020-07-22
 */
@RestController
@RequestMapping("/send-receive-info")
@Api(tags = {"收发人信息"})
public class SendReceiveInfoController extends BaseController {

    @Resource
    private ISendReceiveInfoService sendReceiveInfoService;

    @GetMapping("/getSendReceiveInfo/{orderId}")
    @ApiOperation(value = "根据订单查询收发人信息以及店铺信息")
    public Result<?> getSendReceiveInfo(@Valid @PathVariable Long orderId) {
        //新增订单时，传选择的订单id；编辑时，传订单表中的原订单号即可
        SendReceiveInfoVO sendReceiveInfo = sendReceiveInfoService.getSendReceiveInfo(orderId);
        return Result.success(sendReceiveInfo);
    }

    @PutMapping("/updateSendReceiveInfo/{orderId}")
    @ApiOperation(value = "根据订单修改收发件人信息")
    public Result<?> updateSendReceiveInfo(@Valid UpdateSendReceiveInfoVO updateSendReceiveInfoVO) {
        Boolean aBoolean = sendReceiveInfoService.updateSendReceiveInfo(updateSendReceiveInfoVO);
        return Result.success(aBoolean);
    }

}
