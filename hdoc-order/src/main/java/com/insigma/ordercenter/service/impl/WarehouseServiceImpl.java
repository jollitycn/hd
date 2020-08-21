package com.insigma.ordercenter.service.impl;

import com.alibaba.druid.sql.visitor.functions.Ascii;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.*;
import com.insigma.ordercenter.entity.dto.WarehouseDTO;
import com.insigma.ordercenter.entity.dto.WarehouseProductDTO;
import com.insigma.ordercenter.entity.dto.WarehouseProductPage;
import com.insigma.ordercenter.entity.dto.WarehouseProductPageQuery;
import com.insigma.ordercenter.entity.dto.shop.ShopQueryResponse;
import com.insigma.ordercenter.entity.vo.WarehouseVo;
import com.insigma.ordercenter.mapper.WarehouseMapper;
import com.insigma.ordercenter.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 仓库表 服务实现类
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-08
 */
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements IWarehouseService {

    @Autowired
    private IShopProductService shopProductService;
    @Autowired
    private IWarehouseTypeService typeService;

    @Autowired
    private IWarehouseRegionService warehouseRegionService;

    @Autowired
    private IWarehouseProductRelationService productRelationService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IStockOperationLogService stockLogService;

    @Override
    @Transactional
    public Result<?> addWarehouse(WarehouseDTO wareHouseDTO, LoginUser redisUser) throws MyException {
        //新增仓库
        Warehouse warehouse = new Warehouse();
        BeanUtils.copyProperties(wareHouseDTO,warehouse);
        checkDupl(warehouse);
        warehouse.setCreateId(redisUser.getUserId());
        warehouse.setCreateTime(LocalDateTime.now());
        boolean res = this.save(warehouse);

        //新增仓库地区
        Integer[] regionIds = wareHouseDTO.getRegionIds();
        for (Integer regionId : regionIds) {
            WarehouseRegion region = new WarehouseRegion();
            region.setWarehouseId(warehouse.getWarehouseId());
            region.setRegionId(regionId);
            warehouseRegionService.save(region);
        }
        Integer[] productTypes = wareHouseDTO.getProductTypes();
        for (Integer productType : productTypes) {
            WarehouseType warehouseType = new WarehouseType();
            warehouseType.setProductType(productType);
            warehouseType.setWarehouseId(warehouse.getWarehouseId());
            typeService.save(warehouseType);
        }


        if (res) {
            return Result.success(CodeMsg.SUCCESS);
        }
        return Result.error(CodeMsg.DATA_INSERT_ERROR);
    }

    private void checkDupl (Warehouse warehouse ) throws MyException {
        List<Warehouse>  os = null;
            os =  baseMapper.checkDuplWarehouseNo(warehouse);
            if(os !=null && os.size()>0) throw new MyException(CodeMsg.WARE_HOUSE_NO_DUPL);
            os =  baseMapper.checkDuplWarehouseName(warehouse);
            if(os !=null && os.size()>0)  throw new MyException(CodeMsg.WARE_HOUSE_NAME_DUPL);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Result<?> updateWarehouse(WarehouseDTO warehouseDTO, LoginUser loginUser) throws MyException {
        //修改仓库信息
        Warehouse warehouse = new Warehouse();
        BeanUtils.copyProperties(warehouseDTO,warehouse);
        checkDupl(warehouse);
        warehouse.setModifyId(loginUser.getUserId());
        warehouse.setModifyTime(LocalDateTime.now());
        this.updateById(warehouse);
        //先删除仓库地区和仓库管理员
        QueryWrapper<WarehouseType> wrapper1 = new QueryWrapper<>();
        wrapper1.eq(WarehouseManager.WAREHOUSE_ID,warehouse.getWarehouseId());
        typeService.remove(wrapper1);
        QueryWrapper<WarehouseRegion> wrapper2 = new QueryWrapper<>();
        wrapper2.eq(WarehouseRegion.WAREHOUSE_ID,warehouse.getWarehouseId());
        warehouseRegionService.remove(wrapper2);
        //重新添加仓库地区和管理员
        Integer[] regionIds = warehouseDTO.getRegionIds();
        for (Integer regionId : regionIds) {
            WarehouseRegion region = new WarehouseRegion();
            region.setWarehouseId(warehouse.getWarehouseId());
            region.setRegionId(regionId);
            warehouseRegionService.save(region);
        }
        QueryWrapper<WarehouseType> warehouseTypeQueryWrapper = new QueryWrapper<>();
        warehouseTypeQueryWrapper.eq(WarehouseType.WAREHOUSE_ID,warehouse.getWarehouseId());
        typeService.remove(warehouseTypeQueryWrapper);
        //重新添加仓库地区和管理员
        Integer[] productTypes = warehouseDTO.getProductTypes();
        for (Integer regionId : productTypes) {
            WarehouseType region = new WarehouseType();
            region.setWarehouseId(warehouse.getWarehouseId());
            region.setProductType(regionId);
            typeService.save(region);
        }

        return Result.success(CodeMsg.SUCCESS);
    }

    private void checkDuplProduct (Integer warehouseId,Long[] productIds ) throws MyException {
        List<WarehouseProductRelation>  os = null;
        os =  baseMapper.checkDuplProduct(warehouseId,productIds);
        if(os !=null && os.size()>0) throw new MyException(CodeMsg.PRODUCT_NO_DUPL);
    }



    @Override
    public Result<?> addProduct(WarehouseProductDTO req,Long userId) throws MyException {
        checkDuplProduct(req.getWarehouseId(),req.getProductIds());
        //遍历商品编号 ，新增仓库商品记录
        List<WarehouseProductRelation> whps = new ArrayList<WarehouseProductRelation>();
        List<StockOperationLog> sols = new ArrayList<StockOperationLog>();
        for (Long productId : req.getProductIds()) {
            WarehouseProductRelation whp = new WarehouseProductRelation();
            whp.setWarehouseId(req.getWarehouseId());
            whp.setProductId(productId);
            Product product = productService.getById(productId);
            if (null == product) {
                return Result.error(CodeMsg.PRODUCT_NOT_EXIST);
            }
            whp.setQuantity(0);
            whp.setProductSku(product.getProductSku());
            whps.add(whp);
        }
        productRelationService.saveBatch(whps);
//        WarehouseProductRelation[] whpArr = new WarehouseProductRelation[whps.size()];
//        updateLog(userId, whps.toArray(whpArr));
//        stockLogService.saveBatch(sols);
        return Result.success();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Result<?> modifyStock(WarehouseProductRelation whp, LoginUser redisUser) {
        StockOperationLog log = new StockOperationLog();
        //根据主键查询旧库存数量
        WarehouseProductRelation productStock = productRelationService.getById(whp.getWarehouseProductRelationId());
        if (null == productStock) {
            return Result.error(CodeMsg.STOKE_NOT_EXIST);
        }
        log.setOriginQuantity(productStock.getQuantity());
        //改变量等于 新库存减旧库存
        productStock.setQuantity(productStock.getQuantity()+ whp.getQuantity() );
        //更新库存
        boolean b = productRelationService.updateById(productStock);
        //新增库存变更记录
        log.setChangeQuantity(whp.getQuantity());
        log.setCreateId(redisUser.getUserId());
        log.setCreateTime(LocalDateTime.now());
        log.setDestinationQuantity(productStock.getQuantity());
        log.setOperationType(StockOperationLog.OperationTypeEnum.MANUAL.getStatus());
        log.setWarehouseProductRelationId(whp.getWarehouseProductRelationId());
        stockLogService.save(log);
        updateShopProduct(whp, productStock);
        //   List<ShopProduct> shopProducts= baseMapper.getRatio(productStock.getProductId());
        return Result.success(CodeMsg.SUCCESS);
    }

    private void updateShopProduct(WarehouseProductRelation whp, WarehouseProductRelation productStock) {
        //更新所有仓库的库存信息
        List<ShopProduct> shopProducts = shopProductService.getByProductId(productStock.getProductId());
        if (dealSP(whp, shopProducts)) return;
        shopProductService.saveOrUpdateBatch(shopProducts);
    }

    private boolean dealSP ( WarehouseProductRelation whp, List<ShopProduct> sps ) {
        if (checkSPS(sps)) return true;
        int tr = 0;
        Iterator it = sps.iterator();
        while (it.hasNext()) {
          Object object=   it.next();
          if(object!=null) {
              ShopProduct sp = (ShopProduct)object;
              tr += sp.getRatio();
          }
        }
        if (tr <= 0) return true;
        int tq = getTq(whp, sps, tr);
        int rn = whp.getQuantity() - tq;
        if (rn > 0 && sps.size() > 0) sRn(sps.get(sps.size() - 1), rn);
        return false;
    }

    private boolean checkSPS ( List<ShopProduct> sps ) {
        if (sps == null) return true;
        return false;
    }

    private void sRn ( ShopProduct sp, int rn ) {
        sp.setNumber(sp.getNumber() + rn);
    }

    private int getTq ( WarehouseProductRelation whp, List<ShopProduct> sps, int tr ) {
        int tq = 0 ;
        for (ShopProduct sp : sps) {
            long nq = setNb(whp, tr, sp);
            tq += nq;
        }
        return tq;
    }

    private long setNb ( WarehouseProductRelation whp, int tr, ShopProduct sp ) {
        long nq = getNq(whp, tr, sp);
        Long nn = sp.getNumber() + nq;
        sp.setNumber(nn.intValue());
        return nq;
    }

    private long getNq ( WarehouseProductRelation whp, int tr, ShopProduct sp ) {
        double ratio = 0.0 * tr / sp.getRatio();
        return Math.round(whp.getQuantity() * ratio);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Result<?> deleteWarehouse(Serializable warehouseId) {
        //删除仓库相关负责人
        UpdateWrapper<WarehouseType> wrapper1 = new UpdateWrapper<>();
        wrapper1.eq(WarehouseManager.WAREHOUSE_ID,warehouseId);
        typeService.remove(wrapper1);
        //删除仓库地区
        UpdateWrapper<WarehouseRegion> wrapper2 = new UpdateWrapper<>();
        wrapper2.eq(WarehouseRegion.WAREHOUSE_ID,warehouseId);
        warehouseRegionService.remove(wrapper2);
        //删除仓库
        this.removeById(warehouseId);
        return Result.success();
    }

    @Override
    public Result<?> page(IPage<WarehouseVo> page, WarehouseDTO map) {
        List<WarehouseVo> page1 = this.baseMapper.page(page, map);
        page.setRecords(page1);
        return Result.success(page);
    }

    @Override
    public void removeProduct(String warehouseId, String productId,Long userId) {
        List<WarehouseProductRelation> wpr = productRelationService.getWarehouseProductRelation(warehouseId,productId);
        this.baseMapper.removeProduct(warehouseId, productId);
        updateLog(userId,wpr);
    }

    private void updateLog( Long userId, List<WarehouseProductRelation> whps) {
        List<StockOperationLog> sols = new ArrayList<>();
        for (WarehouseProductRelation whp:
                whps) {
            StockOperationLog sol = new StockOperationLog();
            sol.setCreateId(userId);
            sol.setCreateTime(LocalDateTime.now());
            sol.setOriginQuantity(0);
            sol.setChangeQuantity(0);
            sol.setDestinationQuantity(0);
            sol.setWarehouseProductRelationId(whp.getWarehouseProductRelationId());
            sols.add(sol);
        }

        stockLogService.saveBatch(sols);
    }

    @Override
    public void updateWarningCount(String warehouseId, String productId, String warningCount) {
        this.baseMapper.updateWarningCount(warehouseId,productId,warningCount);
    }

    @Override
    public int getWarningCount(String warehouseId, String productId) {
        return this.baseMapper.getWarningCount(warehouseId,productId );
    }

    @Override
    public void removeProduct(Integer warehouseId, Integer productId, Long userId) {
        this.baseMapper.removeProduct(warehouseId, productId);
    }

    @Override
    public void updateWarningCount(Integer warehouseId, Integer productId,Integer warningCount) {
        this.baseMapper.updateWarningCount(warehouseId,productId,warningCount);
    }

    @Override
    public int getWarningCount(Integer warehouseId, Integer productId ) {
       return this.baseMapper.getWarningCount(warehouseId,productId  );
    }

    @Override
    public Page listProducts(WarehouseProductPageQuery request) {
        Page page = new Page<>(request.getPageNum(), request.getPageSize());
        List<WarehouseProductPage> list = baseMapper.list(page, request);
        page.setRecords(list);
        return page;
    }

    public static void main(String[] args) {
        List<Warehouse> wList = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            Warehouse warehouse = new Warehouse();
            warehouse.setWarehouseId(i);
            warehouse.setWarehouseName("仓库"+i);
            wList.add(warehouse);
        }
        String [] productName = {"低钠水","恒大冰泉","粮油"};
        List<Product> pList = Lists.newArrayList();
        for (int i = 0; i < 3; i++) {
            Product product = new Product();
            product.setProductId(Long.parseLong(String.valueOf(i)));
            product.setProductName(productName[i]);
            pList.add(product);
        }
        WarehouseProductRelation wp = new WarehouseProductRelation();
        wp.setProductId(1L);
        wp.setWarehouseId(1);
        WarehouseProductRelation wp1 = new WarehouseProductRelation();
        wp.setProductId(1L);
        wp.setWarehouseId(2);
        WarehouseProductRelation wp2 = new WarehouseProductRelation();
        wp.setProductId(1L);
        wp.setWarehouseId(3);
        WarehouseProductRelation wp3 = new WarehouseProductRelation();
        wp.setProductId(2L);
        wp.setWarehouseId(2);
        WarehouseProductRelation wp4 = new WarehouseProductRelation();
        wp.setProductId(2L);
        wp.setWarehouseId(4);
        WarehouseProductRelation wp5 = new WarehouseProductRelation();
        wp.setProductId(2L);
        wp.setWarehouseId(5);
        WarehouseProductRelation wp6 = new WarehouseProductRelation();
        wp.setProductId(3L);
        wp.setWarehouseId(1);
        WarehouseProductRelation wp7 = new WarehouseProductRelation();
        wp.setProductId(3L);
        wp.setWarehouseId(2);
        WarehouseProductRelation wp8 = new WarehouseProductRelation();
        wp.setProductId(3L);
        wp.setWarehouseId(3);
        List<WarehouseProductRelation> wpList = Lists.newArrayList();
        wpList.add(wp);
        wpList.add(wp1);
        wpList.add(wp2);
        wpList.add(wp3);
        wpList.add(wp4);
        wpList.add(wp5);
        wpList.add(wp6);
        wpList.add(wp7);
        wpList.add(wp8);

    }
}