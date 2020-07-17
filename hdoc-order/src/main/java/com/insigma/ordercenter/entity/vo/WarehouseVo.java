package com.insigma.ordercenter.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    private Long createId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改人id")
    private Long modifyId;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifyTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "联系电话")
    private String mobilePhone;

    private List<SysRegion> regions;

    private List<WarehouseManager> managers;

}
