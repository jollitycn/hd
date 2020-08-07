package com.insigma.ordercenter.service.sf.oms;

import com.insigma.ordercenter.entity.vo.BaseVO;
import lombok.Data;

@Data
public class OutboundResponse extends APIResponse {

    /**
     * code : 200
     * items : {"erpOrder":"ERP00000001","code":"200","errMsg":"接收成功","sfOrderNo":"SF000000000000000001"}
     * message : 接收成功
     */

    private ItemsBean items;



    public ItemsBean getItems() {
        return items;
    }

    public void setItems(ItemsBean items) {
        this.items = items;
    }


    class ItemsBean {
        /**
         * erpOrder : ERP00000001
         * code : 200
         * errMsg : 接收成功
         * sfOrderNo : SF000000000000000001
         */

        private String erpOrder;
        private String code;
        private String errMsg;
        private String sfOrderNo;

        public String getErpOrder() {
            return erpOrder;
        }

        public void setErpOrder(String erpOrder) {
            this.erpOrder = erpOrder;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getErrMsg() {
            return errMsg;
        }

        public void setErrMsg(String errMsg) {
            this.errMsg = errMsg;
        }

        public String getSfOrderNo() {
            return sfOrderNo;
        }

        public void setSfOrderNo(String sfOrderNo) {
            this.sfOrderNo = sfOrderNo;
        }
    }

}
