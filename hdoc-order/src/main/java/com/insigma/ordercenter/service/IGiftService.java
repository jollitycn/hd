package com.insigma.ordercenter.service;

import com.insigma.ordercenter.entity.Gift;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.dto.GiftNumDTO;

/**
 * <p>
 * 赠品信息表 服务类
 * </p>
 *
 * @author panjuncai
 * @since 2020-07-29
 */
public interface IGiftService extends IService<Gift> {

    /**
     * 更新赠品數量
     * @return
     * @param giftNumDTO
     */
    Boolean updateGiftNum(GiftNumDTO giftNumDTO);
}
