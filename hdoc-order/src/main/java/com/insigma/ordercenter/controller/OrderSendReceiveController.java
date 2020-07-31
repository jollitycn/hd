package com.insigma.ordercenter.controller;


import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.vo.SendReceiveInfoVO;
import com.insigma.ordercenter.entity.vo.UpdateSendReceiveInfoVO;
import com.insigma.ordercenter.service.IOrderSendReceiveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 收发人信息 前端控制器
 * </p>
 *
 * @author AH
 * @since 2020-07-27
 */
@RestController
@RequestMapping("/order-send-receive")
@Api(tags = {"收发人信息"})
public class OrderSendReceiveController extends BaseController {


    @Resource
    private IOrderSendReceiveService orderSendReceiveService;

    @GetMapping("/getSendReceiveInfo/{orderId}")
    @ApiOperation(value = "根据订单查询收发人信息以及店铺信息",response = SendReceiveInfoVO.class)
    public Result<?> getSendReceiveInfo(@Valid @PathVariable Long orderId) {
        //新增订单时，传选择的订单id；编辑时，传订单表中的原订单号即可
        SendReceiveInfoVO sendReceiveInfo = orderSendReceiveService.getSendReceiveInfo(orderId);
        return Result.success(sendReceiveInfo);
    }

    @PutMapping("/updateSendReceiveInfo/{orderId}")
    @ApiOperation(value = "根据订单修改收发件人信息")
    public Result<?> updateSendReceiveInfo(@Valid UpdateSendReceiveInfoVO updateSendReceiveInfoVO) {
        Boolean aBoolean = orderSendReceiveService.updateSendReceiveInfo(updateSendReceiveInfoVO);
        return Result.success(aBoolean);
    }

}
