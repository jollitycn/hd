package com.insigma.ordercenter.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.entity.PeriodOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.PeriodOrderDetail;
import com.insigma.ordercenter.entity.PeriodOrderOperationLog;
import com.insigma.ordercenter.entity.PeriodSendReceiveInfo;
import com.insigma.ordercenter.entity.dto.OrderDTO;
import com.insigma.ordercenter.entity.dto.PeriodOrderDTO;
import com.insigma.ordercenter.entity.dto.PeriodOrderStatuDTO;
import com.insigma.ordercenter.entity.dto.PeriodStatuDTO;
import com.insigma.ordercenter.entity.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 预约订单 服务类
 * </p>
 *
 * @author AH
 * @since 2020-07-28
 */
public interface IPeriodOrderService extends IService<PeriodOrder> {

    IPage<PeriodOrderVO> queryPeriodOrderListPage(Page<PeriodOrderVO> page, PeriodOrderDTO periodOrderDTO);

    Boolean updatePeriodOrderStatu(PeriodOrderStatuDTO periodOrderStatuDTO);

    Boolean updatePeriodStatu(PeriodStatuDTO periodStatuDTO);

    List<PeriodOrderDetail> getPeriodOrderDetailList(Long periodOrderId);

    List<PeriodSendReceiveInfo> getPeriodSendReceiveInfo(Long periodOrderId);

    List<PeriodOrderOperationLogVO> getPeriodOrderOperationLog(Long periodOrderId);

    List<PeriodOrderPayVO> getPeriodPayInfo(Long periodOrderId);

    List<PeriodOrderShippingVO> getPeriodShippingInfo(Long periodOrderId);

    List<?> queryExpressInfo(Long shippingOrderNo);

}
