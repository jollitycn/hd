package com.insigma.ordercenter.entity.dto.shop;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.insigma.ordercenter.base.BaseRequest;
import com.insigma.ordercenter.entity.Shop;
import com.insigma.ordercenter.entity.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ShopQueryResponse extends Shop {

    @ApiModelProperty(value = "创建人名称")
    private String createName;

    private List<String> warehouseIds;
}
