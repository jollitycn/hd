package com.insigma.ordercenter.service.impl;

import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.LoginUser;
import com.insigma.ordercenter.entity.OrderOperationLog;
import com.insigma.ordercenter.entity.vo.OrderOperationLogVO;
import com.insigma.ordercenter.mapper.OrderOperationLogMapper;
import com.insigma.ordercenter.service.IOrderOperationLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 订单操作日志表 服务实现类
 * </p>
 *
 * @author AH
 * @since 2020-07-27
 */
@Service
public class OrderOperationLogServiceImpl extends ServiceImpl<OrderOperationLogMapper, OrderOperationLog> implements IOrderOperationLogService {

    @Override
    public Result addOrderOperationLog(OrderOperationLog orderOperationLog, LoginUser loginUser) {
        orderOperationLog.setContent("新增订单");
        orderOperationLog.setCreateId(loginUser.getUserId());
        orderOperationLog.setCreateTime(LocalDateTime.now());
        int insert = baseMapper.insert(orderOperationLog);
        if (insert > 0) {
            return Result.success();
        }else{
            return Result.error(CodeMsg.DATA_INSERT_ERROR);
        }
    }
}
