package com.insigma.ordercenter.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.entity.ExpressCompany;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.LoginUser;
import com.insigma.ordercenter.entity.dto.ExpressCompanyAddDTO;
import com.insigma.ordercenter.entity.dto.ExpressCompanyEditDTO;
import com.insigma.ordercenter.entity.dto.ExpressCompanyListDTO;
import com.insigma.ordercenter.entity.vo.ExpressCompanyListVO;

/**
 * <p>
 * 物流公司 服务类
 * </p>
 *
 * @author Jason
 * @since 2020-07-17
 */
public interface IExpressCompanyService extends IService<ExpressCompany> {

    IPage<ExpressCompanyListVO> getCompanyList(Page<ExpressCompanyListVO> page, ExpressCompanyListDTO expressCompanyListDTO);

    boolean add(ExpressCompanyAddDTO expressCompanyAddDTO, LoginUser loginUser);

    boolean edit(ExpressCompanyEditDTO expressCompanyEditDTO);

    boolean delete(Long expressCompanyId);

    boolean block(Long expressCompanyId);

    boolean unblock(Long expressCompanyId);
}
