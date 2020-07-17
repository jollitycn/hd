package com.insigma.ordercenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.SysRegion;
import com.insigma.ordercenter.service.ISysRegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 系统地区表 前端控制器
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/sys-region")
@Api(tags = {"系统地区管理"})
public class SysRegionController extends BaseController {

    @Resource
    private ISysRegionService sysRegionService;

    @GetMapping("/getProvinceList")
    @ApiOperation(value = "获取省份列表", response = SysRegion.class)
    public Result<?> getProvinceList() {

        QueryWrapper<SysRegion> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", 100000);
        List<SysRegion> result = sysRegionService.list(wrapper);
        return Result.success(result);

    }

    @GetMapping("/getCityList/{provinceId}")
    @ApiOperation(value = "获取城市列表", response = SysRegion.class)
    public Result<?> getCityList(@PathVariable Long provinceId) {

        QueryWrapper<SysRegion> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", provinceId);
        List<SysRegion> result = sysRegionService.list(wrapper);
        return Result.success(result);
    }

    @GetMapping("/getDistrictList/{cityId}")
    @ApiOperation(value = "获取区县列表", response = SysRegion.class)
    public Result<?> getDistrictList(@PathVariable Long cityId) {

        QueryWrapper<SysRegion> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", cityId);
        List<SysRegion> result = sysRegionService.list(wrapper);
        return Result.success(result);
    }

    @GetMapping("/{regionId}")
    @ApiOperation("地区编号详情")
    public SysRegion detail(@PathVariable Integer regionId) {
        SysRegion region = this.sysRegionService.getById(regionId);
        return region;
    }

}
