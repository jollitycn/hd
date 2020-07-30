package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.entity.PhoneBlacklistStrategy;
import com.insigma.ordercenter.entity.dto.StrategyParamDTO;
import com.insigma.ordercenter.entity.vo.PhoneBlackVO;
import com.insigma.ordercenter.mapper.PhoneBlacklistStrategyMapper;
import com.insigma.ordercenter.service.IPhoneBlacklistStrategyService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 手机号黑名单表 服务实现类
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-28
 */
@Service
public class PhoneBlacklistStrategyServiceImpl extends ServiceImpl<PhoneBlacklistStrategyMapper,
 PhoneBlacklistStrategy> implements IPhoneBlacklistStrategyService {

    @Override
    public IPage<PhoneBlackVO> listPhoneBlackPage(StrategyParamDTO req) {
        Page<PhoneBlackVO> page = new Page<>(null == req.getPageNum() ? 1 : req.getPageNum(),
                null == req.getPageSize() ? 10 : req.getPageSize());
        return baseMapper.listPhoneBlackPage(page, req);
    }
}
