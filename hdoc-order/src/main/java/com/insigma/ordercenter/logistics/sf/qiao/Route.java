package com.insigma.ordercenter.logistics.sf.qiao;

import com.educiot.common.base.BaseVO;
import lombok.Data;

import java.util.Date;

@Data
public class Route extends BaseVO {
    //路由节点发生的时间，格式：YYYY-MM-DD HH24:MM:SS，示例：2012-7-30
    private Date acceptTime;
    //路由节点发生的地点
    private String acceptAddress;
    //路由节点具体描述
    private String remark;
    //路由节点操作
    private String opcode;
}
