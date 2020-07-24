package com.insigma.ordercenter.entity.dto.best;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/24 10:52
 */
 @Data
public class ResponseDTO implements Serializable {
    private Boolean result;
    private String note;
    private String errorCode;
    private String errorDescription;
}
