package com.insigma.ordercenter.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.dto.AddComboDTO;
import com.insigma.ordercenter.entity.dto.ProductAddDTO;
import com.insigma.ordercenter.entity.dto.ProductListDTO;
import com.insigma.ordercenter.entity.dto.ProductUpdateDTO;
import com.insigma.ordercenter.entity.vo.ProductDetailVO;
import com.insigma.ordercenter.entity.vo.ProductListPageVO;
import com.insigma.ordercenter.entity.vo.ProductStockInfoVO;
import com.insigma.ordercenter.service.IExpressWarehouseRelationService;
import com.insigma.ordercenter.service.IProductService;
import com.insigma.ordercenter.service.IWarehouseProductRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IExpressWarehouseRelationService expressWarehouseRelationService;

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

    @PostMapping("/add")
    @ApiOperation(value = "新增商品")
    public Result add(ProductAddDTO productAddDTO) {

        boolean status = productService.add(productAddDTO);

        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_INSERT_ERROR);
        }

    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑商品")
    public Result edit(ProductUpdateDTO productUpdateDTO) {

        boolean status = productService.edit(productUpdateDTO);

        if (status) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_UPDATE_ERROR);
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
    public Result designatedWarehouse(Long expressCompanyId,Long warehouseId) {

        boolean status = expressWarehouseRelationService.designatedWarehouse(expressCompanyId,warehouseId);

        if (status) {
            return Result.success();
        }
        return Result.error(CodeMsg.DATA_INSERT_ERROR);
    }

    @PutMapping("/changePriority")
    @ApiOperation(value = "更改优先级")
    public Result changePriority(Long expressWarehouseRelationId,Integer value) {

        boolean status = expressWarehouseRelationService.changePriority(expressWarehouseRelationId,value);

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

}
