package com.insigma.ordercenter.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.insigma.ordercenter.base.BaseRequest;
import com.insigma.ordercenter.entity.Product;
import com.insigma.ordercenter.entity.WarehouseProductRelation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class WarehouseProductPage implements Serializable {


    @ApiModelProperty(value = "商品ID")
    @TableId(value = "product_id", type = IdType.ID_WORKER)
    private String productId;

    @ApiModelProperty(value = "商品sku")
    private String productSku;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品价格")
    private String productPrice;

    @ApiModelProperty(value = "折扣价格")
    private String discountPrice;

    @ApiModelProperty(value = "商品规格")
    private String productSpecs;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "是否删除（0：正常，1：已删除）")
    private String isDeleted;

    @ApiModelProperty(value = "商品类型")
    private String productType;

    @ApiModelProperty(value = "上架时间")
    private String putOnTime;

    @ApiModelProperty(value = "副标题")
    private String subTitle;

    @ApiModelProperty(value = "商品描述")
    private String description;

    @ApiModelProperty(value = "是否上架（0：未上架，1：已上架）")
    private String isPutOn;

    @ApiModelProperty(value = "是否是组合商品（0：单品，1：组合）")
    private String isCombo;

    @ApiModelProperty(value = "商品品牌")
    private String brand;

    @ApiModelProperty(value = "物流重量")
    private String shipWeight;

    @ApiModelProperty(value = "商品编码")
    private String productNo;

    @ApiModelProperty(value = "商品nc码")
    private String productNc;

    @ApiModelProperty(value = "货主ID")
    private String orderSourceId;

    @ApiModelProperty(value = "是否启用折扣价（0：否，1：是）")
    private String isDiscount;

    @ApiModelProperty(value = "单位数量")
    private String unitQuantity;

    @ApiModelProperty(value = "重量")
    private String weight;

    @ApiModelProperty(value = "条形码")
    private String qrCode;

    @ApiModelProperty(value = "长宽高")
    private String lengthWidthHeight;



    @ApiModelProperty(value = "库存表ID")
    @TableId(value = "warehouse_product_relation_id", type = IdType.AUTO)
    private String warehouseProductRelationId;

    @ApiModelProperty(value = "仓库ID ")
    private String warehouseId;

//    @ApiModelProperty(value = "商品ID")
//    private Long productId;

    @ApiModelProperty(value = "库存数量")
    private String quantity;

    @ApiModelProperty(value = "优先级")
    private String priority;

//    @ApiModelProperty(value = "商品sku")
//    private String productSku;
}