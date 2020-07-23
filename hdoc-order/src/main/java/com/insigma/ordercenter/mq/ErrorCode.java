package com.insigma.ordercenter.mq;

import java.io.Serializable;

public interface ErrorCode extends Serializable {
  String getCode();
  
  String getMsg();
}
