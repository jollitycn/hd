package com.insigma.ordercenter.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/8/5 13:32
 */
@Data
@ApiModel("赠品策略信息返回实体VO")
public class GiftStrategyInfoVO {

    @ApiModelProperty(value = "赠品策略id")
    private Long giftStrategyId;

    @ApiModelProperty(value = "赠品主题")
    private String theme;

    @ApiModelProperty(value = "开始日期")
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate startDate;

    @ApiModelProperty(value = "结束日期")
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate endDate;

    @ApiModelProperty(value = "店铺列表")
    private List<ShopListVO> shopIdList;

    @ApiModelProperty(value = "店铺名稱")
    private String shopName;

    @ApiModelProperty(value = "赠品列表")
    private List<GiftListVO> giftListVOS;
}
