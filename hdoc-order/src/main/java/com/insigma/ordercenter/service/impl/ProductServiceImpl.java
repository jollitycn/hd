package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.constant.Constant;
import com.insigma.ordercenter.entity.Product;
import com.insigma.ordercenter.entity.dto.ProductAddDTO;
import com.insigma.ordercenter.entity.dto.ProductListDTO;
import com.insigma.ordercenter.entity.dto.ProductUpdateDTO;
import com.insigma.ordercenter.entity.vo.ProductListPageVO;
import com.insigma.ordercenter.mapper.ProductMapper;
import com.insigma.ordercenter.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.service.IWarehouseProductRelationService;
import com.insigma.ordercenter.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

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

    @Resource
    private IWarehouseProductRelationService warehouseProductRelationService;

    @Override
    public IPage<ProductListPageVO> getProductListPage(Page<ProductListPageVO> page, ProductListDTO productListDTO) {

        String productName = null;
        if (productListDTO.getProductName() != null) {
            productName = StringUtil.addPercent(productListDTO.getProductName());
        }
        String productSku = null;
        if (productListDTO.getProductSku() != null) {
            productSku = StringUtil.addPercent(productListDTO.getProductSku());
        }
        List<ProductListPageVO> resultList = baseMapper.getProductListPage(page, productName, productSku);

        //获取商品的总库存数量
        for (ProductListPageVO productListPageVO : resultList) {
            int stockQuantity = warehouseProductRelationService.getTotalStock(productListPageVO.getProductId());
            productListPageVO.setStockQuantity(stockQuantity);
        }
        IPage<ProductListPageVO> ipage = page;
        ipage.setRecords(resultList);
        return ipage;
    }

    @Override
    public boolean add(ProductAddDTO productAddDTO) {

        Product product = new Product();
        BeanUtils.copyProperties(productAddDTO, product);
        product.setCreateTime(LocalDateTime.now());
        return save(product);
    }

    @Override
    public boolean edit(ProductUpdateDTO productUpdateDTO) {

        Product product = getById(productUpdateDTO.getProductId());

        if (product != null) {
            BeanUtils.copyProperties(productUpdateDTO, product);
            return updateById(product);
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Long productId) {

        Product product = getById(productId);

        if (product != null) {
            product.setIsDeleted(Constant.SYS_ONE);
            return updateById(product);
        } else {
            return false;
        }
    }
}
