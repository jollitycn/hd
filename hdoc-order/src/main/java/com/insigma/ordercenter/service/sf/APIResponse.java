package com.insigma.ordercenter.service.sf;

import lombok.Data;

import java.io.Serializable;

@Data
public class APIResponse  implements Serializable {
    private String apiErrorMsg;
    private String apiResponseID;
    private String apiResultCode;
    private Object apiResultData;
    private static final long serialVersionUID = 1L;
}
