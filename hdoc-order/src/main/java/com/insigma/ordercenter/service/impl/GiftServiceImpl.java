package com.insigma.ordercenter.service.impl;

import com.insigma.ordercenter.entity.Gift;
import com.insigma.ordercenter.entity.dto.GiftNumDTO;
import com.insigma.ordercenter.mapper.GiftMapper;
import com.insigma.ordercenter.service.IGiftService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 赠品信息表 服务实现类
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-29
 */
@Service
public class GiftServiceImpl extends ServiceImpl<GiftMapper, Gift> implements IGiftService {

    @Override
    public Boolean updateGiftNum(GiftNumDTO giftNumDTO) {
        Gift gift = this.baseMapper.selectById(giftNumDTO.getGiftId());
        gift.setGiftNum(giftNumDTO.getGiftNum());
        int i = this.baseMapper.updateById(gift);
        if(1 == i){
            return true;
        }
        return false;
    }
}
