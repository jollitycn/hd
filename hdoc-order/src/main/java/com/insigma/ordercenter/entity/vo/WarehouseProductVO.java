package com.insigma.ordercenter.entity.vo;

import com.insigma.ordercenter.entity.Product;
import com.insigma.ordercenter.entity.SysRegion;
import com.insigma.ordercenter.entity.WarehouseProductRelation;
import lombok.Data;

import java.io.Serializable;

/**
 * @author youwk
 * @ClassName WarehouseVo
 * @description TODO
 * @date 2020/7/17 9:37
 * @Version 1.0
 */
@Data
public class WarehouseProductVO extends WarehouseProductRelation implements Serializable {
    private Product product;
}
