package com.insigma.ordercenter.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.dto.AddComboDTO;
import com.insigma.ordercenter.entity.dto.ProductAddDTO;
import com.insigma.ordercenter.entity.dto.ProductListDTO;
import com.insigma.ordercenter.entity.dto.ProductUpdateDTO;
import com.insigma.ordercenter.entity.vo.ProductDetailVO;
import com.insigma.ordercenter.entity.vo.ProductListPageVO;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-08
 */
public interface IProductService extends IService<Product> {

    IPage<ProductListPageVO> getProductListPage(Page<ProductListPageVO> page, ProductListDTO productListDTO);

    boolean add(ProductAddDTO productAddDTO);

    boolean edit(ProductUpdateDTO productUpdateDTO);

    boolean delete(Long productId);

    /**
     * 获取商品详情
     * @param productId
     * @return
     */
    ProductDetailVO getProductDetail(Long productId);

    /**
     * 添加商品组合
     * @param addComboDTO
     * @return
     */
    boolean addCombo(AddComboDTO addComboDTO);
}
