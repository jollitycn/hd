package com.insigma.ordercenter.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.base.BaseRequest;
import com.insigma.ordercenter.entity.SysRole;
import com.insigma.ordercenter.entity.vo.SysRoleVO;
import com.insigma.ordercenter.entity.vo.UserRoleVO;

import java.util.List;

/**
 * <p>
 * 系统角色 服务类
 * </p>
 *
 * @author Jason
 * @since 2020-01-09
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 查询角色下所有的人员信息
     * @param
     * @return
     */
    IPage<SysRoleVO> queryByRoleId(IPage<SysRoleVO> page, BaseRequest request);

    /**
     * 获取用户角色信息
     * @param userId
     * @return
     */
    List<UserRoleVO> getUserRoleList(Long userId);
}
