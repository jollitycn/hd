package com.insigma.ordercenter.logistics.zjs.express;

import lombok.Data;

import java.util.List;

/**
 * @program: hdoc-parent
 * @description: 宅急送单号返回
 * @author: XuChao
 * @create: 2020-08-07 17:08
 **/
@Data
public class ZjsOrderResult {

    private String clientFlag;

    private String msg;

    private String status;

    private List<String> data;

}
