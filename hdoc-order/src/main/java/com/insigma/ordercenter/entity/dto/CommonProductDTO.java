package com.insigma.ordercenter.entity.dto;

import com.insigma.ordercenter.entity.vo.ProductDetailVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: hdoc-parent
 * @description: 通用货物封装类
 * @author: XuChao
 * @create: 2020-08-03 15:47
 **/
@Data
public class CommonProductDTO extends ProductDetailVO {

    @ApiModelProperty(value = "商品列表")
    private List<ProductDetailVO> productList;

}
