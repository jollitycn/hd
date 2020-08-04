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
import com.insigma.ordercenter.service.IOrderDetailService;
import com.insigma.ordercenter.service.IRefundService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

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

    @Resource
    private IOrderDetailService orderDetailService;


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

        RefundDetailVO result=baseMapper.getRefundDetail(refundId);
        result.setProductList(orderDetailService.getOrderDetail(result.getOrderId()));

        return result;
    }

    /**
     * 添加退货单
     *
     * @param addRefundDTO
     * @return
     */
    @Override
    public boolean addRefund(AddRefundDTO addRefundDTO) {

        Long refundId=addRefundDTO.getRefundId();

        List<Long> productIdList=addRefundDTO.getProductIdList();
        //处理退货的商品
        //TODO

        Refund refund=new Refund();

        //新增退货单
        if(null==refundId){
            refund.setCreateTime(LocalDateTime.now());
            //编辑
        }else {
            refund=this.getById(refundId);
        }
        BeanUtil.copyProperties(addRefundDTO,refund);

        return null==refundId?this.save(refund):this.updateById(refund);
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
