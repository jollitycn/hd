package com.insigma.ordercenter.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.io.Serializable;
    import java.time.LocalDateTime;

    import com.fasterxml.jackson.annotation.JsonFormat;
    import com.fasterxml.jackson.databind.annotation.JsonSerialize;
    import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
    import com.insigma.ordercenter.constant.Constant;
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 店铺表
    * </p>
*
* @author Jason
* @since 2020-07-22
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("t_shop")
    @ApiModel(value="Shop对象", description="店铺表")
    public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "店铺ID")
    @TableId(value = "shop_id", type = IdType.ID_WORKER)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long shopId;

    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "所属平台名称")
    private String platformName;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "所属平台代码")
    private String platformNo;

    @ApiModelProperty(value = "创建人id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createId;

    @ApiModelProperty(value = "创建时间")
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "是否停用（0：未停用，1：已停用）")
    private int isStop;

    @ApiModelProperty(value = "平台接口地址")
    private String urlAddress;

    @ApiModelProperty(value = "请求周期")
    private int requestTime;

    @ApiModelProperty(value = "账户")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "是否删除（0：未删除，1：已删除）")
    private int isDeleted;


    public static final String SHOP_ID = "shop_id";

    public static final String PLATFORM_NAME = "platform_name";

    public static final String REMARK = "remark";

    public static final String PLATFORM_NO = "platform_no";

    public static final String CREATE_ID = "create_id";

    public static final String CREATE_TIME = "create_time";

    public static final String IS_STOP = "is_stop";

    public static final String URL_ADDRESS = "url_address";

    public static final String REQUEST_TIME = "request_time";

    public static final String ACCOUNT = "account";

    public static final String PASSWORD = "password";

    public static final String IS_DELETED = "is_deleted";

}
