package com.insigma.ordercenter.entity.query;


import com.insigma.ordercenter.base.BaseQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@ApiModel(value = "新增菜单和角色关系实体")
@EqualsAndHashCode(callSuper = true)
public class AddMenuQuery extends BaseQuery {

    private Long roleId;
    private List<Long> menuId;

}
