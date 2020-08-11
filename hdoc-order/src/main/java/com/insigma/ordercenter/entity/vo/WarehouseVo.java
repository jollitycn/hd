package com.insigma.ordercenter.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.insigma.ordercenter.constant.Constant;
import com.insigma.ordercenter.entity.SysRegion;
import com.insigma.ordercenter.entity.WarehouseManager;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author youwk
 * @ClassName WarehouseVo
 * @description TODO
 * @date 2020/7/17 9:37
 * @Version 1.0
 */
@Data
public class WarehouseVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "仓库ID ")
    private Integer warehouseId;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "区县")
    private String district;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "区域ID")
    private Integer regionId;

    @ApiModelProperty(value = "纬度")
    private Double latitude;

    @ApiModelProperty(value = "经度")
    private Double longitude;

    @ApiModelProperty(value = "仓库编码")
    private String warehouseNo;

    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;

    @ApiModelProperty(value = "创建人id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createId;

    @ApiModelProperty(value = "创建时间")
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改人id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long modifyId;

    @ApiModelProperty(value = "修改时间")
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime modifyTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "联系电话")
    private String mobilePhone;

    @ApiModelProperty("货主编号")
    private Integer orderSourceId;

    @ApiModelProperty("货主名称")
    private String orderSourceName;

    @ApiModelProperty("承运商编号")
    private Integer expressCompanyId;

    @ApiModelProperty("承运商名称")
    private String expressCompanyName;

    private List<SysRegion> regions;

    @ApiModelProperty(value = "联系电话")
    private String managerName;


    @ApiModelProperty("商品分类")
    private Integer[] productTypes;

}
