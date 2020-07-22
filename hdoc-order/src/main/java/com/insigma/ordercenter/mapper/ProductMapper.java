package com.insigma.ordercenter.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.vo.ProductDetailVO;
import com.insigma.ordercenter.entity.vo.ProductListPageVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-08
 */
public interface ProductMapper extends BaseMapper<Product> {

    List<ProductListPageVO> getProductListPage(Page<ProductListPageVO> page,
                                               @Param("productName") String productName,
                                               @Param("productSku") String productSku);

    ProductDetailVO getProductDetail(@Param("productId")Long productId);
}
