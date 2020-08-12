package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.constant.Constant;
import com.insigma.ordercenter.entity.LoginUser;
import com.insigma.ordercenter.entity.OrderSource;
import com.insigma.ordercenter.entity.dto.OrderSourceAddDTO;
import com.insigma.ordercenter.entity.dto.OrderSourceDTO;
import com.insigma.ordercenter.entity.dto.OrderSourceEditDTO;
import com.insigma.ordercenter.entity.vo.OrderSourceListVO;
import com.insigma.ordercenter.mapper.OrderSourceMapper;
import com.insigma.ordercenter.service.IOrderSourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 订单来源定义表 服务实现类
 * </p>
 *
 * @author Jason
 * @since 2020-07-16
 */
@Service
public class OrderSourceServiceImpl extends ServiceImpl<OrderSourceMapper, OrderSource> implements IOrderSourceService {

    @Override
    public IPage<OrderSourceListVO> getOrderSourceList(Page<OrderSourceListVO> page, OrderSourceDTO orderSourceDTO) {

        OrderSource orderSource = new OrderSource();
        String sourceName = null;
        if (orderSourceDTO.getSourceName() != null) {
            sourceName = StringUtil.addPercent(orderSourceDTO.getSourceName());
            orderSource.setSourceName(sourceName);
        }
        String sourceNo = null;
        if (orderSourceDTO.getSourceNo() != null) {
            sourceNo = StringUtil.addPercent(orderSourceDTO.getSourceNo());
            orderSource.setSourceNo(sourceNo);
        }
        if (null != orderSourceDTO.getIsStop()) {
            orderSource.setIsStop(orderSourceDTO.getIsStop());
        }

        return baseMapper.getOrderSourceList(page,orderSource);
    }

    @Override
    public boolean add(OrderSourceAddDTO orderSourceAddDTO, LoginUser loginUser) throws MyException {

        OrderSource orderSource = new OrderSource();
        BeanUtils.copyProperties(orderSourceAddDTO, orderSource);
        orderSource.setCreateId(loginUser.getUserId());
        orderSource.setCreateTime(LocalDateTime.now());

        if(checkDupl(orderSource)){throw new MyException(CodeMsg.ORDER_SOURCE_DUPL);}
        return save(orderSource);
    }

    private boolean checkDupl ( OrderSource orderSource ) {
        OrderSource  os = null;
        if(orderSource.getOrderSourceId()!=null){
            os =  baseMapper.checkDuplById(orderSource);
        }else{
            os =  baseMapper.checkDupl(orderSource);
        }
        return os !=null;
    }

    @Override
    public boolean edit(OrderSourceEditDTO orderSourceEditDTO) throws MyException {
        OrderSource orderSource = getById(orderSourceEditDTO.getOrderSourceId());

        if (orderSource != null) {
            if(checkDupl(orderSource)){throw new MyException(CodeMsg.ORDER_SOURCE_DUPL);}
            orderSource.setSourceName(orderSourceEditDTO.getSourceName());
            orderSource.setSourceNo(orderSourceEditDTO.getSourceNo());
            orderSource.setRemark(orderSourceEditDTO.getRemark());
            return updateById(orderSource);
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Long orderSourceId) {

        OrderSource orderSource = getById(orderSourceId);

        if (orderSource != null) {
            orderSource.setIsDeleted(Constant.SYS_ONE);
            return updateById(orderSource);
        } else {
            return false;
        }
    }

    @Override
    public boolean unblock(Long orderSourceId) {

        OrderSource orderSource = getById(orderSourceId);

        if (orderSource != null) {
            orderSource.setIsStop(Constant.SYS_ZERO);
            return updateById(orderSource);
        } else {
            return false;
        }
    }

    @Override
    public boolean block(Long orderSourceId) {

        OrderSource orderSource = getById(orderSourceId);

        if (orderSource != null) {
            orderSource.setIsStop(Constant.SYS_ONE);
            return updateById(orderSource);
        } else {
            return false;
        }
    }
}
