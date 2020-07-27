package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.constant.Constant;
import com.insigma.ordercenter.entity.ExpressCompany;
import com.insigma.ordercenter.entity.LoginUser;
import com.insigma.ordercenter.entity.dto.ExpressCompanyAddDTO;
import com.insigma.ordercenter.entity.dto.ExpressCompanyEditDTO;
import com.insigma.ordercenter.entity.dto.ExpressCompanyListDTO;
import com.insigma.ordercenter.entity.vo.ExpressCompanyListVO;
import com.insigma.ordercenter.mapper.ExpressCompanyMapper;
import com.insigma.ordercenter.service.IExpressCompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 物流公司 服务实现类
 * </p>
 *
 * @author Jason
 * @since 2020-07-17
 */
@Service
public class ExpressCompanyServiceImpl extends ServiceImpl<ExpressCompanyMapper, ExpressCompany> implements IExpressCompanyService {

    @Override
    public IPage<ExpressCompanyListVO> getCompanyList(Page<ExpressCompanyListVO> page, ExpressCompanyListDTO expressCompanyListDTO) {

        String companyName = null;
        if (expressCompanyListDTO.getCompanyName() != null) {
            companyName = StringUtil.addPercent(expressCompanyListDTO.getCompanyName());
        }
        String companyNo = null;
        if (expressCompanyListDTO.getCompanyNo() != null) {
            companyNo = StringUtil.addPercent(expressCompanyListDTO.getCompanyNo());
        }

        return baseMapper.getCompanyList(page, companyName, companyNo);
    }

    @Override
    public boolean add(ExpressCompanyAddDTO expressCompanyAddDTO, LoginUser loginUser) {

        ExpressCompany expressCompany = new ExpressCompany();
        BeanUtils.copyProperties(expressCompanyAddDTO, expressCompany);
        expressCompany.setCreateId(loginUser.getUserId());
        expressCompany.setCreateTime(LocalDateTime.now());
        return save(expressCompany);

    }

    @Override
    public boolean edit(ExpressCompanyEditDTO expressCompanyEditDTO) {

        ExpressCompany expressCompany = getById(expressCompanyEditDTO.getExpressCompanyId());
        if (expressCompany != null) {
            expressCompany.setCompanyName(expressCompanyEditDTO.getCompanyName());
            expressCompany.setCompanyNo(expressCompanyEditDTO.getCompanyNo());
            expressCompany.setRemark(expressCompanyEditDTO.getRemark());
            return updateById(expressCompany);
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Long expressCompanyId) {

        ExpressCompany expressCompany = getById(expressCompanyId);
        if (expressCompany != null) {
            expressCompany.setIsDeleted(Constant.SYS_ONE);
            return updateById(expressCompany);
        } else {
            return false;
        }
    }

    @Override
    public boolean block(Long expressCompanyId) {
        ExpressCompany expressCompany = getById(expressCompanyId);
        if (expressCompany != null) {
            expressCompany.setIsStop(Constant.SYS_ONE);
            return updateById(expressCompany);
        } else {
            return false;
        }
    }

    @Override
    public boolean unblock(Long expressCompanyId) {
        ExpressCompany expressCompany = getById(expressCompanyId);
        if (expressCompany != null) {
            expressCompany.setIsStop(Constant.SYS_ZERO);
            return updateById(expressCompany);
        } else {
            return false;
        }
    }
}
