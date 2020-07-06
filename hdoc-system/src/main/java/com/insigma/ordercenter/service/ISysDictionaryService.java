package com.insigma.ordercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.SysDictionary;
import com.insigma.ordercenter.entity.vo.SysDictVO;

import java.util.List;

/**
 * <p>
 * 系统数字字典 服务类
 * </p>
 *
 * @author LiuHao
 * @since 2020-01-09
 */
public interface ISysDictionaryService extends IService<SysDictionary> {

    Result<?> deleteDictionary(Long dictionaryId);

    List<SysDictVO> getAllDict();
}
