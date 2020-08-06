package com.insigma.ordercenter.entity.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/29 19:19
 */
@Data
@ApiModel("保存赠品策略的参数实体")
public class AddGiftStrategyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "赠品策略id")
    private Long giftStrategyId;

    @ApiModelProperty(value = "策略id", required = true)
    private Long strategyId;

    @ApiModelProperty(value = "赠品主题", required = true)
    private String theme;

    @ApiModelProperty(value = "开始日期", required = true)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;

    @ApiModelProperty(value = "结束日期", required = true)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;

    @ApiModelProperty(value = "关联的店铺集合")
    private List<Long> shopIdList;

    @Valid
    @ApiModelProperty(value = "赠品信息集合", required = true)
    private List<AddGiftStrDTO> giftList;
}
