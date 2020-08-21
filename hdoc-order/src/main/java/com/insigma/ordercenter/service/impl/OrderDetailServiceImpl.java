package com.insigma.ordercenter.service.impl;

import com.insigma.ordercenter.entity.LoginUser;
import com.insigma.ordercenter.entity.OrderDetail;
import com.insigma.ordercenter.entity.SysOperationLog;
import com.insigma.ordercenter.entity.vo.AddOrderDetailVO;
import com.insigma.ordercenter.entity.vo.OrderDetailExamineVO;
import com.insigma.ordercenter.entity.vo.OrderDetailVO;
import com.insigma.ordercenter.mapper.OrderDetailMapper;
import com.insigma.ordercenter.service.IOrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.service.ISysOperationLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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

    @Resource
    private ISysOperationLogService sysOperationLogService;

    @Override
    public List<OrderDetailVO> getOrderDetail(Long orderId) {
        return orderDetailMapper.getOrderDetail(orderId);
    }

    @Override
    public List<OrderDetailVO> orderDerailList(Long orderId) {
        return orderDetailMapper.orderDerailList(orderId);
    }

    @Override
    public Boolean addOrderDerail(AddOrderDetailVO addOrderDetailVO, LoginUser loginUser) {
        //先删除
        if(null != addOrderDetailVO.getOrderDetails() && addOrderDetailVO.getOrderDetails().size()>0){
            addOrderDetailVO.getOrderDetails().forEach(orderDetail -> {
                orderDetailService.removeById(orderDetail.getOrderDetailId());
            });
        }
        //再保存
        if (null!=addOrderDetailVO.getOrderDetails() && addOrderDetailVO.getOrderDetails().size()>0){
            addOrderDetailVO.getOrderDetails().forEach(orderDetail -> {
                SysOperationLog sysOperationLog=new SysOperationLog();
                sysOperationLog.setContent("新增赠品");
                sysOperationLog.setCreateTime(LocalDateTime.now());
                sysOperationLog.setOrderId(orderDetail.getOrderId());
                sysOperationLog.setCreateId(loginUser.getUserId());
                sysOperationLogService.save(sysOperationLog);
                orderDetailService.save(orderDetail);
            });
            return true;
        }
        return false;
    }


}
