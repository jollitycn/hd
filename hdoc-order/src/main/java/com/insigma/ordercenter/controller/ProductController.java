package com.insigma.ordercenter.controller;


import com.insigma.ordercenter.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-08
 */
@RestController
@RequestMapping("/product")
@Api(tags = {"商品管理"})
public class ProductController extends BaseController {

    @GetMapping("/test")
    @ApiOperation("测试接口")
    public Result<?> test() {
        return Result.success();
    }
}
