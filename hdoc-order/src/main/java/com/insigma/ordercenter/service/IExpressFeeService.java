package com.insigma.ordercenter.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.insigma.ordercenter.entity.ExpressFee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.LoginUser;
import com.insigma.ordercenter.entity.dto.ExpressFeeAddDTO;
import com.insigma.ordercenter.entity.dto.ExpressFeeEditDTO;
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
 * @since 2020-07-28
 */
public interface IExpressFeeService extends IService<ExpressFee> {

    ExpressFeeDetailVO detail(Long id);

    boolean delete(Serializable id);

    boolean add(ExpressFeeAddDTO expressFeeAddDTO);

    IPage<ExpressFeePageVO> page(ExpressFeePageDTO expressFeePageDTO);

    boolean edit(ExpressFeeEditDTO expressFeeEditDTO);
}
