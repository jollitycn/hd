package com.insigma.ordercenter.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.dto.ExpressCompanyAddDTO;
import com.insigma.ordercenter.entity.dto.shop.ShopEdit;
import com.insigma.ordercenter.entity.dto.shop.ShopQueryRequest;
import com.insigma.ordercenter.entity.dto.shop.ShopQueryResponse;
import com.insigma.ordercenter.entity.dto.shop.ShopSetting;
import com.insigma.ordercenter.entity.vo.ExpressCompanyListVO;
import com.insigma.ordercenter.service.IShopService;
import com.insigma.ordercenter.utils.DataUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * <p>
 * 店铺表 前端控制器
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-22
 */

@RestController
@RequestMapping("/shop")
@Api(tags = {"店铺"})
public class ShopController extends BaseController {

    @Resource
    private IShopService shopService;

    @GetMapping("/list")
    @ApiOperation(value = "获取列表", response = ShopQueryResponse.class)
    @Transactional(rollbackFor = RuntimeException.class)
    public Result<?> list(@Valid ShopQueryRequest request) {
        IPage<ShopQueryResponse> result = shopService.page(request);
        return Result.success(result);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增")
    @Transactional(rollbackFor = RuntimeException.class)
    public Result<?> add(@Valid @RequestBody ShopEdit data) {

        boolean status = shopService.add(data,redisUser().getUserId());
        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_INSERT_ERROR);
        }
    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑")
    @Transactional(rollbackFor = RuntimeException.class)
    public Result<?> edit(@Valid @RequestBody ShopEdit data) {
        boolean status = shopService.edit(data);
        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_UPDATE_ERROR);
        }
    }

    @PutMapping("/setting")
    @ApiOperation(value = "设置")
    @Transactional(rollbackFor = RuntimeException.class)
    public Result<?> setting(@Valid @RequestBody ShopSetting data) {
        boolean status = shopService.setting(data);
        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_UPDATE_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除")
    @Transactional(rollbackFor = RuntimeException.class)
    public Result<?> delete(@PathVariable Long id) {
        boolean status = shopService.delete(id);
        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_UPDATE_ERROR);
        }
    }

    @PutMapping("/disable/{id}")
    @ApiOperation(value = "停用")
    @Transactional(rollbackFor = RuntimeException.class)
    public Result<?> disable(@PathVariable Long id) {
        boolean status = shopService.disable(id);
        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_UPDATE_ERROR);
        }
    }

    @PutMapping("/enable/{id}")
    @ApiOperation(value = "启用")
    @Transactional(rollbackFor = RuntimeException.class)
    public Result<?> enable(@PathVariable Long id) {
        boolean status = shopService.enable(id);
        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_UPDATE_ERROR);
        }
    }
}
