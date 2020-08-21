package com.insigma.ordercenter.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.LoginUser;
import com.insigma.ordercenter.entity.dto.AddShippingOrderResultDTO;
import com.insigma.ordercenter.entity.dto.OrderDTO;
import com.insigma.ordercenter.entity.dto.UpdateOrderStatuDTO;
import com.insigma.ordercenter.entity.vo.OrderListVO;
import com.insigma.ordercenter.entity.vo.SendReceiveInfoVO;
import com.insigma.ordercenter.entity.vo.StrategyProductTypeVO;
import com.insigma.ordercenter.service.IStrategyProductTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import com.insigma.ordercenter.controller.BaseController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author youwk
 * @since 2020-08-18
 */
@Api(value = "StrategyController", tags = "策略规则配置")
@Slf4j
@RestController
@RequestMapping("/strategy-product-type")
public class StrategyProductTypeController extends BaseController {

    @Resource
    private IStrategyProductTypeService strategyProductTypeService;

    @PostMapping("/strategyProduct/{paramType}")
    @ApiOperation(value = "商品列表")
    public Result<?> strategyProduct(@Valid @PathVariable Integer paramType) {
        List<StrategyProductTypeVO> strategyProductTypeVOS = strategyProductTypeService.strategyProduct(paramType);
        return Result.success(strategyProductTypeVOS);
    }

    @PostMapping("/strategyProductTypeList/{paramType}")
    @ApiOperation(value = "商品、分类策略列表")
    public Result<?> strategyProductTypeList(@Valid @PathVariable Integer paramType) {
        List<StrategyProductTypeVO> strategyProductTypeVOS = strategyProductTypeService.strategyProductTypeList(paramType);
        return Result.success(strategyProductTypeVOS);
    }


    @PostMapping("/addStrategyProductType")
    @ApiOperation(value = "新增商品、分类策略")
    public Result<?> addStrategyProductType(@Valid @RequestBody StrategyProductTypeVO strategyProductTypeVO) {
        return strategyProductTypeService.addStrategyProductType(strategyProductTypeVO);
    }


    @PostMapping("/deleteStrategyProductType/{strategyProductTypeId}")
    @ApiOperation(value = "删除商品、分类策略")
    public Result<?> deleteStrategyProductType(@Valid @PathVariable Integer strategyProductTypeId) {
        return strategyProductTypeService.deleteStrategyProductType(strategyProductTypeId);
    }

    @GetMapping("/getStrategyProductType/{strategyProductTypeId}")
    @ApiOperation(value = "查看商品、分类策略")
    public Result<?> getStrategyProductType(@Valid @PathVariable Integer strategyProductTypeId) {
        return strategyProductTypeService.getStrategyProductType(strategyProductTypeId);
    }

    @PostMapping("/updateStrategyProductType")
    @ApiOperation(value = "修改商品、分类策略")
    public Result<?> updateStrategyProductType(@Valid @RequestBody StrategyProductTypeVO strategyProductTypeVO) {
        return strategyProductTypeService.updateStrategyProductType(strategyProductTypeVO);
    }

    @PutMapping("/updateStrategyProductTypeIsStop")
    @ApiOperation(value = "修改商品、分类策略是否禁用")
    public Result<?> updateStrategyProductTypeIsStop(StrategyProductTypeVO strategyProductTypeVO) {
        return strategyProductTypeService.updateStrategyProductTypeIsStop(strategyProductTypeVO);
    }

}
