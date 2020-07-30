package com.insigma.ordercenter.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.AccountBlacklistStrategy;
import com.insigma.ordercenter.entity.dto.StrategyParamDTO;
import com.insigma.ordercenter.entity.vo.AccountBlackVO;

/**
 * <p>
 * 账户黑名单表 服务类
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-28
 */
public interface IAccountBlacklistStrategyService extends IService<AccountBlacklistStrategy> {
    /**
     * 分页查询账号黑名单
     *
     * @param req 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage&lt;com.insigma.ordercenter.entity.vo.AccountBlackVO&gt;
     * @author Pan Juncai
     * @date 2020/7/29 13:51
     */
    IPage<AccountBlackVO> listAccountBlackPage(StrategyParamDTO req);
}
