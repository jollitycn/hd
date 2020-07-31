package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.insigma.ordercenter.entity.OrderDetail;
import com.insigma.ordercenter.entity.OrderSendReceive;
import com.insigma.ordercenter.entity.SendReceiveInfo;
import com.insigma.ordercenter.entity.vo.SendReceiveInfoVO;
import com.insigma.ordercenter.entity.vo.UpdateSendReceiveInfoVO;
import com.insigma.ordercenter.mapper.OrderSendReceiveMapper;
import com.insigma.ordercenter.mapper.SendReceiveInfoMapper;
import com.insigma.ordercenter.service.IOrderDetailService;
import com.insigma.ordercenter.service.IOrderSendReceiveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.service.ISendReceiveInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 收发人信息 服务实现类
 * </p>
 *
 * @author AH
 * @since 2020-07-27
 */
@Service
public class OrderSendReceiveServiceImpl extends ServiceImpl<OrderSendReceiveMapper, OrderSendReceive> implements IOrderSendReceiveService {


    @Resource
    private OrderSendReceiveMapper orderSendReceiveMapper;

    @Resource
    private IOrderDetailService orderDetailService;

    @Resource
    private ISendReceiveInfoService sendReceiveInfoService;

    @Override
    public SendReceiveInfoVO getSendReceiveInfo(Long orderId) {
        SendReceiveInfoVO sendReceiveInfo = orderSendReceiveMapper.getSendReceiveInfo(orderId);
        QueryWrapper<OrderDetail> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("order_id",orderId);
        List<OrderDetail> list = orderDetailService.list(queryWrapper);
        sendReceiveInfo.setOrderDetails(list);
        return sendReceiveInfo;
    }


    @Override
    public Boolean updateSendReceiveInfo(UpdateSendReceiveInfoVO updateSendReceiveInfoVO) {
        SendReceiveInfo sendReceiveInfo=new SendReceiveInfo();
        BeanUtils.copyProperties(updateSendReceiveInfoVO,sendReceiveInfo);
        boolean b = sendReceiveInfoService.updateById(sendReceiveInfo);
        return b;
    }

}
