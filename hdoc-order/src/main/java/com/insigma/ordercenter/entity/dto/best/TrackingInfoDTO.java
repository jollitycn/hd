package com.insigma.ordercenter.entity.dto.best;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/24 11:02
 */
@Data
public class TrackingInfoDTO implements Serializable {
    private Date timePoint;
    private String description;
    private String location;
}
