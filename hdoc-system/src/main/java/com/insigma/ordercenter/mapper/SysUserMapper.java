package com.insigma.ordercenter.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.entity.SysUser;
import com.insigma.ordercenter.entity.vo.sysuservo.SysUserListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author Jason
 * @since 2020-01-09
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    List<SysUserListVO> getSysUserList(Page<SysUserListVO> page, @Param("userAccount") String userAccount,@Param(
    "userName")String userName,@Param("roleId")Long roleId);

    /**
     * 根据账号查询用户信息
     *
     * @param userAccount 用户账号
     * @return com.insi.hd.wsmanager.entity.SysUser
     * @author Pan Juncai
     * @date 2020/1/14 14:39
     */
    SysUser getSysUserByAccount(@Param("userAccount") String userAccount);



}