package com.insigma.ordercenter.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.entity.LoginUser;
import com.insigma.ordercenter.entity.OrderSource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.dto.OrderSourceAddDTO;
import com.insigma.ordercenter.entity.dto.OrderSourceDTO;
import com.insigma.ordercenter.entity.dto.OrderSourceEditDTO;
import com.insigma.ordercenter.entity.vo.OrderSourceListVO;
import com.insigma.ordercenter.service.impl.MyException;

/**
 * <p>
 * 订单来源定义表 服务类
 * </p>
 *
 * @author Jason
 * @since 2020-07-16
 */
public interface IOrderSourceService extends IService<OrderSource> {

    IPage<OrderSourceListVO> getOrderSourceList(Page<OrderSourceListVO> page, OrderSourceDTO orderSourceDTO);

    boolean add(OrderSourceAddDTO orderSourceAddDTO, LoginUser loginUser) throws MyException;

    boolean edit(OrderSourceEditDTO orderSourceEditDTO) throws MyException;

    boolean delete(Long orderSourceId);

    boolean block(Long orderSourceId);

    boolean unblock(Long orderSourceId);
}
