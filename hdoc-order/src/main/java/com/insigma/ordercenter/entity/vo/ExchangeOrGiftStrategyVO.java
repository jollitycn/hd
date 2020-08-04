package com.insigma.ordercenter.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/29 16:19
 */
@Data
@ApiModel("换货or赠品策略信息返回实体VO")
@EqualsAndHashCode(callSuper = true)
public class ExchangeOrGiftStrategyVO extends BaseVO {
    @ApiModelProperty(value = "ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "策略id")
    private Long strategyId;

    @ApiModelProperty(value = "主题")
    private String theme;

    @ApiModelProperty(value = "开始日期")
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate startDate;

    @ApiModelProperty(value = "结束日期")
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate endDate;

    @ApiModelProperty(value = "是否停用（0：未停用，1：已停用）")
    private Integer isStop;

    @ApiModelProperty(value = "原商品id")
    private Long oldProductId;

    @ApiModelProperty(value = "新商品id")
    private Long newProductId;

    @ApiModelProperty(value = "原商品名称")
    private String oldProductName;

    @ApiModelProperty(value = "新商品名称")
    private String newProductName;

    @ApiModelProperty(value = "店铺列表")
    private List<ShopListVO> shopIdList;

    @ApiModelProperty(value = "旧商品编码")
    private String oldProductNo;

    @ApiModelProperty(value = "新商品编码")
    private String newProductNo;

    @ApiModelProperty(value = "店铺名称")
    private String shopName;
}
