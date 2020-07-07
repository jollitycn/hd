package com.insigma.ordercenter.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.LoginUser;
import com.insigma.ordercenter.entity.SysFile;
import com.insigma.ordercenter.entity.FileReq;

/**
 * <p>
 * 系统上传文件表 服务类
 * </p>
 *
 * @author youwk
 * @since 2020-05-12
 */
public interface ISysFileService extends IService<SysFile> {

    Result uploadFile(FileReq req, LoginUser redisUser);



}
