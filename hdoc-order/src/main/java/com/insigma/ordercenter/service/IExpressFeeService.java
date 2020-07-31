package com.insigma.ordercenter.service;

import com.insigma.ordercenter.entity.ExpressFee;
import com.baomidou.mybatisplus.extension.service.IService;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.insigma.ordercenter.entity.dto.ExpressFeeSaveDTO;
import com.insigma.ordercenter.entity.dto.ExpressFeePageDTO;
import com.insigma.ordercenter.entity.vo.ExpressFeeDetailVO;
import com.insigma.ordercenter.entity.vo.ExpressFeePageVO;
import java.io.Serializable;
/**
 * <p>
 * 物流报价表 服务类
 * </p>
 *
 * @author Jason
 * @since 2020-07-30
 */
public interface IExpressFeeService extends IService<ExpressFee> {

ExpressFeeDetailVO detail(Serializable id);

boolean delete(Serializable id);

 boolean deletes(Serializable... id);
boolean add(ExpressFeeSaveDTO expressFeeSaveDTO);

IPage<ExpressFeePageVO> page(ExpressFeePageDTO expressFeeDTO);

 boolean edit(ExpressFeeSaveDTO  expressFeeSaveDTO);
}
