package com.insigma.ordercenter.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.dto.AddRefundDTO;
import com.insigma.ordercenter.entity.dto.RefundDTO;
import com.insigma.ordercenter.entity.vo.RefundDetailVO;
import com.insigma.ordercenter.entity.vo.RefundVO;
import com.insigma.ordercenter.service.IRefundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 退换货记录表 前端控制器
 * </p>
 *
 * @author Xuchao
 * @since 2020-07-30
 */
@RestController
@RequestMapping("/refund")
@Api(tags = {"退货单模块-xuchao"})
public class RefundController extends BaseController {

    @Autowired
    private IRefundService refundService;


    @GetMapping("/list")
    @ApiOperation(value = "退货单列表", response = RefundVO.class)
    public Result list(RefundDTO refundDTO) {

        IPage<RefundVO> result = refundService.getRefundList(refundDTO);

        return Result.success(result);
    }

    @GetMapping("/detail/{refundId}")
    @ApiOperation(value = "退货单详情", response = RefundVO.class)
    public Result detail(@PathVariable Long refundId) {

        RefundDetailVO result = refundService.getRefundDetail(refundId);

        return Result.success(result);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增退货单")
    public Result add(AddRefundDTO addRefundDTO) {

        boolean result = refundService.addRefund(addRefundDTO);

        if (result) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_UPDATE_ERROR);
        }
    }

    @PutMapping("/warehousing/{refundId}")
    @ApiOperation(value = "退货单入库")
    public Result warehousing(@PathVariable Long refundId) {

        boolean result = refundService.warehousing(refundId);

        if (result) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.DATA_UPDATE_ERROR);
        }
    }



}
