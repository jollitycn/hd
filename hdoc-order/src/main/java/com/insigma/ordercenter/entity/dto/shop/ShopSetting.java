package com.insigma.ordercenter.entity.dto.shop;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.insigma.ordercenter.entity.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ShopSetting extends BaseVO {

    @ApiModelProperty(value = "店铺ID")
    @TableId(value = "shop_id", type = IdType.ID_WORKER)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long shopId;

    @ApiModelProperty(value = "平台接口地址")
    private String urlAddress;

    @ApiModelProperty(value = "请求周期")
    private Integer requestTime;

    @ApiModelProperty(value = "账户")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;
}
