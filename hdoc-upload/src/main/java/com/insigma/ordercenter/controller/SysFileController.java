package com.insigma.ordercenter.controller;


import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.FileReq;
import com.insigma.ordercenter.service.ISysFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 系统上传文件表 前端控制器
 * </p>
 *
 * @author youwk
 * @since 2020-05-12
 */
@RestController
@RequestMapping("/sysFile")
@Api(tags = "文件上传")
@Slf4j
public class SysFileController extends BaseController {

    @Autowired
    private ISysFileService fileService;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @PutMapping("uploadSingle")
    @ApiOperation("上传单个文件")
    public Result uploadSingle(FileReq req) {
        return fileService.uploadFile(req,redisUser());
    }

    @PutMapping("/send")
    @ApiOperation("发送消息")
    public Result sendKafka(@RequestParam @ApiParam("消息") String  msg) {
        for (int i = 0; i < 20; i++) {
            ListenableFuture listenableFuture = kafkaTemplate.send("hdoc-dev", msg);

            SuccessCallback<SendResult<String, String>> successCallback = result -> {
                //成功业务逻辑
                System.out.println(result);
                log.info("消息发送成功");
            };
            //发送失败回调
            FailureCallback failureCallback = new FailureCallback() {
                @Override
                public void onFailure(Throwable ex) {
                    //失败业务逻辑
                    log.error("消息发送失败..."+ex.getMessage());
                }
            };
            listenableFuture.addCallback(successCallback, failureCallback);
        }
        return Result.success();
    }




}

