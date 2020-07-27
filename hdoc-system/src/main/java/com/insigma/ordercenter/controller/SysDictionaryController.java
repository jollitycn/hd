package com.insigma.ordercenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.constant.Constant;
import com.insigma.ordercenter.entity.SysDictionary;
import com.insigma.ordercenter.entity.SysUser;
import com.insigma.ordercenter.entity.vo.SysDictVO;
import com.insigma.ordercenter.service.ISysDictionaryService;
import com.insigma.ordercenter.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 系统数字字典 前端控制器
 * </p>
 *
 * @author Jason
 * @since 2020-01-09
 */
@Api(tags = {"系统数据字典管理"})
@RestController
@RequestMapping("/sys-dictionary")
public class SysDictionaryController extends BaseController {

    @Resource
    private ISysDictionaryService sysDictionaryService;

    @Resource
    private ISysUserService sysUserService;

    @PutMapping("/add")
    @ApiOperation("新增数据字典")
    public Result<?> add(@RequestBody SysDictionary sysDictionary) {

        Long userId = redisUser().getUserId();
        QueryWrapper<SysDictionary> wrapper = new QueryWrapper<>();
        wrapper.eq("data_code", sysDictionary.getDataCode());
        wrapper.eq("is_deleted", Constant.SYS_ZERO);
        int count = sysDictionaryService.count(wrapper);
        if (count > 0) {
            return Result.error(CodeMsg.DIC_CODE_REPEAT);
        }
        sysDictionary.setModifyId(userId);
        sysDictionary.setModifyTime(LocalDateTime.now());
        boolean status = sysDictionaryService.save(sysDictionary);
        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_INSERT_ERROR);
        }
    }

    @DeleteMapping("/delete/{dictionaryId}")
    @ApiOperation("删除数据字典")
    public Result<?> delete(@PathVariable Long dictionaryId) {

        return sysDictionaryService.deleteDictionary(dictionaryId);
    }

    @PostMapping("/update")
    @ApiOperation("更新数据字典")
    public Result<?> update(@RequestBody SysDictionary sysDictionary) {
        Long userId = redisUser().getUserId();

        // 校验修改的编码不能重复
        QueryWrapper<SysDictionary> wrapper = new QueryWrapper<>();
        wrapper.notIn("dictionary_id", sysDictionary.getDictionaryId());
        wrapper.eq("is_deleted", Constant.SYS_ZERO);
        wrapper.eq("data_code", sysDictionary.getDataCode());
        int count = sysDictionaryService.count(wrapper);
        if (count > 0) {
            return Result.error(CodeMsg.DIC_CODE_REPEAT);
        }

        sysDictionary.setModifyId(userId);
        sysDictionary.setModifyTime(LocalDateTime.now());
        boolean status = sysDictionaryService.updateById(sysDictionary);
        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_UPDATE_ERROR);
        }
    }

    @GetMapping("/detail/{dictionaryId}")
    @ApiOperation(value = "获取字典详情", response = SysDictionary.class)
    public Result<?> detail(@PathVariable Long dictionaryId) {

        SysDictionary sysDictionary = sysDictionaryService.getById(dictionaryId);

        //获取操作人信息
        SysUser sysUser = sysUserService.getById(sysDictionary.getModifyId());
        sysDictionary.setModifyName(sysUser.getUserName());

        return Result.success(sysDictionary);
    }

    @GetMapping("/list")
    @ApiOperation(value = "获取数据字典列表", response = SysDictionary.class)
    public Result<?> list(Long parentId) {

        QueryWrapper<SysDictionary> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", Constant.SYS_ZERO);
        if (null != parentId) {
            wrapper.eq("parent_id", parentId);
        }
        List<SysDictionary> result = sysDictionaryService.list(wrapper);
        return Result.success(result);
    }

    @GetMapping("/listAllData")
    @ApiOperation(value = "获取全部数据字典", response = SysDictVO.class)
    public Result<?> list() {
        List<SysDictVO> list = sysDictionaryService.getAllDict();
        return Result.success(list);
    }
}