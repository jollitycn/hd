package com.insigma.ordercenter.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.dto.ExpressCompanyAddDTO;
import com.insigma.ordercenter.entity.dto.ExpressCompanyEditDTO;
import com.insigma.ordercenter.entity.dto.ExpressCompanyListDTO;
import com.insigma.ordercenter.entity.vo.ExpressCompanyListVO;
import com.insigma.ordercenter.service.IExpressCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import com.insigma.ordercenter.controller.BaseController;

import javax.annotation.Resource;

/**
 * <p>
 * 物流公司 前端控制器
 * </p>
 *
 * @author Jason
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/express-company")
@Api(tags = {"承运商管理"})
public class ExpressCompanyController extends BaseController {

    @Resource
    private IExpressCompanyService expressCompanyService;

    @GetMapping("/list")
    @ApiOperation(value = "获取承运商列表", response = ExpressCompanyListVO.class)
    public Result<?> list(ExpressCompanyListDTO expressCompanyListDTO) {

        Page<ExpressCompanyListVO> page = new Page<>(expressCompanyListDTO.getPageNum(), expressCompanyListDTO.getPageSize());

        IPage<ExpressCompanyListVO> result = expressCompanyService.getCompanyList(page, expressCompanyListDTO);

        return Result.success(result);

    }

    @PostMapping("/add")
    @ApiOperation(value = "新增承运商")
    public Result<?> add(ExpressCompanyAddDTO expressCompanyAddDTO) {

        boolean status = expressCompanyService.add(expressCompanyAddDTO, redisUser());

        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_INSERT_ERROR);
        }
    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑承运商")
    public Result<?> edit(ExpressCompanyEditDTO expressCompanyEditDTO) {

        boolean status = expressCompanyService.edit(expressCompanyEditDTO);

        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_UPDATE_ERROR);
        }
    }

    @DeleteMapping("/delete/{expressCompanyId}")
    @ApiOperation(value = "删除承运商")
    public Result<?> delete(@PathVariable Long expressCompanyId) {

        boolean status = expressCompanyService.delete(expressCompanyId);

        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_UPDATE_ERROR);
        }
    }

    @PutMapping("/block/{expressCompanyId}")
    @ApiOperation(value = "停用承运商")
    public Result<?> block(@PathVariable Long expressCompanyId) {

        boolean status = expressCompanyService.block(expressCompanyId);

        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_UPDATE_ERROR);
        }
    }

    @PutMapping("/unblock/{expressCompanyId}")
    @ApiOperation(value = "启用承运商")
    public Result<?> unblock(@PathVariable Long expressCompanyId) {

        boolean status = expressCompanyService.unblock(expressCompanyId);

        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_UPDATE_ERROR);
        }
    }
}
