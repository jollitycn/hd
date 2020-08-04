package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.constant.Constant;
import com.insigma.ordercenter.entity.PeriodOrder;
import com.insigma.ordercenter.entity.PeriodOrderDetail;
import com.insigma.ordercenter.entity.PeriodOrderOperationLog;
import com.insigma.ordercenter.entity.PeriodSendReceiveInfo;
import com.insigma.ordercenter.entity.dto.PeriodOrderDTO;
import com.insigma.ordercenter.entity.dto.PeriodOrderStatuDTO;
import com.insigma.ordercenter.entity.dto.PeriodStatuDTO;
import com.insigma.ordercenter.entity.vo.*;
import com.insigma.ordercenter.mapper.PeriodOrderMapper;
import com.insigma.ordercenter.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 预约订单 服务实现类
 * </p>
 *
 * @author AH
 * @since 2020-07-28
 */
@Service
public class PeriodOrderServiceImpl extends ServiceImpl<PeriodOrderMapper, PeriodOrder> implements IPeriodOrderService {

    @Resource
    private IPeriodOrderDetailService periodOrderDetailService;

    @Resource
    private IPeriodSendReceiveInfoService periodSendReceiveInfoService;

    @Resource
    private IPeriodOrderOperationLogService periodOrderOperationLogService;

    @Override
    public IPage<PeriodOrderVO> queryPeriodOrderListPage(Page<PeriodOrderVO> page, PeriodOrderDTO periodOrderDTO) {
        return baseMapper.queryPeriodOrderListPage(page,periodOrderDTO);
    }

    @Override
    public Boolean updatePeriodOrderStatu(PeriodOrderStatuDTO periodOrderStatuDTO) {
        PeriodOrder periodOrder=new PeriodOrder();
        periodOrder.setPeriodOrderId(periodOrderStatuDTO.getPeriodOrderId());
        periodOrder.setOrderStatus(periodOrderStatuDTO.getOrderStatus());
        int i = baseMapper.updateById(periodOrder);
        if(i>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean updatePeriodStatu(PeriodStatuDTO periodStatuDTO) {
        PeriodOrder periodOrder=new PeriodOrder();
        periodOrder.setPeriodOrderId(periodStatuDTO.getPeriodOrderId());
        periodOrder.setIsStop(periodStatuDTO.getIsStop());
        if(periodOrder.getIsStop() == 1){
            periodOrder.setOrderStatus(Constant.SYS_ONE);
        }
        int i = baseMapper.updateById(periodOrder);
        if(i>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<PeriodOrderDetail> getPeriodOrderDetailList(Long periodOrderId) {
        QueryWrapper<PeriodOrderDetail> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("period_order_id",periodOrderId);
        List<PeriodOrderDetail> list = periodOrderDetailService.list(queryWrapper);
        return list;
    }

    @Override
    public List<PeriodSendReceiveInfo> getPeriodSendReceiveInfo(Long periodOrderId) {
        QueryWrapper<PeriodSendReceiveInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("period_order_id",periodOrderId);
        List<PeriodSendReceiveInfo> list = periodSendReceiveInfoService.list(queryWrapper);
        return list;
    }

    @Override
    public List<PeriodOrderOperationLogVO> getPeriodOrderOperationLog(Long periodOrderId) {

        return baseMapper.getPeriodOrderOperationLog(periodOrderId);
    }


    @Override
    public List<PeriodOrderPayVO> getPeriodPayInfo(Long periodOrderId) {
        return baseMapper.getPeriodPayInfo(periodOrderId);
    }


    @Override
    public List<PeriodOrderShippingVO> getPeriodShippingInfo(Long periodOrderId) {
        return baseMapper.getPeriodShippingInfo(periodOrderId);
    }

}
