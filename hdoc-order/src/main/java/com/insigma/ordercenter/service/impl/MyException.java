package com.insigma.ordercenter.service.impl;

import com.insigma.ordercenter.base.CodeMsg;
import lombok.Data;

@Data
public class MyException extends Throwable {
    private CodeMsg codeMsg;
    public MyException ( CodeMsg codeMsg ) {
        this.codeMsg = codeMsg;
    }
}
