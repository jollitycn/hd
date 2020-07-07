package com.insigma.ordercenter.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author youwk
 * @ClassName 上传文件封装参数类
 * @description
 * @date 2020/5/12 18:02
 * @Version 1.0
 */
@Data
@ApiModel("上传文件封装参数类")
public class FileReq implements Serializable {

    public static final long serialVersionUID = 1L;

    /**
     * 文件内容
     */
    @ApiModelProperty("上传文件")
    private MultipartFile file;

    /**
     * 所属对象编号
     */
    @ApiModelProperty("所属对象编号")
    private Integer belongId;

    /**
     * 所属对象 1-表单应用，2-字段
     */
    @ApiModelProperty("所属对象类型")
    private Integer belongObj;
}
