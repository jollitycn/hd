package com.insigma.ordercenter.service;

import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.LoginUser;
import com.insigma.ordercenter.entity.OrderOperationLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.vo.OrderOperationLogVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 订单操作日志表 服务类
 * </p>
 *
 * @author AH
 * @since 2020-07-27
 */
public interface IOrderOperationLogService extends IService<OrderOperationLog> {


    Result addOrderOperationLog(OrderOperationLog orderOperationLog, LoginUser loginUser);

}
