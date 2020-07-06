package com.insigma.zerocode.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.LoginUser;
import com.insigma.ordercenter.entity.SysFile;
import com.insigma.zerocode.entity.FileReq;
import com.insigma.zerocode.mapper.SysFileMapper;
import com.insigma.zerocode.service.ISysFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统上传文件表 服务实现类
 * </p>
 *
 * @author youwk
 * @since 2020-05-12
 */
@Service
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile> implements ISysFileService {

    @Value("${zc.fileServer.path}")
    private String fileServerPath;

    @Value("${zc.fileServer.remote-url}")
    private String remoteUrl;

    @Override
    public Result uploadFile(FileReq req, LoginUser redisUser) {
        String fileName = IdWorker.getIdStr() + req.getFile().getOriginalFilename();
        String destFileName = fileServerPath + "/" + fileName;
        File destFile = new File(destFileName);
        destFile.getParentFile().mkdirs();
        String url = remoteUrl + "/" + fileName;
        try {
            req.getFile().transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SysFile sysFile = new SysFile();
        sysFile.setUploadTime(LocalDateTime.now());
        sysFile.setUploadUserId(redisUser.getUserId());
        sysFile.setFileName(fileName);
        sysFile.setFilePath(url);
        sysFile.setFileSize(req.getFile().getSize());
        sysFile.setBelongId(req.getBelongId());
        sysFile.setBelongObj(req.getBelongObj());
        this.baseMapper.insert(sysFile);
        return Result.success(sysFile);
    }
}
