package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.entity.OrderSource;
import com.insigma.ordercenter.entity.dto.OrderSourceDTO;
import com.insigma.ordercenter.entity.vo.OrderSourceListVO;
import com.insigma.ordercenter.mapper.OrderSourceMapper;
import com.insigma.ordercenter.service.IOrderSourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.utils.StringUtil;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单来源定义表 服务实现类
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-16
 */
@Service
public class OrderSourceServiceImpl extends ServiceImpl<OrderSourceMapper, OrderSource> implements IOrderSourceService {

    @Override
    public IPage<OrderSourceListVO> getOrderSourceList(Page<OrderSourceListVO> page, OrderSourceDTO orderSourceDTO) {

        String sourceName = null;
        if (orderSourceDTO.getSourceName() != null) {
            sourceName = StringUtil.addPercent(orderSourceDTO.getSourceName());
        }
        String sourceNo = null;
        if (orderSourceDTO.getSourceNo() != null) {
            sourceNo = StringUtil.addPercent(orderSourceDTO.getSourceNo());
        }

        return baseMapper.getOrderSourceList(page, sourceName, sourceNo);
    }
}
