package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.entity.RegionBlacklistStrategy;
import com.insigma.ordercenter.entity.dto.StrategyParamDTO;
import com.insigma.ordercenter.entity.vo.RegionBlackVO;
import com.insigma.ordercenter.mapper.RegionBlacklistStrategyMapper;
import com.insigma.ordercenter.service.IRegionBlacklistStrategyService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 区域黑名单表 服务实现类
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-28
 */
@Service
public class RegionBlacklistStrategyServiceImpl extends ServiceImpl<RegionBlacklistStrategyMapper,
 RegionBlacklistStrategy> implements IRegionBlacklistStrategyService {

    @Override
    public IPage<RegionBlackVO> listRegionBlackPage(StrategyParamDTO req) {
        Page<RegionBlackVO> page = new Page<>(null == req.getPageNum() ? 1 : req.getPageNum(),
                null == req.getPageSize() ? 10 : req.getPageSize());
        return baseMapper.listRegionBlackPage(page, req);
    }
}
