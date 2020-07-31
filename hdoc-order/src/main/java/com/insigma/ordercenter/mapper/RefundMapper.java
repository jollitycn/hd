package com.insigma.ordercenter.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.entity.Refund;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.dto.RefundDTO;
import com.insigma.ordercenter.entity.vo.RefundVO;

/**
 * <p>
 * 退换货记录表 Mapper 接口
 * </p>
 *
 * @author Jason
 * @since 2020-07-22
 */
public interface RefundMapper extends BaseMapper<Refund> {

    IPage<RefundVO> getRefundList(Page<RefundVO> page, RefundDTO refundDTO);

}
