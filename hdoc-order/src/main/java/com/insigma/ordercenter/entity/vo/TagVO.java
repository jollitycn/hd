package com.insigma.ordercenter.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: hdoc-parent
 * @description: 商品标签返回封装类
 * @author: XuChao
 * @create: 2020-07-27 15:24
 **/
@Data
public class TagVO {

    @ApiModelProperty(value = "标签ID")
    private Long tagId;

    @ApiModelProperty(value = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "标签CODE")
    private String tagCode;

    @ApiModelProperty(value = "标签")
    private String tag;
}
