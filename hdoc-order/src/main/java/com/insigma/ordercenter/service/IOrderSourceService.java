package com.insigma.ordercenter.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.entity.OrderSource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.dto.OrderSourceDTO;
import com.insigma.ordercenter.entity.vo.OrderSourceListVO;

/**
 * <p>
 * 订单来源定义表 服务类
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-16
 */
public interface IOrderSourceService extends IService<OrderSource> {

    IPage<OrderSourceListVO> getOrderSourceList(Page<OrderSourceListVO> page, OrderSourceDTO orderSourceDTO);
}
