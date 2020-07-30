package com.insigma.ordercenter.service.impl;

import com.insigma.ordercenter.entity.Gift;
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

}
