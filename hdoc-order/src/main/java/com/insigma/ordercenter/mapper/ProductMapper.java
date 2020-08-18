package com.insigma.ordercenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.entity.Product;
import com.insigma.ordercenter.entity.vo.ProductDetailVO;
import com.insigma.ordercenter.entity.vo.ProductListPageVO;
import com.insigma.ordercenter.entity.vo.ShippingProductVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author Jason
 * @since 2020-07-08
 */
public interface ProductMapper extends BaseMapper<Product> {

    List<ProductListPageVO> getProductListPage(Page<ProductListPageVO> page,
                                               @Param("productName") String productName,
                                               @Param("productSku") String productSku,
                                               @Param("productNo")String productNo,
                                               @Param("isPutOn")Integer isPutOn,
                                               @Param("isCombo")Integer isCombo,
                                               @Param("brand")String brand);

    ProductDetailVO getProductDetail(@Param("productId")Long productId);

    List<ShippingProductVO> getShippingOrderProductList(@Param("shippingOrderId")Long shippingOrderId);

    List<ProductDetailVO> getProductListByshippingOrderId(Long shippingOrderId);
}
