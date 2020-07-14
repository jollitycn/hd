package com.insigma.ordercenter.entity;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.io.Serializable;
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 系统地区表
    * </p>
*
* @author LiuHao
* @since 2020-07-14
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @ApiModel(value="SysRegion对象", description="系统地区表")
    public class SysRegion implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "地区ID")
            @TableId(value = "region_id", type = IdType.ID_WORKER)
    private Long regionId;

            @ApiModelProperty(value = "父ID")
    private Long parentId;

            @ApiModelProperty(value = "简称")
    private String shortName;

            @ApiModelProperty(value = "名称")
    private String name;

            @ApiModelProperty(value = "全称")
    private String mergerName;

            @ApiModelProperty(value = "层级（1：省，2：市，3：区县）")
    private Integer level;

            @ApiModelProperty(value = "拼音")
    private String pinyin;

            @ApiModelProperty(value = "长途区号")
    private String code;

            @ApiModelProperty(value = "邮编")
    private String zipCode;


        public static final String REGION_ID = "region_id";

        public static final String PARENT_ID = "parent_id";

        public static final String SHORT_NAME = "short_name";

        public static final String NAME = "name";

        public static final String MERGER_NAME = "merger_name";

        public static final String LEVEL = "level";

        public static final String PINYIN = "pinyin";

        public static final String CODE = "code";

        public static final String ZIP_CODE = "zip_code";

}
