package com.insigma.ordercenter.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.LoginUser;
import com.insigma.ordercenter.entity.dto.EditShippingOrderDTO;
import com.insigma.ordercenter.entity.dto.EditShippingOrderProductDTO;
import com.insigma.ordercenter.entity.dto.ShippingOrderDTO;
import com.insigma.ordercenter.entity.vo.LogisticsVO;
import com.insigma.ordercenter.entity.vo.ShippingOrderDetailVO;
import com.insigma.ordercenter.entity.vo.ShippingOrderVO;
import com.insigma.ordercenter.service.IShippingOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 发货单模块
 * </p>
 *
 * @author Xuchao
 * @since 2020-07-21
 */
@RestController
@RequestMapping("/shipping-order")
@Api(tags = {"发货单模块-xuchao"})
public class ShippingOrderController extends BaseController{


    @Autowired
    private IShippingOrderService shippingOrderService;

    @GetMapping("/list")
    @ApiOperation(value = "发货单列表", response = ShippingOrderVO.class)
    public Result list(ShippingOrderDTO shippingOrderDTO) {

        IPage<ShippingOrderVO> result = shippingOrderService.getShippingOrderList(shippingOrderDTO);

        return Result.success(result);
    }

    @GetMapping("/detail/{shippingOrderId}")
    @ApiOperation(value = "发货单明细", response = ShippingOrderVO.class)
    public Result detail(@PathVariable Long shippingOrderId) {

        ShippingOrderDetailVO result = shippingOrderService.getShippingDetail(shippingOrderId);

        return Result.success(result);
    }

    @PostMapping("/increaseCargo")
    @ApiOperation("增加补货单")
    public Result increaseCargo(EditShippingOrderDTO editShippingOrderDTO) {

        //TODO
        LoginUser loginUser=redisUser();

        Boolean result = shippingOrderService.increaseCargo(editShippingOrderDTO);

        if(result){
            return Result.success();
        }

        return Result.error(CodeMsg.DATA_INSERT_ERROR);
    }

    @PutMapping("/changeAddress/{shippingOrderId}")
    @ApiOperation(value ="更改地址")
    public Result changeAddress(@PathVariable Long shippingOrderId,
                                EditShippingOrderDTO editShippingOrderDTO) {

        Boolean result = shippingOrderService.changeAddress(shippingOrderId,editShippingOrderDTO);

        if(result){
            return Result.success();
        }

        return Result.error(CodeMsg.DATA_UPDATE_ERROR);
    }

    @PutMapping("/changeProduct/{shippingOrderId}")
    @ApiOperation(value ="更改商品")
    public Result changeProduct(@PathVariable Long shippingOrderId,
                                @RequestBody EditShippingOrderProductDTO editParameters) {

        Boolean result = shippingOrderService.changeProduct(shippingOrderId,editParameters);

        if(result){
            return Result.success();
        }

        return Result.error(CodeMsg.DATA_UPDATE_ERROR);
    }

    @PutMapping("/changeWarehouse/{shippingOrderId}")
    @ApiOperation(value ="更改仓库")
    public Result changeWarehouse(@PathVariable Long shippingOrderId,
                                  EditShippingOrderDTO editShippingOrderDTO) {

        Boolean result = shippingOrderService.changeWarehouse(shippingOrderId,editShippingOrderDTO);

        if(result){
            return Result.success();
        }

        return Result.error(CodeMsg.DATA_UPDATE_ERROR);
    }

    @PutMapping("/cancel/{shippingOrderId}")
    @ApiOperation(value ="取消发货单")
    public Result cancel(@PathVariable Long shippingOrderId) {

        Boolean result = shippingOrderService.cancel(shippingOrderId);

        if(result){
            return Result.success();
        }

        return Result.error(CodeMsg.DATA_UPDATE_ERROR);

    }

    @PutMapping("/frozen/{shippingOrderId}")
    @ApiOperation("冻结发货单")
    public Result frozen(@PathVariable Long shippingOrderId) {
        Boolean result = shippingOrderService.frozen(shippingOrderId);
        if(result){
            return Result.success();
        }

        return Result.error(CodeMsg.DATA_UPDATE_ERROR);
    }

    @PutMapping("/rejection/{shippingOrderId}")
    @ApiOperation("拒收发货单")
    public Result rejection(@PathVariable Long shippingOrderId,Integer sourceType,String reason) {
        Boolean result = shippingOrderService.rejection(shippingOrderId,sourceType,reason);
        if(result){
            return Result.success();
        }
        return Result.error(CodeMsg.DATA_UPDATE_ERROR);
    }

    @GetMapping("/queryLogistics/{shippingOrderId}")
    @ApiOperation("物流查询")
    public Result queryLogistics(@PathVariable Long shippingOrderId) {

        LogisticsVO result = shippingOrderService.queryLogistics(shippingOrderId);

        return Result.success(result);
    }
}
