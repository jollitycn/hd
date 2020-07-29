package com.insigma.ordercenter.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.controller.BaseController;

import com.insigma.ordercenter.entity.dto.ExpressFeeAddDTO;
import com.insigma.ordercenter.entity.dto.ExpressFeeEditDTO;
import com.insigma.ordercenter.entity.dto.ExpressFeePageDTO;
import com.insigma.ordercenter.entity.vo.ExpressFeeDetailVO;
import com.insigma.ordercenter.entity.vo.ExpressFeePageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.insigma.ordercenter.controller.BaseController;
 import com.insigma.ordercenter.service.IExpressFeeService;

/**
 * <p>
 * 物流报价表 前端控制器
 * </p>
 *
 * @author Jason
 * @since 2020-07-28
 */
@RestController
@RequestMapping("/express-fee")
@Api(tags = {"物流报价表"})
public class ExpressFeeController extends BaseController {
 @Resource
 private IExpressFeeService iExpressFeeService;

 @PostMapping("/page")
 @ApiOperation(value = "获取列表", response = ExpressFeePageVO.class)
 public Result<?> page(@Valid @RequestBody ExpressFeePageDTO expressFeePageDTO) {
  IPage<ExpressFeePageVO> result = iExpressFeeService.page(expressFeePageDTO);
  return Result.success(result);
 }

 @PostMapping("/add")
 @ApiOperation(value = "新增")
 public Result<?> add(@Valid @RequestBody ExpressFeeAddDTO expressFeeAddDTO) {
  boolean status = iExpressFeeService.add(expressFeeAddDTO);

  if (status) {
   return Result.success();
  } else {
   return Result.error(CodeMsg.DATA_INSERT_ERROR);
  }
 }

 @PutMapping("/edit")
 @ApiOperation(value = "编辑")
 public Result<?> edit(@Valid @RequestBody ExpressFeeEditDTO expressFeeEditDTO) {
  boolean status = iExpressFeeService.edit(expressFeeEditDTO);
  if (status) {
   return Result.success();
  } else {
   return Result.error(CodeMsg.DATA_UPDATE_ERROR);
  }
 }

 @DeleteMapping("/delete/{id}")
 @ApiOperation(value = "删除")
 public Result<?> delete(@PathVariable Long id) {

  boolean status = iExpressFeeService.delete(id);

  if (status) {
   return Result.success();
  } else {
   return Result.error(CodeMsg.DATA_UPDATE_ERROR);
  }
 }


 @DeleteMapping("/detail/{id}")
 @ApiOperation(value = "详情")
 public Result<?> detail(@Valid @PathVariable Long id) {
  ExpressFeeDetailVO result = iExpressFeeService.detail(id);
  return Result.success(result);
 }
}
