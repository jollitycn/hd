package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("判断库存，以及店铺发货比例数量")
public class CheckProductListDTO {

    private List<CheckProductDTO> checkProductDTOS;

}
