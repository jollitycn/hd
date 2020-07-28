package com.insigma.ordercenter.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.Product;
import com.insigma.ordercenter.entity.dto.AddComboDTO;
import com.insigma.ordercenter.entity.dto.ProductAddDTO;
import com.insigma.ordercenter.entity.dto.ProductListDTO;
import com.insigma.ordercenter.entity.dto.ShopRatioDTO;
import com.insigma.ordercenter.entity.vo.ProductDetailVO;
import com.insigma.ordercenter.entity.vo.ProductListPageVO;
import com.insigma.ordercenter.entity.vo.ShopProductVO;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author Jason
 * @since 2020-07-08
 */
public interface IProductService extends IService<Product> {

    IPage<ProductListPageVO> getProductListPage(Page<ProductListPageVO> page, ProductListDTO productListDTO);

    boolean add(ProductAddDTO productAddDTO);


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

    /**
     * 禁用/启用商品组合
     * @param productId
     * @return
     */
    boolean disable(Long productId);

    /**
     * 获取商品电商发货比例列表
     * @param productId
     * @return
     */
    List<ShopProductVO> getProductRatio(Long productId);


    /**
     * 编辑电商发货比例
     * @param productId
     * @param shopRatioDTOList
     * @return
     */
    boolean editRatio(Long productId, List<ShopRatioDTO> shopRatioDTOList);
}
