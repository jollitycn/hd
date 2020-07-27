package com.insigma.ordercenter.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insigma.ordercenter.entity.ExpressCompany;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.vo.ExpressCompanyListVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 物流公司 Mapper 接口
 * </p>
 *
 * @author Jason
 * @since 2020-07-17
 */
public interface ExpressCompanyMapper extends BaseMapper<ExpressCompany> {

    IPage<ExpressCompanyListVO> getCompanyList(Page<ExpressCompanyListVO> page,
                                               @Param("companyName") String companyName,
                                               @Param("companyNo") String companyNo);
}
