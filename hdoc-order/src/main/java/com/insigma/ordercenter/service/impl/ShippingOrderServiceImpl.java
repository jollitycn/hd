package com.insigma.ordercenter.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.constant.Constant;
import com.insigma.ordercenter.entity.OrderDetail;
import com.insigma.ordercenter.entity.Refund;
import com.insigma.ordercenter.entity.ShippingOrder;
import com.insigma.ordercenter.entity.ShippingOrderOperation;
import com.insigma.ordercenter.entity.dto.EditShippingOrderDTO;
import com.insigma.ordercenter.entity.dto.EditShippingOrderProductDTO;
import com.insigma.ordercenter.entity.dto.ShippingOrderDTO;
import com.insigma.ordercenter.entity.vo.EditOrderProductDTO;
import com.insigma.ordercenter.entity.vo.ShippingOrderDetailVO;
import com.insigma.ordercenter.entity.vo.ShippingOrderVO;
import com.insigma.ordercenter.mapper.*;
import com.insigma.ordercenter.service.IShippingOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 发货单表 服务实现类
 * </p>
 *
 * @author Xuchao
 * @since 2020-07-08
 */
@Service
public class ShippingOrderServiceImpl extends ServiceImpl<ShippingOrderMapper, ShippingOrder> implements IShippingOrderService {

    @Resource
    private ShippingOrderOperationMapper shippingOrderOperationMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private RefundMapper refundMapper;

    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Override
    public IPage<ShippingOrderVO> getShippingOrderList(ShippingOrderDTO shippingOrderDTO) {

        //处理分页参数
        Page<ShippingOrderVO> page = new Page<>(shippingOrderDTO.getPageNum(), shippingOrderDTO.getPageSize());

        //查询
        IPage<ShippingOrderVO> result=baseMapper.getShippingOrderList(page,shippingOrderDTO);

        return result;

    }

    @Override
    public Boolean increaseCargo(EditShippingOrderDTO editShippingOrderDTO) {

        //写入发货单对象
        ShippingOrder shippingOrder = new ShippingOrder();

        shippingOrder.setAddress(editShippingOrderDTO.getAddress());
        shippingOrder.setReceiveName(editShippingOrderDTO.getReceiveName());
        shippingOrder.setReceiveRemark(editShippingOrderDTO.getReceiveRemark());
        shippingOrder.setMobilePhone(editShippingOrderDTO.getMobilePhone());
        shippingOrder.setWarehouseId(editShippingOrderDTO.getWarehouseId());
        shippingOrder.setExpressCompanyId(editShippingOrderDTO.getExpressCompanyId());
        //生成发货单id ObjectId是MongoDB数据库的一种唯一ID生成策略，是UUID version1的变种
        shippingOrder.setShippingOrderNo("FH"+IdUtil.objectId());
        shippingOrder.setCreateTime(LocalDateTime.now());

        this.createLog(null,1L,1L,"张三新增了补货单:"+shippingOrder.getShippingOrderNo());


        return this.save(shippingOrder);
    }

    /**
     * 更改发货单地址
     *
     * @param shippingOrderId
     * @param editShippingOrderDTO
     * @return
     */
    @Override
    public Boolean changeAddress(Long shippingOrderId, EditShippingOrderDTO editShippingOrderDTO) {

        //获取发货单对象
        ShippingOrder shippingOrder=this.getById(shippingOrderId);

        //状态判断
        //TODO
        Integer status=shippingOrder.getStatus();

        //更新收货信息
        shippingOrder.setAddress(editShippingOrderDTO.getAddress());
        shippingOrder.setMobilePhone(editShippingOrderDTO.getMobilePhone());
        shippingOrder.setReceiveName(editShippingOrderDTO.getReceiveName());

        this.createLog(shippingOrderId,1L,1L,"张三修改了发货单:"+shippingOrder.getShippingOrderNo()+"的收货人信息。更改原因："+editShippingOrderDTO.getChangeReason());

        return this.updateById(shippingOrder);
    }

    /**
     * 更改发货单商品
     *
     * @param shippingOrderId
     * @param editParameters
     * @return
     */
    @Override
    public Boolean changeProduct(Long shippingOrderId, EditShippingOrderProductDTO editParameters) {

        //获取发货单对象
        ShippingOrder shippingOrder=this.getById(shippingOrderId);

        //状态判断
        Integer status=shippingOrder.getStatus();

        for (EditOrderProductDTO editOrderProductDTO:editParameters.getEditOrderProductDTOList()) {
            OrderDetail orderDetail=orderDetailMapper.selectById(editOrderProductDTO.getOrderDetailId());
            orderDetail.setAmount(editOrderProductDTO.getAmount());
            orderDetail.setProductSpecs(editOrderProductDTO.getProductSpecs());
            orderDetailMapper.updateById(orderDetail);
        }

        this.createLog(shippingOrderId,1L,1L,"张三修改了发货单:"+shippingOrder.getShippingOrderNo()+"的商品,原因:"+editParameters.getChangeReason());

        return true;
    }

    /**
     * 更改发货单仓库
     *
     * @param shippingOrderId
     * @param editShippingOrderDTO
     * @return
     */
    @Override
    public Boolean changeWarehouse(Long shippingOrderId, EditShippingOrderDTO editShippingOrderDTO) {

        //获取发货单对象
        ShippingOrder shippingOrder=this.getById(shippingOrderId);

        //状态判断
        //TODO
        Integer status=shippingOrder.getStatus();

        //修改仓库
        shippingOrder.setWarehouseId(editShippingOrderDTO.getWarehouseId());

        this.createLog(shippingOrderId,1L,1L,"张三修改了发货单:"+shippingOrder.getShippingOrderNo()+"的仓库");

        return this.updateById(shippingOrder);
    }

    /**
     * 取消发货单
     *
     * @param shippingOrderId
     * @return
     */
    @Override
    public Boolean cancel(Long shippingOrderId) {

        //获取发货单对象
        ShippingOrder shippingOrder=this.getById(shippingOrderId);

        //修改状态为取消
        shippingOrder.setStatus(Constant.SYS_FOUR);

        this.createLog(shippingOrderId,1L,1L,"张三取消了发货单:"+shippingOrder.getShippingOrderNo());

        return this.updateById(shippingOrder);
    }

    /**
     * 冻结发货单
     *
     * @param shippingOrderId
     * @return
     */
    @Override
    public Boolean frozen(Long shippingOrderId) {

        //获取发货单对象
        ShippingOrder shippingOrder=this.getById(shippingOrderId);

        //修改状态为冻结
        shippingOrder.setStatus(Constant.SYS_THREE);

        this.createLog(shippingOrderId,1L,1L,"张三冻结了发货单:"+shippingOrder.getShippingOrderNo());

        return this.updateById(shippingOrder);
    }

    /**
     * 获取发货单明细
     *
     * @param shippingOrderId
     * @return
     */
    @Override
    public ShippingOrderDetailVO getShippingDetail(Long shippingOrderId) {

        ShippingOrderDetailVO result=baseMapper.getShippingDetail(shippingOrderId);

        //查询发货单商品信息
        result.setProductList(productMapper.getShippingOrderProductList(result.getOrderId()));

        return result;
    }

    /**
     * 发货单拒收
     *
     * @param shippingOrderId
     * @param sourceType
     * @param reason
     * @return
     */
    @Override
    public Boolean rejection(Long shippingOrderId, Integer sourceType, String reason) {

        //更新发货单状态
        ShippingOrder shippingOrder=this.getById(shippingOrderId);
        shippingOrder.setStatus(Constant.SYS_FIVE);

        //新建退货单
        Refund refund=new Refund();
        refund.setCreateTime(LocalDateTime.now());
        refund.setOrderId(shippingOrder.getShippingOrderId());
        refund.setRefundNo("TH"+IdUtil.objectId());
        refund.setSourceType(sourceType);
        refund.setStatus(Constant.SYS_ZERO);
        refund.setReason(reason);

        refundMapper.insert(refund);
        return  this.updateById(shippingOrder);
    }


/**
     * 建立发货单操作日志
     * @param shippingOrderId
     * @param orderId
     * @param createId
     * @param content
     */
    private void createLog(Long shippingOrderId,Long orderId,Long createId,String content){

        ShippingOrderOperation shippingOrderOperation=new ShippingOrderOperation();

        shippingOrderOperation.setContent(content);
        shippingOrderOperation.setShippingOrderId(shippingOrderId);
        shippingOrderOperation.setOrderId(orderId);
        shippingOrderOperation.setCreateId(createId);
        shippingOrderOperation.setCreateTime(LocalDateTime.now());

        shippingOrderOperationMapper.insert(shippingOrderOperation);

    }


}
