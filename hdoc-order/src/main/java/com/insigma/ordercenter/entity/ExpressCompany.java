package com.insigma.ordercenter.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 物流公司
 * </p>
 *
 * @author LiuHao
 * @since 2020-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_express_company")
@ApiModel(value = "ExpressCompany对象", description = "物流公司")
public class ExpressCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "物流公司ID")
    @TableId(value = "express_company_id", type = IdType.ID_WORKER)
    private Long expressCompanyId;

    @ApiModelProperty(value = "物流公司名称")
    private String companyName;

    @ApiModelProperty(value = "物流公司编码")
    private String companyNo;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "是否删除（0：未删除，1：已删除）")
    private Integer isDeleted;

    @ApiModelProperty(value = "说明")
    private String remark;

    @ApiModelProperty(value = "是否停用（0：未停用，1：已停用）")
    private Integer isStop;

    @ApiModelProperty(value = "创建人ID")
    private Long createId;


    public static final String EXPRESS_COMPANY_ID = "express_company_id";

    public static final String COMPANY_NAME = "company_name";

    public static final String COMPANY_NO = "company_no";

    public static final String CREATE_TIME = "create_time";

    public static final String IS_DELETED = "is_deleted";

    public static final String REMARK = "remark";

    public static final String IS_STOP = "is_stop";

    public static final String CREATE_ID = "create_id";

}
