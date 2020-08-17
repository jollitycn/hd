package com.insigma.ordercenter.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.LoginUser;
import com.insigma.ordercenter.entity.dto.EditShippingOrderDTO;
import com.insigma.ordercenter.entity.dto.EditShippingOrderProductDTO;
import com.insigma.ordercenter.entity.dto.ShippingOrderDTO;
import com.insigma.ordercenter.entity.vo.ShippingOrderDetailVO;
import com.insigma.ordercenter.entity.vo.ShippingOrderVO;
import com.insigma.ordercenter.logistics.LogisticsCentre;
import com.insigma.ordercenter.service.IShippingOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
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
    @ApiOperation("新建补货单")
    public Result increaseCargo(@RequestBody EditShippingOrderDTO editShippingOrderDTO) {

        LoginUser loginUser=redisUser();

        Boolean result = shippingOrderService.increaseCargo(loginUser,editShippingOrderDTO);

        if(result){
            return Result.success();
        }

        return Result.error(CodeMsg.DATA_INSERT_ERROR);
    }

    @PutMapping("/saveIncreaseCargo/{shippingOrderId}")
    @ApiOperation("保存补货单")
    public Result saveIncreaseCargo(@PathVariable Long shippingOrderId) {

        LoginUser loginUser=redisUser();

        Boolean result = shippingOrderService.saveIncreaseCargo(loginUser,shippingOrderId);
        if(result){
            return Result.success();
        }

        return Result.error(CodeMsg.DATA_UPDATE_ERROR);
    }

    @PutMapping("/changeAddress")
    @ApiOperation(value ="更改地址")
    public Result changeAddress(@RequestBody EditShippingOrderDTO editShippingOrderDTO) {

        LoginUser loginUser=redisUser();

        Boolean result = shippingOrderService.changeAddress(loginUser,editShippingOrderDTO);

        if(result){
            return Result.success();
        }

        return Result.error(CodeMsg.DATA_UPDATE_ERROR);
    }

    @PutMapping("/changeProduct")
    @ApiOperation(value ="更改商品")
    public Result changeProduct(@RequestBody EditShippingOrderProductDTO editParameters) {

        LoginUser loginUser=redisUser();

        Boolean result = shippingOrderService.changeProduct(loginUser,editParameters);

        if(result){
            return Result.success();
        }

        return Result.error(CodeMsg.DATA_UPDATE_ERROR);
    }

    @PutMapping("/changeWarehouse")
    @ApiOperation(value ="更改仓库")
    public Result changeWarehouse(@RequestBody EditShippingOrderDTO editShippingOrderDTO) {

        LoginUser loginUser=redisUser();

        Boolean result = shippingOrderService.changeWarehouse(loginUser,editShippingOrderDTO);

        if(result){
            return Result.success();
        }

        return Result.error(CodeMsg.DATA_UPDATE_ERROR);
    }

    @PutMapping("/cancel/{shippingOrderId}")
    @ApiOperation(value ="取消发货单")
    public Result cancel(@PathVariable Long shippingOrderId) {

        LoginUser loginUser=redisUser();

        Boolean result = shippingOrderService.cancel(loginUser,shippingOrderId);

        if(result){
            return Result.success();
        }

        return Result.error(CodeMsg.DATA_UPDATE_ERROR);

    }

    @PutMapping("/frozen/{shippingOrderId}")
    @ApiOperation("冻结发货单")
    public Result frozen(@PathVariable Long shippingOrderId) {

        LoginUser loginUser=redisUser();

        Boolean result = shippingOrderService.frozen(loginUser,shippingOrderId);
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

        Result result = shippingOrderService.queryLogistics(shippingOrderId);

        return result;
    }

    @GetMapping("/queryLogistics/{expressNo}/{logisticsType}")
    @ApiOperation("物流查询")
    public Result queryLogistics(@PathVariable("expressNo") String expressNo,
                       @PathVariable("logisticsType") int logisticsType) {

        Result result = LogisticsCentre.queryLogistics(expressNo,logisticsType);

        return Result.success(result);
    }


    /**
     * 创建物流快递单 定时任务调用
     * @return
     */
    @ApiOperation("物流下单-仅供测试使用")
    @GetMapping("/createLogisticsJob")
    public Result createLogisticsJob() throws Exception{

        log.info("发货单定时物流下单接口调度成功");

        boolean result=shippingOrderService.createLogisticsJob();

        return Result.success(result);
    }
}
