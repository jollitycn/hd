package com.insigma.ordercenter.service.impl;

import com.insigma.ordercenter.entity.Tag;
import com.insigma.ordercenter.mapper.TagMapper;
import com.insigma.ordercenter.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品标签表 服务实现类
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-24
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

}
