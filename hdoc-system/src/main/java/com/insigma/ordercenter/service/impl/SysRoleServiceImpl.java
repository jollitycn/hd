package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.base.BaseRequest;
import com.insigma.ordercenter.entity.SysRole;
import com.insigma.ordercenter.entity.vo.SysRoleVO;
import com.insigma.ordercenter.mapper.SysRoleMapper;
import com.insigma.ordercenter.service.ISysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统角色 服务实现类
 * </p>
 *
 * @author LiuHao
 * @since 2020-01-09
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    public IPage<SysRoleVO> queryByRoleId(IPage<SysRoleVO> page, BaseRequest request) {
        List<SysRoleVO> sysRoleVOS = baseMapper.queryByRoleId(page, request.getId());
        page.setRecords(sysRoleVOS);
        return page;
    }

}
