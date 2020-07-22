package com.insigma.ordercenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.entity.ShippingOrder;
import com.insigma.ordercenter.entity.dto.ShippingOrderDTO;
import com.insigma.ordercenter.entity.vo.ShippingOrderVO;

/**
 * <p>
 * 发货单表 Mapper 接口
 * </p>
 *
 * @author Xuchao
 * @since 2020-07-08
 */
public interface ShippingOrderMapper extends BaseMapper<ShippingOrder> {

    IPage<ShippingOrderVO> getShippingOrderList(Page<ShippingOrderVO> page,
                                                ShippingOrderDTO shippingOrderDTO);

}
