package com.insigma.ordercenter.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.insigma.ordercenter.entity.Product;
import com.insigma.ordercenter.entity.SysRegion;
import com.insigma.ordercenter.entity.WarehouseProductRelation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
