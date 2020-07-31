package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.insigma.ordercenter.entity.OrderDetail;
import com.insigma.ordercenter.entity.SendReceiveInfo;
import com.insigma.ordercenter.entity.vo.SendReceiveInfoVO;
import com.insigma.ordercenter.entity.vo.UpdateSendReceiveInfoVO;
import com.insigma.ordercenter.mapper.SendReceiveInfoMapper;
import com.insigma.ordercenter.service.IOrderDetailService;
import com.insigma.ordercenter.service.ISendReceiveInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 收发人信息 服务实现类
 * </p>
 *
 * @author AH
 * @since 2020-07-22
 */
@Service
public class SendReceiveInfoServiceImpl extends ServiceImpl<SendReceiveInfoMapper, SendReceiveInfo> implements ISendReceiveInfoService {




}
