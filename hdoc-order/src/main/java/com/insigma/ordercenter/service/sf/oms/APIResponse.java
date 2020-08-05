package com.insigma.ordercenter.service.sf.oms;

import lombok.Data;

import java.io.Serializable;

@Data
public class APIResponse  implements Serializable {
    private String code;
    private String message;


}
