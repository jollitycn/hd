package com.insigma.ordercenter.entity.vo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "订单详情及仓库列表")
@EqualsAndHashCode(callSuper = true)
public class ExpressCompanyVO extends BaseVO{

    @ApiModelProperty(value = "物流公司ID")
    private Integer expressCompanyId;

    @ApiModelProperty(value = "物流公司名称")
    private String companyName;

    @ApiModelProperty(value = "物流公司编码")
    private String companyNo;


}