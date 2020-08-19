package com.insigma.ordercenter.service;

import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.entity.LoginUser;
import com.insigma.ordercenter.entity.StrategyProductType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.vo.StrategyProductTypeVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author youwk
 * @since 2020-08-18
 */
public interface IStrategyProductTypeService extends IService<StrategyProductType> {

    List<StrategyProductTypeVO> strategyProductTypeList(Integer paramType);

    Result addStrategyProductType(StrategyProductTypeVO sendReceiveInfoVO);

    Result deleteStrategyProductType(Integer strategyProductTypeId);

    Result getStrategyProductType(Integer strategyProductTypeId);

    Result updateStrategyProductType(StrategyProductTypeVO strategyProductTypeVO);

    Result updateStrategyProductTypeIsStop(StrategyProductTypeVO strategyProductTypeVO);

}
