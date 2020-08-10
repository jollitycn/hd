package com.insigma.ordercenter.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/8/10 10:37
 */
 @Data
public class SendParaDTO {

    @ApiModelProperty("发送内容")
    private String content;

    @ApiModelProperty("日期格式：2020-05-09 16:37:15")
    private Date createTime;

    @ApiModelProperty("消息类型为2时必输，短信消息体")
    private SendMqDTO smsMesssageInfo;

    @ApiModelProperty("消息类型:0-OA消息，1-邮件消息，2-短信消息")
    private List<Integer> msgTypes;

    @ApiModelProperty("消息通知主题")
    private String subject;

}
