package com.insigma.ordercenter.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.constant.Constant;
import com.insigma.ordercenter.entity.dto.*;
import com.insigma.ordercenter.entity.vo.ProductDetailVO;
import com.insigma.ordercenter.entity.vo.ProductListPageVO;
import com.insigma.ordercenter.entity.vo.ProductStockInfoVO;
import com.insigma.ordercenter.entity.vo.ShopProductVO;
import com.insigma.ordercenter.service.IProductService;
import com.insigma.ordercenter.service.IWarehouseProductRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author Jason
 * @since 2020-07-08
 */
@RestController
@RequestMapping("/product")
@Api(tags = {"商品管理"})
public class ProductController extends BaseController {

    @Resource
    private IProductService productService;

    @Resource
    private IWarehouseProductRelationService warehouseProductRelationService;




    @GetMapping("/list")
    @ApiOperation(value = "获取商品列表", response = ProductListPageVO.class)
    public Result list(ProductListDTO productListDTO) {

        Page<ProductListPageVO> page = new Page<>(productListDTO.getPageNum(), productListDTO.getPageSize());

        IPage<ProductListPageVO> result = productService.getProductListPage(page, productListDTO);

        return Result.success(result);
    }

    @GetMapping("/detail/{productId}")
    @ApiOperation(value = "获取商品详情", response = ProductDetailVO.class)
    public Result detail(@PathVariable Long productId) {

        ProductDetailVO productDetailVO = productService.getProductDetail(productId);

        return Result.success(productDetailVO);
    }

    @GetMapping("/getProductTotalQuantity/{productId}")
    @ApiOperation(value = "获取商品库存总数量")
    public Result getProductTotalQuantity(@PathVariable Long productId) {

        int totalQuantity = warehouseProductRelationService.getTotalStock(productId);

        return Result.success(totalQuantity);
    }

    @GetMapping("/getProductStockInfo/{productId}")
    @ApiOperation(value = "获取商品库存情况", response = ProductStockInfoVO.class)
    public Result getProductStockInfo(@PathVariable Long productId) {

        List<ProductStockInfoVO> result = warehouseProductRelationService.getProductStockInfo(productId);

        return Result.success(result);
    }

    @DeleteMapping("/delProductStock/{warehouseProductRelationId}")
    @ApiOperation(value = "删除商品库存")
    public Result delProductStock(@PathVariable Long warehouseProductRelationId) {

        boolean result = warehouseProductRelationService.delProductStock(warehouseProductRelationId);

        if (result) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_DELETE_ERROR);
        }

    }

    @PostMapping("/add")
    @ApiOperation(value = "新增/编辑商品")
    public Result add(@RequestBody  ProductAddDTO productAddDTO) {

        boolean status = productService.add(productAddDTO);

        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_INSERT_ERROR);
        }

    }


    @DeleteMapping("/delete/{productId}")
    @ApiOperation(value = "删除商品")
    public Result delete(@PathVariable Long productId) {

        boolean status = productService.delete(productId);

        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_DELETE_ERROR);
        }
    }

    @PostMapping("/designatedWarehouse")
    @ApiOperation(value = "指定仓库")
    public Result designatedWarehouse(@RequestBody DesignatedWarehouseDTO designatedWarehouseDTO) {

        boolean status =warehouseProductRelationService.designatedWarehouse(designatedWarehouseDTO);

        if (status) {
            return Result.success();
        }
        return Result.error(CodeMsg.DATA_INSERT_ERROR);
    }

    @PutMapping("/changePriority")
    @ApiOperation(value = "更改优先级")
    public Result changePriority(Long warehouseProductRelationId,Integer priority) {

        boolean status = warehouseProductRelationService.changePriority(warehouseProductRelationId,priority);

        if (status) {
            return Result.success();
        }
            return Result.error(CodeMsg.DATA_UPDATE_ERROR);
    }

    @PostMapping("/addCombo")
    @ApiOperation(value = "添加商品组合")
    public Result addCombo(AddComboDTO addComboDTO) {

        boolean status = productService.addCombo(addComboDTO);

        if (status) {
            return Result.success();
        }
        return Result.error(CodeMsg.DATA_INSERT_ERROR);
    }

    @PutMapping("/disable/{productId}")
    @ApiOperation(value = "禁用/启用商品")
    public Result addCombo(@PathVariable("productId") Long productId) {

        boolean status = productService.disable(productId);

        if (status) {
            return Result.success();
        }
        return Result.error(CodeMsg.DATA_UPDATE_ERROR);
    }

    @GetMapping("/calculation")
    @ApiOperation(value = "计算预约发送次数")
    public Result calculation(Double unitQuantity,Double sendNumber) {

        //除数0判断
        if(Constant.SYS_ZERO==sendNumber.intValue()){
            return Result.error(CodeMsg.PARAMETER_ERROR);
        }

        //向上取整返回
        Double result=unitQuantity/sendNumber;

        return Result.success(Math.ceil(result));

    }


    @GetMapping("/getProductRatio/{productId}")
    @ApiOperation(value = "获取商品电商发货比例列表", response = ShopProductVO.class)
    public Result getProductRatio(@PathVariable Long productId) {

        List<ShopProductVO> result =productService.getProductRatio(productId);

        return Result.success(result);
    }

    @GetMapping("/editRatio/{productId}")
    @ApiOperation(value = "编辑电商发货比例")
    public Result editRatio(@PathVariable Long productId,
                                  @RequestBody  List<ShopRatioDTO> shopRatioDTOList) {

        boolean  result =productService.editRatio(productId,shopRatioDTOList);

        if (result) {
            return Result.success();
        }
        return Result.error(CodeMsg.DATA_UPDATE_ERROR);
    }

    @GetMapping("/editWarningValue/{spid}")
    @ApiOperation(value = "编辑电商发货预警值")
    public Result editWarningValue(@PathVariable Long spid,Integer warningValue) {

        boolean  result =productService.editWarningValue(spid,warningValue);

        if (result) {
            return Result.success();
        }
        return Result.error(CodeMsg.DATA_UPDATE_ERROR);
    }
}
