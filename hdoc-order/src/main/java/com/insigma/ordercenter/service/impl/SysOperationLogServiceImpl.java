package com.insigma.ordercenter.service.impl;

import com.insigma.ordercenter.entity.SysOperationLog;
import com.insigma.ordercenter.mapper.SysOperationLogMapper;
import com.insigma.ordercenter.service.ISysOperationLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统订单操作日志表 服务实现类
 * </p>
 *
 * @author AH
 * @since 2020-08-18
 */
@Service
public class SysOperationLogServiceImpl extends ServiceImpl<SysOperationLogMapper, SysOperationLog> implements ISysOperationLogService {

}
