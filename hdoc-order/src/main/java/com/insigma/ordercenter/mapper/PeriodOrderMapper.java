package com.insigma.ordercenter.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.entity.PeriodOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.dto.PeriodOrderDTO;
import com.insigma.ordercenter.entity.vo.PeriodOrderOperationLogVO;
import com.insigma.ordercenter.entity.vo.PeriodOrderPayVO;
import com.insigma.ordercenter.entity.vo.PeriodOrderShippingVO;
import com.insigma.ordercenter.entity.vo.PeriodOrderVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 预约订单 Mapper 接口
 * </p>
 *
 * @author AH
 * @since 2020-07-28
 */
public interface PeriodOrderMapper extends BaseMapper<PeriodOrder> {

    IPage<PeriodOrderVO> queryPeriodOrderListPage(Page<PeriodOrderVO> page, @Param("periodOrderDTO")PeriodOrderDTO periodOrderDTO);

    List<PeriodOrderPayVO> getPeriodPayInfo(@Param("periodOrderId") Long periodOrderId);

    List<PeriodOrderOperationLogVO> getPeriodOrderOperationLog(@Param("periodOrderId") Long periodOrderId);

    List<PeriodOrderShippingVO> getPeriodShippingInfo(@Param("orderId") Long orderId);
}
