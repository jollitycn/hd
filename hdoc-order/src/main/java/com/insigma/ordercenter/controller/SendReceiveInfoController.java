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
public class SendReceiveInfoController extends BaseController {



}
