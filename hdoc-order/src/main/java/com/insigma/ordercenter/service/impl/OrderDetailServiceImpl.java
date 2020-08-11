package com.insigma.ordercenter.service.impl;

import com.insigma.ordercenter.entity.OrderDetail;
import com.insigma.ordercenter.entity.vo.AddOrderDetailVO;
import com.insigma.ordercenter.entity.vo.OrderDetailExamineVO;
import com.insigma.ordercenter.mapper.OrderDetailMapper;
import com.insigma.ordercenter.service.IOrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 订单明细 服务实现类
 * </p>
 *
 * @author AH
 * @since 2020-07-22
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {

    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Resource
    private IOrderDetailService orderDetailService;

    @Override
    public List<OrderDetail> getOrderDetail(Long orderId) {
        return orderDetailMapper.getOrderDetail(orderId);
    }

    @Override
    public Boolean addOrderDerail(AddOrderDetailVO addOrderDetailVO) {
        //先删除
        if(null != addOrderDetailVO.getOrderDetails() && addOrderDetailVO.getOrderDetails().size()>0){
            addOrderDetailVO.getOrderDetails().forEach(orderDetail -> {
                orderDetailService.removeById(orderDetail.getOrderDetailId());
            });
        }
        //再保存
        if (null!=addOrderDetailVO.getOrderDetails() && addOrderDetailVO.getOrderDetails().size()>0){
            orderDetailService.saveBatch(addOrderDetailVO.getOrderDetails());
            return true;
        }
       return false;
    }


}
