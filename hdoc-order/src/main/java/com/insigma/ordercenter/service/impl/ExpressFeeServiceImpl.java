package com.insigma.ordercenter.service.impl;

import com.insigma.ordercenter.entity.ExpressFee;
import com.insigma.ordercenter.mapper.ExpressFeeMapper;
import com.insigma.ordercenter.service.IExpressFeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.entity.dto.ExpressFeeSaveDTO;
import com.insigma.ordercenter.entity.dto.ExpressFeePageDTO;
import com.insigma.ordercenter.entity.vo.ExpressFeeDetailVO;
import com.insigma.ordercenter.entity.vo.ExpressFeePageVO;
import org.springframework.stereotype.Service;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 物流报价表 服务实现类
 * </p>
 *
 * @author Jason
 * @since 2020-07-30
 */
@Service
public class ExpressFeeServiceImpl extends ServiceImpl<ExpressFeeMapper, ExpressFee> implements IExpressFeeService {

@Override
public  ExpressFeeDetailVO detail(Serializable id) {
 ExpressFeeDetailVO vo = new  ExpressFeeDetailVO();
 ExpressFee bean = baseMapper.selectById(id);
BeanUtil.copyProperties(bean,vo);
return vo;
}



 @Override
public boolean delete(Serializable id) {
return baseMapper.deleteById(id)>0;
}

 @Override
 public boolean deletes(Serializable ... ids  ) {
 return true;
 }
@Override
public boolean add( ExpressFeeSaveDTO bean) {
return baseMapper.insert(bean)>0;
}

@Override
public IPage< ExpressFeePageVO> page( ExpressFeePageDTO request) {
 Page< ExpressFeePageVO> page = new Page< ExpressFeePageVO>(request.getPageNum(), request.getPageSize());
   List< ExpressFeePageVO> result = baseMapper.page(page, request);
    page.setRecords(result);
    return page;
    }

    @Override
    public boolean edit( ExpressFeeSaveDTO bean) {
    return    baseMapper.updateById(bean)>0;
    }
}
