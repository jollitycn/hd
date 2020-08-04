package com.insigma.ordercenter.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.insigma.ordercenter.entity.Refund;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.dto.AddRefundDTO;
import com.insigma.ordercenter.entity.dto.RefundDTO;
import com.insigma.ordercenter.entity.vo.RefundDetailVO;
import com.insigma.ordercenter.entity.vo.RefundVO;

/**
 * <p>
 * 退换货记录表 服务类
 * </p>
 *
 * @author Xuchao
 * @since 2020-07-22
 */
public interface IRefundService extends IService<Refund> {

    /**
     * 获取退货单列表
     * @param refundDTO
     * @return
     */
    IPage<RefundVO> getRefundList(RefundDTO refundDTO);

    /**
     * 获取发货单详情
     * @param refundId
     * @return
     */
    RefundDetailVO getRefundDetail(Long refundId);

    /**
     * 添加/编辑退货单
     * @param addRefundDTO
     * @return
     */
    boolean addRefund(AddRefundDTO addRefundDTO);

    /**
     * 退货单入库
     * @param refundId
     * @return
     */
    boolean warehousing(Long refundId);
}
