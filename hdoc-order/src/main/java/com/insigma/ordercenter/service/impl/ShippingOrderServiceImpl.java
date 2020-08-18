package com.insigma.ordercenter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.constant.Constant;
import com.insigma.ordercenter.entity.*;
import com.insigma.ordercenter.entity.dto.*;
import com.insigma.ordercenter.entity.vo.*;
import com.insigma.ordercenter.logistics.LogisticsCentre;
import com.insigma.ordercenter.mapper.*;
import com.insigma.ordercenter.service.IShippingOrderService;
import com.insigma.ordercenter.utils.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Resource
    private ShopMapper shopMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderSendReceiveMapper orderSendReceiveMapper;

    @Override
    public IPage<ShippingOrderVO> getShippingOrderList(ShippingOrderDTO shippingOrderDTO) {

        //处理分页参数
        Page<ShippingOrderVO> page = new Page<>(shippingOrderDTO.getPageNum(), shippingOrderDTO.getPageSize());

        //查询
        IPage<ShippingOrderVO> result = baseMapper.getShippingOrderList(page, shippingOrderDTO);

        return result;

    }

    @Override
    public Boolean increaseCargo(LoginUser loginUser, EditShippingOrderDTO editShippingOrderDTO) {

        Long shippingOrderId = editShippingOrderDTO.getShippingOrderIds().get(0);
        //写入发货单对象
        ShippingOrder shippingOrder = new ShippingOrder();
        if (null != shippingOrderId) {
            shippingOrder = getById(shippingOrderId);
            this.createLog(shippingOrderId, shippingOrder.getOrderId(), loginUser.getUserId(), loginUser.getUserName() + "编辑了补货单:" + shippingOrder.getShippingOrderNo());

            //编辑时候删除补货商品
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq(OrderDetail.ORDER_ID, shippingOrder.getOrderId());
            queryWrapper.eq(OrderDetail.IS_SUPPLEMENT, Constant.SYS_ONE);
            orderDetailMapper.delete(queryWrapper);
        } else {
            //生成发货单id
            shippingOrder.setShippingOrderNo(UUIDUtil.getSerializeNo("FH"));
            shippingOrder.setCreateTime(LocalDateTime.now());
            this.createLog(null, shippingOrder.getOrderId(), loginUser.getUserId(), loginUser.getUserName() + "新增了补货单:" + shippingOrder.getShippingOrderNo());
        }
        BeanUtil.copyProperties(editShippingOrderDTO, shippingOrder);

        //设置为新建状态
        shippingOrder.setStatus(Constant.SYS_EIGHT);

        //增加补货商品
        List<ShippingProductAddDTO> productList = editShippingOrderDTO.getProductList();
        for (ShippingProductAddDTO shippingProduct : productList) {
            Long productId = shippingProduct.getProductId();
            Product product = productMapper.selectById(productId);
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(shippingOrder.getOrderId());
            orderDetail.setProductId(productId);
            BeanUtil.copyProperties(product, orderDetail);
            Integer amount = shippingProduct.getAmount();
            orderDetail.setAmount(amount);
            //小计为 数量乘以单价
            orderDetail.setTotalPrice(product.getProductPrice().multiply(new BigDecimal(amount)));
            orderDetail.setIsSupplement(Constant.SYS_ONE);
            orderDetailMapper.insert(orderDetail);
        }

        return shippingOrderId == null ? save(shippingOrder) : updateById(shippingOrder);
    }

    /**
     * 更改发货单地址
     *
     * @param editShippingOrderDTO
     * @return
     */
    @Override
    public Boolean changeAddress(LoginUser loginUser, EditShippingOrderDTO editShippingOrderDTO) {

        List<Long> shippingOrderIds = editShippingOrderDTO.getShippingOrderIds();

        //批量更新
        List<ShippingOrder> updateList = new ArrayList<>();
        for (Long shippingOrderId : shippingOrderIds) {

            //获取发货单对象
            ShippingOrder shippingOrder = this.getById(shippingOrderId);

            //状态判断
            //TODO
            Integer status = shippingOrder.getStatus();

            Long orderId = shippingOrder.getOrderId();
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq(OrderSendReceive.ORDER_ID, orderId);
            OrderSendReceive orderSendReceive = orderSendReceiveMapper.selectOne(queryWrapper);
            orderSendReceive.setLocationCity(editShippingOrderDTO.getLocationCity());
            orderSendReceive.setProvince(editShippingOrderDTO.getProvince());
            orderSendReceive.setAddress(editShippingOrderDTO.getAddress());
            orderSendReceiveMapper.updateById(orderSendReceive);

            //更新收货信息
            shippingOrder.setAddress(editShippingOrderDTO.getAddress());
            shippingOrder.setMobilePhone(editShippingOrderDTO.getMobilePhone());
            shippingOrder.setReceiveName(editShippingOrderDTO.getReceiveName());

            updateList.add(shippingOrder);

            this.createLog(shippingOrderId, shippingOrder.getOrderId(), loginUser.getUserId(), loginUser.getUserName() + "修改了发货单:" + shippingOrder.getShippingOrderNo() + "的收货人信息。更改原因：" + editShippingOrderDTO.getChangeReason());
        }

        return updateBatchById(updateList);

    }

    /**
     * 更改发货单商品
     *
     * @param editParameters
     * @return
     */
    @Override
    public Boolean changeProduct(LoginUser loginUser, EditShippingOrderProductDTO editParameters) {

        List<Long> shippingOrderIds = editParameters.getShippingOrderId();

        //批量更新
        List<ShippingOrder> updateList = new ArrayList<>();
        for (Long shippingOrderId : shippingOrderIds) {

            //获取发货单对象
            ShippingOrder shippingOrder = this.getById(shippingOrderId);

            //状态判断
            Integer status = shippingOrder.getStatus();

            //更改商品
            for (EditOrderProductDTO editOrderProductDTO : editParameters.getEditOrderProductDTOList()) {
                OrderDetail orderDetail = orderDetailMapper.selectById(editOrderProductDTO.getOrderDetailId());
                orderDetail.setAmount(editOrderProductDTO.getAmount());
                orderDetail.setProductSpecs(editOrderProductDTO.getProductSpecs());
                orderDetailMapper.updateById(orderDetail);
            }

            this.createLog(shippingOrderId, shippingOrder.getOrderId(), loginUser.getUserId(), loginUser.getUserName() + "修改了发货单:" + shippingOrder.getShippingOrderNo() + "的商品,原因:" + editParameters.getChangeReason());
        }


        return true;
    }

    /**
     * 更改发货单仓库
     *
     * @param editShippingOrderDTO
     * @return
     */
    @Override
    public Boolean changeWarehouse(LoginUser loginUser, EditShippingOrderDTO editShippingOrderDTO) {

        List<Long> shippingOrderIds = editShippingOrderDTO.getShippingOrderIds();

        //批量更新
        List<ShippingOrder> updateList = new ArrayList<>();
        for (Long shippingOrderId : shippingOrderIds) {

            //获取发货单对象
            ShippingOrder shippingOrder = this.getById(shippingOrderId);

            //状态判断
            //TODO
            Integer status = shippingOrder.getStatus();

            //修改仓库
            shippingOrder.setWarehouseId(editShippingOrderDTO.getWarehouseId());

            this.createLog(shippingOrderId, shippingOrder.getOrderId(), loginUser.getUserId(), loginUser.getUserName() + "修改了发货单:" + shippingOrder.getShippingOrderNo() + "的仓库");

            updateList.add(shippingOrder);
        }


        return updateBatchById(updateList);
    }

    /**
     * 取消发货单
     *
     * @param shippingOrderId
     * @return
     */
    @Override
    public Boolean cancel(LoginUser loginUser, Long shippingOrderId) {

        //获取发货单对象
        ShippingOrder shippingOrder = this.getById(shippingOrderId);

        //修改状态为取消
        shippingOrder.setStatus(Constant.SYS_FOUR);

        this.createLog(shippingOrderId, shippingOrder.getOrderId(), loginUser.getUserId(), loginUser.getUserName() + "取消了发货单:" + shippingOrder.getShippingOrderNo());

        return this.updateById(shippingOrder);
    }

    /**
     * 冻结发货单
     *
     * @param shippingOrderId
     * @return
     */
    @Override
    public Boolean frozen(LoginUser loginUser, Long shippingOrderId) {

        //获取发货单对象
        ShippingOrder shippingOrder = this.getById(shippingOrderId);

        //修改状态为冻结
        shippingOrder.setStatus(Constant.SYS_THREE);

        this.createLog(shippingOrderId, shippingOrder.getOrderId(), loginUser.getUserId(), loginUser.getUserName() + "冻结了发货单:" + shippingOrder.getShippingOrderNo());

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

        ShippingOrderDetailVO result = baseMapper.getShippingDetail(shippingOrderId);

        //查询发货单商品信息
        result.setProductList(productMapper.getShippingOrderProductList(shippingOrderId));

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
        ShippingOrder shippingOrder = this.getById(shippingOrderId);
        shippingOrder.setStatus(Constant.SYS_FIVE);

        //新建退货单
        Refund refund = new Refund();
        refund.setCreateTime(LocalDateTime.now());
        refund.setOrderId(shippingOrder.getShippingOrderId());
        refund.setRefundNo("TH" + IdUtil.objectId());
        refund.setSourceType(sourceType);
        refund.setStatus(Constant.SYS_ZERO);
        refund.setReason(reason);

        refundMapper.insert(refund);
        return this.updateById(shippingOrder);
    }

    /**
     * 物流查询
     *
     * @param shippingOrderId
     * @return
     */
    @Override
    public Result queryLogistics(Long shippingOrderId) {

        //LogisticsVO

        ShippingOrder shippingOrder = getById(shippingOrderId);
        String expressNo = shippingOrder.getExpressNo();
        //TODO 获取物流类型
        int logisticsType = 1;
        Result result = LogisticsCentre.queryLogistics(expressNo, logisticsType);

        return result;
    }


    @Override
    public List<Long> getShippingOrderByProductType(Long orderDetailId) {
        return this.baseMapper.getShippingOrderIdByProductType(orderDetailId);
    }

    @Override
    public boolean createLogisticsJob() throws Exception {

        //查询符合条件的发货单
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("status", Constant.SYS_ZERO);
        queryWrapper.eq("is_deleted", Constant.SYS_ZERO);
        List<ShippingOrder> shippingOrderList = baseMapper.selectList(queryWrapper);

        if (CollUtil.isNotEmpty(shippingOrderList)) {

            //循环处理
            for (ShippingOrder shippingOrder : shippingOrderList) {

                //查询商品
                List<ProductDetailVO> productDetailVOList = productMapper.getProductListByshippingOrderId(shippingOrder.getShippingOrderId());
                CommonProductDTO commonProduct = new CommonProductDTO();
                commonProduct.setProductList(productDetailVOList);

                //查询收件人
                SendReceiveInfoVO orderSendReceive = orderSendReceiveMapper.getSendReceiveInfo(shippingOrder.getOrderId());

                //设置收件人
                CommonConsigneeDTO commonConsignee = new CommonConsigneeDTO();
                commonConsignee.setReceiveName(orderSendReceive.getReceiveName());
                commonConsignee.setMobilePhone(orderSendReceive.getMobilePhone());
                commonConsignee.setAddress(orderSendReceive.getAddress());

                //查询发件人
                CommonConsignorDTO commonConsignor = new CommonConsignorDTO();
                Order order = orderMapper.selectById(shippingOrder.getOrderId());
                Shop shop = shopMapper.selectById(order.getShopId());
                commonConsignor.setAddress(shop.getSAddr());
                commonConsignor.setMobilePhone(shop.getCPhone());
                commonConsignor.setReceiveName(shop.getCName());

                //获取物流类型
                int logisticsType = shippingOrder.getExpressCompanyId();

                //调度对应物流下单
                Result result = LogisticsCentre.generateLogistics(shippingOrder.getShippingOrderNo(), commonProduct, commonConsignee, commonConsignor, logisticsType);

                if (Constant.SYS_ZERO == result.getRetCode()) {
                    //将发货单置为已发货状态
                    shippingOrder.setStatus(Constant.SYS_TWO);

                    //写入发货单号
                    shippingOrder.setExpressNo(result.getData().toString());
                } else {
                    //将发货单置为异常状态
                    shippingOrder.setStatus(Constant.SYS_SIX);

                    //写入异常原因
                    shippingOrder.setExceptionReason(result.getMessage());
                    log.error("发货单下单失败");
                }

            }

            //更新发货单数据
            return saveOrUpdateBatch(shippingOrderList);
        }
        return false;
    }

    /**
     * 保存补货单
     *
     * @param loginUser
     * @param shippingOrderId
     * @return
     */
    @Override
    public Boolean saveIncreaseCargo(LoginUser loginUser, Long shippingOrderId) {
        //获取发货单对象
        ShippingOrder shippingOrder = this.getById(shippingOrderId);

        //修改状态为待出库
        shippingOrder.setStatus(Constant.SYS_ZERO);

        this.createLog(shippingOrderId, shippingOrder.getOrderId(), loginUser.getUserId(), loginUser.getUserName() + "保存了发货单:" + shippingOrder.getShippingOrderNo());

        return this.updateById(shippingOrder);
    }


    /**
     * 建立发货单操作日志
     *
     * @param shippingOrderId
     * @param orderId
     * @param createId
     * @param content
     */
    private void createLog(Long shippingOrderId, Long orderId, Long createId, String content) {

        ShippingOrderOperation shippingOrderOperation = new ShippingOrderOperation();

        shippingOrderOperation.setContent(content);
        shippingOrderOperation.setShippingOrderId(shippingOrderId);
        shippingOrderOperation.setOrderId(orderId);
        shippingOrderOperation.setCreateId(createId);
        shippingOrderOperation.setCreateTime(LocalDateTime.now());

        shippingOrderOperationMapper.insert(shippingOrderOperation);

    }


}
