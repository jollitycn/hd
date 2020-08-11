package com.insigma.ordercenter.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.insigma.ordercenter.entity.SysRole;
import com.insigma.ordercenter.entity.vo.SysRoleVO;
import com.insigma.ordercenter.entity.vo.UserRoleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统角色 Mapper 接口
 * </p>
 *
 * @author Jason
 * @since 2020-01-09
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 查询角色下所有的人员信息
     * @param id 角色Id
     * @return
     */
    List<SysRoleVO> queryByRoleId(IPage<SysRoleVO> page, @Param("id")Long id);

    /**
     * 获取用户角色信息
     * @param userId
     * @return
     */
    List<UserRoleVO> getUserRoleList(@Param("userId")Long userId);
}
