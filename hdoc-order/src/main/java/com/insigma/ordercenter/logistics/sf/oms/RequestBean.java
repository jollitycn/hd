package com.insigma.ordercenter.logistics.sf.oms;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

@Data
public class RequestBean extends BaseVO {
    public RequestBean(Request request) {
        this.param_json = request;
    }
    private String method;
    private String appId;
    private String v;
    private String appToken;
    private Long timestamp;
    private String userToken;
    private String source;
    private Request param_json;

    @Data
    public static class Request {
        private String msgData;
        private String dataDigest;
        public Request(String msgData, String dataDigest) {
            super();
            this.msgData = msgData;
            this.dataDigest = dataDigest;
        }

//        {
//            "param_json": {
//            "msgData": "加密报文，详细请见加密说明",
//                    "dataDigest": "加签报文，详细请见加签报文"
//        }
//        }
    }
}
