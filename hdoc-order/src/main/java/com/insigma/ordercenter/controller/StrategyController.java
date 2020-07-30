package com.insigma.ordercenter.controller;


import cn.hutool.core.bean.BeanUtil;
import com.google.common.collect.Lists;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.*;
import com.insigma.ordercenter.entity.dto.*;
import com.insigma.ordercenter.entity.vo.StrategyVO;
import com.insigma.ordercenter.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 策略表 前端控制器
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-28
 */
@Api(value = "StrategyController", tags = "策略规则配置")
@Slf4j
@RestController
@RequestMapping("/strategy")
public class StrategyController extends BaseController {

    @Resource
    private IStrategyService strategyService;

    @Resource
    private IShopService shopService;

    @Resource
    private IProductTypeStrategyService productTypeStrategyService;

    @Resource
    private IAccountBlacklistStrategyService accountBlacklistStrategyService;

    @Resource
    private IPhoneBlacklistStrategyService phoneBlacklistStrategyService;

    @Resource
    private IRegionBlacklistStrategyService regionBlacklistStrategyService;

    @Resource
    private IExchangeStrategyService exchangeStrategyService;

    @Resource
    private IGiftStrategyService giftStrategyService;

    @GetMapping("/listStrategy")
    @ApiOperation("获取所有策略")
    public Result<List<StrategyVO>> listStrategy() {
        List<StrategyVO> list = Lists.newArrayList();
        // 查询所有
        List<Strategy> strategyList = strategyService.list();
        // 渲染返回VO
        strategyList.forEach(strategy -> {
            StrategyVO strategyVO = new StrategyVO();
            BeanUtil.copyProperties(strategy, strategyVO);
            list.add(strategyVO);
        });
        return Result.success(list);
    }

    @PutMapping("/enableOrStopStrategy/{strategyId}")
    @ApiImplicitParam(name = "strategyId", value = "策略id", required = true)
    @ApiOperation("启用or禁用策略")
    public Result enableOrStopStrategy(@PathVariable("strategyId") @Valid
                                       @NotNull(message = "策略id不能为空")
                                       @Min(value = 1L, message = "策略id不合法")
                                       @Max(value = Long.MAX_VALUE, message = "策略id不合法") Long strategyId) {
        Strategy select = strategyService.getById(strategyId);
        if (null == select) {
            return Result.error(CodeMsg.STRATEGY_NOT_EXIST);
        }
        Strategy strategy = new Strategy();
        strategy.setStrategyId(strategyId);
        strategy.setIsStop((select.getIsStop() + 1) % 2);
        strategyService.updateById(strategy);
        return Result.success();
    }

    @GetMapping("/getStrategyParam")
    @ApiOperation("获取策略的参数信息")
    public Result<?> getStrategyParam(@Valid StrategyParamDTO req) {
        Object result = strategyService.getStrategyParam(req);
        return Result.success(result);
    }

    @PutMapping("/enableOrStopStrategyOneParam/{shopId}")
    @ApiImplicitParam(name = "shopId", value = "店铺id", required = true)
    @ApiOperation("启用or禁用店铺与策略关系")
    public Result enableOrStopStrategyOneParam(@PathVariable("shopId") @Valid
                                       @NotNull(message = "店铺id不能为空")
                                       @Min(value = 1L, message = "店铺id不合法")
                                       @Max(value = Long.MAX_VALUE, message = "店铺id不合法") Long shopId) {
        Shop select = shopService.getById(shopId);
        if (null == select) {
            return Result.error(CodeMsg.SHOP_NOT_EXIST);
        }
        Shop shop = new Shop();
        shop.setShopId(shopId);
        shop.setIsStop((select.getStrategyStatus() + 1) % 2);
        shopService.updateById(shop);
        return Result.success();
    }

    @PutMapping("/updateStrategyData")
    @ApiOperation("修改策略的自动审单时间or发送物流时间or优先级")
    public Result updateStrategyData(@RequestBody @Valid UpdateTimeLengthDTO req) {
        Strategy select = strategyService.getById(req.getStrategyId());
        if (null == select) {
            return Result.error(CodeMsg.STRATEGY_NOT_EXIST);
        }
        Strategy strategy = new Strategy();
        strategy.setStrategyId(req.getStrategyId());
        if (req.getDataType() == 1) {
            // 修改自动审单时间
            strategy.setAutoAuditTime(req.getValue());
        } else if (req.getDataType() == 2) {
            // 修改推送物流公司时间
            strategy.setExpressTime(req.getValue());
        } else {
            // 修改优先级
            strategy.setPriority(req.getValue());
        }
        strategyService.updateById(strategy);
        return Result.success();
    }

    @PutMapping("/saveProductTypeStrategy")
    @ApiOperation("保存按商品分类拆单参数配置")
    public Result saveProductTypeStrategy(@RequestBody @Valid AddProductTypeStrategyDTO req) {
        productTypeStrategyService.saveProductTypeStrategy(req);
        return Result.success();
    }

    @PostMapping("/addStrategyBlack")
    @ApiOperation("根据类型新增黑名单")
    public Result addStrategyBlack(@RequestBody @Valid AddStrategyBlackDTO req) {
        if (req.getBlackType() == 1) {
            // 账号黑名单
            AccountBlacklistStrategy accountBlacklistStrategy = new AccountBlacklistStrategy();
            BeanUtil.copyProperties(req, accountBlacklistStrategy);
            accountBlacklistStrategyService.save(accountBlacklistStrategy);
        } else if(req.getBlackType() == 2){
            // 手机号黑名单
            PhoneBlacklistStrategy phoneBlacklistStrategy = new PhoneBlacklistStrategy();
            BeanUtil.copyProperties(req, phoneBlacklistStrategy);
            phoneBlacklistStrategyService.save(phoneBlacklistStrategy);
        } else {
            // 区域黑名单
            RegionBlacklistStrategy regionBlacklistStrategy = new RegionBlacklistStrategy();
            BeanUtil.copyProperties(req, regionBlacklistStrategy);
            regionBlacklistStrategyService.save(regionBlacklistStrategy);
        }
        return Result.success();
    }

    @PutMapping("/removeBlackByType")
    @ApiOperation("移除指定类型的黑名单")
    public Result removeBlackByType(@RequestBody @Valid UpdateBlackDeleteDTO req) {
        if (req.getBlackType() == 1) {
            // 账号黑名单
            AccountBlacklistStrategy accountBlacklistStrategy = new AccountBlacklistStrategy();
            accountBlacklistStrategy.setAccountBlacklistStrategyId(req.getId());
            accountBlacklistStrategy.setIsDeleted(1);
            accountBlacklistStrategyService.updateById(accountBlacklistStrategy);
        } else if(req.getBlackType() == 2){
            // 手机号黑名单
            PhoneBlacklistStrategy phoneBlacklistStrategy = new PhoneBlacklistStrategy();
            phoneBlacklistStrategy.setPhoneBlacklistStrategyId(req.getId());
            phoneBlacklistStrategy.setIsDeleted(1);
            phoneBlacklistStrategyService.updateById(phoneBlacklistStrategy);
        } else {
            // 区域黑名单
            RegionBlacklistStrategy regionBlacklistStrategy = new RegionBlacklistStrategy();
            regionBlacklistStrategy.setRegionBlacklistStrategyId(req.getId());
            regionBlacklistStrategy.setIsDeleted(1);
            regionBlacklistStrategyService.updateById(regionBlacklistStrategy);
        }
        return Result.success();
    }

    @PutMapping("/updateExchangeOrGiftStrategyStatus")
    @ApiOperation("启用or禁用拆单策略参数or赠品策略参数")
    public Result updateExchangeOrGiftStrategyStatus(@RequestBody @Valid ExchangeOrGiftStrategyStatusDTO req) {
        if (req.getDataType() == 1) {
            // 禁用启用拆单策略参数
            ExchangeStrategy select = exchangeStrategyService.getById(req.getId());
            if (null == select) {
                return Result.error(CodeMsg.STRATEGY_PARAM_NOT_EXIST);
            }
            ExchangeStrategy exchangeStrategy = new ExchangeStrategy();
            exchangeStrategy.setExchangeStrategyId(req.getId());
            exchangeStrategy.setIsStop((select.getIsStop() + 1) % 2);
            exchangeStrategyService.updateById(exchangeStrategy);
        } else {
            // 禁用启用赠品策略参数
            GiftStrategy select = giftStrategyService.getById(req.getId());
            if (null == select) {
                return Result.error(CodeMsg.STRATEGY_PARAM_NOT_EXIST);
            }
            GiftStrategy giftStrategy = new GiftStrategy();
            giftStrategy.setGiftStrategyId(req.getId());
            giftStrategy.setIsStop((select.getIsStop() + 1) % 2);
            giftStrategyService.updateById(giftStrategy);
        }
        return Result.success();
    }

    @PostMapping("/addExchangeStrategy")
    @ApiOperation("新增换货策略参数")
    public Result addExchangeStrategy(@RequestBody @Valid AddExchangeStrategyDTO req) {
        ExchangeStrategy exchangeStrategy = BeanUtil.toBean(req, ExchangeStrategy.class);
        exchangeStrategyService.save(exchangeStrategy);
        return Result.success();
    }

    @PostMapping("/addGiftStrategy")
    @ApiOperation("新增赠品策略参数")
    public Result addGiftStrategy(@RequestBody @Valid AddGiftStrategyDTO req) {
        giftStrategyService.addGiftStrategy(req);
        return Result.success();
    }

}