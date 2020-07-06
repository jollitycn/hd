package com.insigma.ordercenter.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.entity.RoleMenuRelation;
import com.insigma.ordercenter.entity.query.AddMenuQuery;
import com.insigma.ordercenter.mapper.RoleMenuRelationMapper;
import com.insigma.ordercenter.service.IRoleMenuRelationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色菜单关联 服务实现类
 * </p>
 *
 * @author LiuHao
 * @since 2020-01-10
 */
@Service
public class RoleMenuRelationServiceImpl extends ServiceImpl<RoleMenuRelationMapper, RoleMenuRelation> implements IRoleMenuRelationService {

    @Override
    public Boolean addRoleMenuList(AddMenuQuery addMenuQuery) {

        if(addMenuQuery.getMenuId().size() == 0){
            return false;
        }
        //先删除，再添加
        baseMapper.deleteByRoleId(addMenuQuery.getRoleId());
        addMenuQuery.getMenuId().forEach(str -> {
            RoleMenuRelation roleMenuRelation=new RoleMenuRelation();
            roleMenuRelation.setMenuId(str);
            roleMenuRelation.setRoleId(addMenuQuery.getRoleId());
            baseMapper.insert(roleMenuRelation);
        });
        return true;
    }
}
