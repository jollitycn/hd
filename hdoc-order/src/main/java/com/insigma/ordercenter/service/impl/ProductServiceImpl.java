package com.insigma.ordercenter.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.constant.Constant;
import com.insigma.ordercenter.entity.Product;
import com.insigma.ordercenter.entity.ShopProduct;
import com.insigma.ordercenter.entity.Tag;
import com.insigma.ordercenter.entity.dto.ProductAddDTO;
import com.insigma.ordercenter.entity.dto.ProductListDTO;
import com.insigma.ordercenter.entity.dto.ShopRatioDTO;
import com.insigma.ordercenter.entity.vo.ProductDetailVO;
import com.insigma.ordercenter.entity.vo.ProductListPageVO;
import com.insigma.ordercenter.entity.vo.ShopProductVO;
import com.insigma.ordercenter.entity.vo.TagVO;
import com.insigma.ordercenter.mapper.ProductMapper;
import com.insigma.ordercenter.mapper.ShopProductMapper;
import com.insigma.ordercenter.mapper.TagMapper;
import com.insigma.ordercenter.service.IProductService;
import com.insigma.ordercenter.service.IWarehouseProductRelationService;
import com.insigma.ordercenter.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Resource
    private TagMapper tagMapper;

    @Resource
    private ShopProductMapper shopProductMapper;

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
        if (productListDTO.getProductNo() != null) {
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

        Long productId=productAddDTO.getProductId();

        Product product = new Product();

        //新增商品
        if(null==productId){

            BeanUtils.copyProperties(productAddDTO, product);
            product.setIsPutOn(Constant.SYS_ONE);
            product.setCreateTime(LocalDateTime.now());

            return this.save(product);
        }else{
            //编辑商品
            product = getById(productId);
            BeanUtils.copyProperties(productAddDTO, product);

            //删除旧的商品标签
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq(Tag.PRODUCT_ID,productId);
            tagMapper.delete(queryWrapper);

        }

        //处理预约发货参数
        Integer isReserve=productAddDTO.getIsReserve();
        Integer cycle=productAddDTO.getCycle();
        if(Constant.SYS_ONE==isReserve.intValue()&&Constant.SYS_FOUR==cycle.intValue()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            product.setReserveTime(LocalDateTime.parse(productAddDTO.getReserveTime(),formatter));
        }

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

        //保存或者删除
        return productId==null?this.save(product):this.updateById(product);
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

    @Override
    public List<ShopProductVO> getProductRatio(Long productId) {
        return shopProductMapper.getProductRatio(productId);
    }

    @Override
    public boolean editRatio(Long productId, List<ShopRatioDTO> shopRatioDTOList) {


        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq(ShopProduct.PRODUCT_ID,productId);
        shopProductMapper.delete(queryWrapper);


        for (ShopRatioDTO shopRatioD:shopRatioDTOList) {
            ShopProduct shopProduct=new ShopProduct();
            shopProduct.setProductId(productId);
            shopProduct.setShopId(shopRatioD.getShopId());
            shopProduct.setRatio(shopRatioD.getRatio());
            shopProduct.setCreateTime(LocalDateTime.now());
            shopProductMapper.insert(shopProduct);
        }

        return true;
    }

    @Override
    public boolean editWarningValue(Long spid, Integer warningValue) {
        ShopProduct shopProduct=shopProductMapper.selectById(spid);
        if(null!=shopProduct){
            shopProduct.setWarningValue(warningValue);
            shopProduct.setModifyTime(LocalDateTime.now());
            shopProductMapper.updateById(shopProduct);
            return true;
        }
        return false;
    }


}
