package com.insigma.ordercenter.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.insigma.ordercenter.entity.StockOperationLog;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class StockOperationLogPage extends StockOperationLog {
    private String createName;
    private String operationTypeName;

    public StockOperationLogPage() {
    }

    public String getOperationTypeName() {
        switch (getOperationType()) {
//            操作类型（1-订单发货，2-库存调拨，3-退货）

            case 2:
                operationTypeName = "库存调拨";
                break;
            case 3:
                operationTypeName = "退货";
                break;
            case 4:
                operationTypeName = "手工调整";
                break;
                case 1:    default:
                operationTypeName = "订单发货";
                break;
        }
        return operationTypeName;
    }

    public String getCreateName() {
        return this.createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

//    public void setOperationTypeName(String operationTypeName) {
//        this.operationTypeName = operationTypeName;
//    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof StockOperationLogPage)) return false;
        final StockOperationLogPage other = (StockOperationLogPage) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$createName = this.getCreateName();
        final Object other$createName = other.getCreateName();
        if (this$createName == null ? other$createName != null : !this$createName.equals(other$createName))
            return false;
        final Object this$operationTypeName = this.getOperationTypeName();
        final Object other$operationTypeName = other.getOperationTypeName();
        if (this$operationTypeName == null ? other$operationTypeName != null : !this$operationTypeName.equals(other$operationTypeName))
            return false;
        return true;
    }

    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof StockOperationLogPage;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $createName = this.getCreateName();
        result = result * PRIME + ($createName == null ? 43 : $createName.hashCode());
        final Object $operationTypeName = this.getOperationTypeName();
        result = result * PRIME + ($operationTypeName == null ? 43 : $operationTypeName.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "StockOperationLogPage(createName=" + this.getCreateName() + ", operationTypeName=" + this.getOperationTypeName() + ")";
    }
}
