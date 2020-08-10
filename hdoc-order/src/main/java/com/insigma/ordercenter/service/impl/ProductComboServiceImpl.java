package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.constant.Constant;
import com.insigma.ordercenter.entity.Product;
import com.insigma.ordercenter.entity.ProductCombo;
import com.insigma.ordercenter.entity.dto.AddComboDTO;
import com.insigma.ordercenter.entity.vo.ProductComboVO;
import com.insigma.ordercenter.mapper.ProductComboMapper;
import com.insigma.ordercenter.mapper.ProductMapper;
import com.insigma.ordercenter.service.IProductComboService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 组合信息表 服务实现类
 * </p>
 *
 * @author Jason
 * @since 2020-07-22
 */
@Service
public class ProductComboServiceImpl extends ServiceImpl<ProductComboMapper, ProductCombo> implements IProductComboService {

    @Resource
    private ProductMapper productMapper;


    /**
     * 获取商品组合列表
     *
     * @param productId
     * @return
     */
    @Override
    public List<ProductComboVO> getComboList(Long productId) {
        return baseMapper.getComboList(productId);
    }

    /**
     * 删除商品组合列表
     *
     * @param productComboId
     * @return
     */
    @Override
    public boolean deleteCombo(Long productComboId) {
        ProductCombo productCombo=this.getById(productComboId);
        productCombo.setIsDeleted(Constant.SYS_ONE);
        return this.updateById(productCombo);
    }

    /**
     * 添加商品组合
     *@param productId
     * @param addComboDTOList
     * @return
     */
    @Override
    public boolean addCombo(Long productId,List<AddComboDTO> addComboDTOList) {

        //批量添加组合商品
        List<ProductCombo> insert=new ArrayList<>();
        for (AddComboDTO comboDTO:addComboDTOList) {
            ProductCombo productCombo=new ProductCombo();
            productCombo.setParentProductId(productId);
            productCombo.setChildProductId(comboDTO.getProductId());
            productCombo.setQuantity(comboDTO.getQuantity());
            insert.add(productCombo);
        }

        //将该商品设置为组合商品
        Product product=productMapper.selectById(productId);
        product.setIsCombo(Constant.SYS_ONE);
        productMapper.updateById(product);

        return this.saveBatch(insert);
    }

    /**
     * 编辑商品组合数量
     *
     * @param productComboId
     * @param quantity
     * @return
     */
    @Override
    public boolean editCombo(Long productComboId, Integer quantity) {
        ProductCombo productCombo=this.getById(productComboId);
        productCombo.setQuantity(quantity);
        return this.updateById(productCombo);
    }
}
