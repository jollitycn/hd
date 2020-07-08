package com.insigma.ordercenter.service.impl;

import com.insigma.ordercenter.entity.Product;
import com.insigma.ordercenter.mapper.ProductMapper;
import com.insigma.ordercenter.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-08
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
