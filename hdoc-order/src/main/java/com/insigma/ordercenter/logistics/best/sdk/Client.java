package com.insigma.ordercenter.logistics.best.sdk;

import java.util.HashMap;
import java.util.Map;

public class Client {

    private String url;
    private String partnerID;
    private String partnerKey;
    private String messageFormat;

    public Client(String url, String partnerID, String partnerKey, String messageFormat){
        this.url = url;
        this.messageFormat = messageFormat;
        this.partnerID = partnerID;
        this.partnerKey = partnerKey;

    }

    public <T extends BaseResponse> T executed(BaseRequest baseRequest) {
        String response = "";
        Map<String, String> params = new HashMap<>();
        Param param = new Param();
		param.setServiceType(baseRequest.obtainServiceType());
		param.setBizData(makeBizData(baseRequest));
		param.setPartnerID(partnerID);
		param.setPartnerKey(partnerKey);

		params.put("serviceType", param.getServiceType());
		params.put("bizData", param.getBizData());
		params.put("sign", Sign.makeSign(param));
		params.put("partnerID", param.getPartnerID());
        try {
            response = HttpService.sendPost(url, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response == null ? null : (T) baseRequest.makeResponse(response, messageFormat);
    }

    private String makeBizData(BaseRequest baseRequest) {
        if("xml".equalsIgnoreCase(this.messageFormat)) {
            return Parser.coverObject2Xml(baseRequest);
        }
        return  Parser.convertObject2Json(baseRequest);
    }
}