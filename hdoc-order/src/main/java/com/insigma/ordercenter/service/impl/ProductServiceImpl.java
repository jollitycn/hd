package com.insigma.ordercenter.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.constant.Constant;
import com.insigma.ordercenter.entity.Product;
import com.insigma.ordercenter.entity.ProductCombo;
import com.insigma.ordercenter.entity.Tag;
import com.insigma.ordercenter.entity.dto.AddComboDTO;
import com.insigma.ordercenter.entity.dto.ProductAddDTO;
import com.insigma.ordercenter.entity.dto.ProductListDTO;
import com.insigma.ordercenter.entity.dto.ProductUpdateDTO;
import com.insigma.ordercenter.entity.vo.ProductDetailVO;
import com.insigma.ordercenter.entity.vo.ProductListPageVO;
import com.insigma.ordercenter.entity.vo.TagVO;
import com.insigma.ordercenter.mapper.ProductComboMapper;
import com.insigma.ordercenter.mapper.ProductMapper;
import com.insigma.ordercenter.mapper.TagMapper;
import com.insigma.ordercenter.service.IProductService;
import com.insigma.ordercenter.service.IWarehouseProductRelationService;
import com.insigma.ordercenter.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author Jason
 * @since 2020-07-08
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Resource
    private IWarehouseProductRelationService warehouseProductRelationService;

    @Autowired
    private ProductComboMapper productComboMapper;

    @Autowired
    private TagMapper tagMapper;

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
        String productNo = null;
        if (productListDTO.getProductSku() != null) {
            productNo = StringUtil.addPercent(productListDTO.getProductNo());
        }
        List<ProductListPageVO> resultList = baseMapper.getProductListPage(page, productName, productSku,productNo);

        //获取商品的总库存数量
        for (ProductListPageVO productListPageVO : resultList) {
            Integer stockQuantity = warehouseProductRelationService.getTotalStock(productListPageVO.getProductId());
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
        product.setProductNo("SP"+ IdUtil.objectId());
        product.setIsPutOn(Constant.SYS_ONE);
        product.setCreateTime(LocalDateTime.now());

        this.save(product);

        //写入商品标签
        List<String> tagCodeList=productAddDTO.getTagList();
        if(CollUtil.isNotEmpty(tagCodeList)){
            for (String tagCode:tagCodeList) {
                Tag tag=new Tag();
                tag.setTag(tagCode);
                tag.setProductId(product.getProductId());
                tagMapper.insert(tag);
            }
        }

        return true;
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

    /**
     * 获取商品详情
     *
     * @param productId
     * @return
     */
    @Override
    public ProductDetailVO getProductDetail(Long productId) {

        //查询详情
        ProductDetailVO result=baseMapper.getProductDetail(productId);

        //查询标签
        List<TagVO> tagList=tagMapper.getTagList(productId);

        //非空判断
        if(CollUtil.isNotEmpty(tagList)){
            result.setTagList(tagList);
        }

        return result;
    }

    /**
     * 添加商品组合
     *
     * @param addComboDTO
     * @return
     */
    @Override
    public boolean addCombo(AddComboDTO addComboDTO) {

        String[] productIds=addComboDTO.getProductIds().split(",");
        Long parentProductId=addComboDTO.getProductId();
        for (String productId:productIds) {
            ProductCombo productCombo=new ProductCombo();
            productCombo.setParentProductId(parentProductId);
            productCombo.setChildProductId(Long.valueOf(productId));
            productCombo.setQuantity(addComboDTO.getQuantity());
            productComboMapper.insert(productCombo);
        }
        return true;
    }

    /**
     * 禁用/启用商品组合
     *
     * @param productId
     * @return
     */
    @Override
    public boolean disable(Long productId) {
        Product product = getById(productId);

        if (product != null) {
            Integer isPutOn=product.getIsPutOn();
            if(Constant.SYS_ZERO==isPutOn.intValue()){
                product.setPutOnTime(LocalDateTime.now());
                product.setIsPutOn(Constant.SYS_ONE);
            }else{
                product.setIsPutOn(Constant.SYS_ZERO);
            }
            return updateById(product);
        } else {
            return false;
        }
    }
}
