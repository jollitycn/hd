package com.insigma.ordercenter.entity.dto.best;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/24 10:31
 */
 @Data
public class ProductsDTO implements Serializable {
    private List<ProductDTO> product;
}
