package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.entity.AccountBlacklistStrategy;
import com.insigma.ordercenter.entity.dto.StrategyParamDTO;
import com.insigma.ordercenter.entity.vo.AccountBlackVO;
import com.insigma.ordercenter.mapper.AccountBlacklistStrategyMapper;
import com.insigma.ordercenter.service.IAccountBlacklistStrategyService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账户黑名单表 服务实现类
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-28
 */
@Service
public class AccountBlacklistStrategyServiceImpl extends ServiceImpl<AccountBlacklistStrategyMapper,
 AccountBlacklistStrategy> implements IAccountBlacklistStrategyService {

    @Override
    public IPage<AccountBlackVO> listAccountBlackPage(StrategyParamDTO req) {
        Page<AccountBlackVO> page = new Page<>(null == req.getPageNum() ? 1 : req.getPageNum(),
                null == req.getPageSize() ? 10 : req.getPageSize());
        return baseMapper.listAccountBlackPage(page, req);
    }
}
