package com.insigma.ordercenter.controller.api;

import com.insigma.ordercenter.entity.dto.best.ResponseDTO;
import com.insigma.ordercenter.entity.dto.best.TmsTracePushDTO;
import com.insigma.ordercenter.entity.dto.best.WmsSoStatusPushDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 百世物流回调的接口
 *
 * @author Pan Juncai
 * @version 1.0
 * @date 2020/7/24 9:42
 */
@Slf4j
@RestController
@RequestMapping("/bestPushApi")
@Api(tags = {"百世推送回调api接口---供百世回调"})
public class BestPushApiController {

    @PostMapping("/wmsSoStatusPush")
    @ApiOperation(value = "出库单状态推送接口", response = WmsSoStatusPushDTO.class)
    public ResponseDTO wmsSoStatusPush(@RequestBody WmsSoStatusPushDTO wmsSoStatusPushDTO) {
        log.info("状态推送开始：入参WmsSoStatusPushDTO = {}", wmsSoStatusPushDTO);
        ResponseDTO response = new ResponseDTO();
        response.setResult(true);
        return response;
    }

    @PostMapping("/tmsTracePush")
    @ApiOperation(value = "物流在途信息推送", response = TmsTracePushDTO.class)
    public ResponseDTO tmsTracePush(@RequestBody TmsTracePushDTO tmsTracePushDTO) {
        log.info("物流在途信息推送开始：入参TmsTracePushDTO = {}", tmsTracePushDTO);
        ResponseDTO response = new ResponseDTO();
        response.setResult(true);
        return response;
    }

}
