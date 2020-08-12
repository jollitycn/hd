package com.insigma.ordercenter.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.entity.OrderSource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.vo.OrderSourceListVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 订单来源定义表 Mapper 接口
 * </p>
 *
 * @author Jason
 * @since 2020-07-16
 */
public interface OrderSourceMapper extends BaseMapper<OrderSource> {

    IPage<OrderSourceListVO> getOrderSourceList(Page<OrderSourceListVO> page,
                                               @Param("orderSource") OrderSource orderSource);

    OrderSource checkDuplById (  @Param("orderSource") OrderSource orderSource );

    OrderSource checkDupl (  @Param("orderSource") OrderSource orderSource );
}
