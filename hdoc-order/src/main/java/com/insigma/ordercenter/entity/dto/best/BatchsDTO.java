package com.insigma.ordercenter.entity.dto.best;

import com.insigma.ordercenter.logistics.best.sdk.wmsSkuInventoryExtQuery.response.Batch;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/24 10:34
 */
 @Data
public class BatchsDTO implements Serializable {
    private List<BatchDTO> batch;
}
