package com.insigma.ordercenter.entity.dto.shop;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.insigma.ordercenter.constant.Constant;
import com.insigma.ordercenter.entity.Shop;
import com.insigma.ordercenter.entity.ShopWarehouse;
import com.insigma.ordercenter.entity.vo.BaseVO;
import com.insigma.ordercenter.utils.DataUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ShopEdit extends BaseVO {


    @ApiModelProperty(value = "店铺ID")
    @TableId(value = "shop_id", type = IdType.ID_WORKER)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long shopId;

    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "所属平台名称")
    private String platformName;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "所属平台代码")
    private String platformNo;


    @ApiModelProperty(value = "cName")
    private String cName;
    @ApiModelProperty(value = "cPhone")
    private String cPhone;
    @ApiModelProperty(value = "sAddr")
    private String sAddr;

    private List<String> warehouseIds;

}
