package com.insigma.ordercenter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.constant.Constant;
import com.insigma.ordercenter.entity.LoginUser;
import com.insigma.ordercenter.entity.Refund;
import com.insigma.ordercenter.entity.dto.AddRefundDTO;
import com.insigma.ordercenter.entity.dto.RefundDTO;
import com.insigma.ordercenter.entity.vo.RefundDetailVO;
import com.insigma.ordercenter.entity.vo.RefundVO;
import com.insigma.ordercenter.mapper.OrderMapper;
import com.insigma.ordercenter.mapper.RefundMapper;
import com.insigma.ordercenter.service.IOrderDetailService;
import com.insigma.ordercenter.service.IRefundService;
import com.insigma.ordercenter.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource
    private IOrderDetailService orderDetailService;

    @Resource
    private OrderMapper orderMapper;

    @Override
    public IPage<RefundVO> getRefundList(RefundDTO refundDTO) {

        //处理分页参数
        Page<RefundVO> page = new Page<>(refundDTO.getPageNum(), refundDTO.getPageSize());

        //模糊查询参数处理
        String orderNo=refundDTO.getOrderNo();
        if (StrUtil.isNotEmpty(orderNo)) {
            orderNo = StringUtil.addPercent(orderNo);
        }

        String refundNo=refundDTO.getRefundNo();
        if (StrUtil.isNotEmpty(refundNo)) {
            refundNo = StringUtil.addPercent(refundNo);
        }

        //查询
        IPage<RefundVO> result=baseMapper.getRefundList(page, orderNo, refundNo);

        return result;
    }

    /**
     * 获取退货单详情
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
     *@param loginUser
     * @param addRefundDTO
     * @return
     */
    @Override
    public boolean addRefund(LoginUser loginUser, AddRefundDTO addRefundDTO) {

        Refund refund=new Refund();
        BeanUtil.copyProperties(addRefundDTO,refund);
        refund.setCreateId(loginUser.getUserId());
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


        //归还库存 TODO



        //更改退货单状态
        Refund refund=this.getById(refundId);
        refund.setStatus(Constant.SYS_ONE);




        return this.updateById(refund);
    }


}
