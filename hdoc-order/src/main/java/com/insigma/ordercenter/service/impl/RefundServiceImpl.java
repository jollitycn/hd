package com.insigma.ordercenter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.constant.Constant;
import com.insigma.ordercenter.entity.Refund;
import com.insigma.ordercenter.entity.dto.AddRefundDTO;
import com.insigma.ordercenter.entity.dto.RefundDTO;
import com.insigma.ordercenter.entity.vo.RefundDetailVO;
import com.insigma.ordercenter.entity.vo.RefundVO;
import com.insigma.ordercenter.mapper.RefundMapper;
import com.insigma.ordercenter.service.IRefundService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 退换货记录表 服务实现类
 * </p>
 *
 * @author Jason
 * @since 2020-07-22
 */
@Service
public class RefundServiceImpl extends ServiceImpl<RefundMapper, Refund> implements IRefundService {

    @Override
    public IPage<RefundVO> getRefundList(RefundDTO refundDTO) {

        //处理分页参数
        Page<RefundVO> page = new Page<>(refundDTO.getPageNum(), refundDTO.getPageSize());

        //查询
        IPage<RefundVO> result=baseMapper.getRefundList(page,refundDTO);

        return result;
    }

    /**
     * 获取发货单详情
     *
     * @param refundId
     * @return
     */
    @Override
    public RefundDetailVO getRefundDetail(Long refundId) {

        //TODO

        return null;
    }

    /**
     * 添加退货单
     *
     * @param addRefundDTO
     * @return
     */
    @Override
    public boolean addRefund(AddRefundDTO addRefundDTO) {

        Refund refund=new Refund();
        BeanUtil.copyProperties(addRefundDTO,refund);
        refund.setCreateTime(LocalDateTime.now());

        return this.save(refund);
    }

    /**
     * 退货单入库
     *
     * @param refundId
     * @return
     */
    @Override
    public boolean warehousing(Long refundId) {

        //TODO 规划库存

        //更改退货单状态
        Refund refund=this.getById(refundId);
        refund.setStatus(Constant.SYS_ONE);

        return this.updateById(refund);
    }


}
