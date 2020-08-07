package com.insigma.ordercenter.entity.dto.shop;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.insigma.ordercenter.base.BaseRequest;
import com.insigma.ordercenter.entity.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("ShopQueryRequest")
public class ShopQueryRequest extends BaseVO {

    @ApiModelProperty(value = "店铺名称")
    private String shopName;
    @ApiModelProperty(value = "所属平台代码")
    private String platformNo;
    @ApiModelProperty(value = "页码编号",example="1")
    private Long pageNum=1L;

    @ApiModelProperty(value = "页码大小",example="10")
    private Long pageSize=10L;
}
