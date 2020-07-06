package com.insigma.zerocode.controller;


import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.controller.BaseController;
import com.insigma.zerocode.entity.FileReq;
import com.insigma.zerocode.service.ISysFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SysFileController extends BaseController {

    @Autowired
    private ISysFileService fileService;

    @PutMapping("uploadSingle")
    @ApiOperation("上传单个文件")
    public Result uploadSingle(FileReq req) {
        return fileService.uploadFile(req,redisUser());
    }

}

