package com.insigma.ordercenter.mapper;

import com.insigma.ordercenter.entity.ExpressFee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.dto.ExpressFeePageDTO;
import  com.insigma.ordercenter.entity.vo.ExpressFeePageVO;
import org.apache.ibatis.annotations.Param;
import java.util.List;
/**
 * <p>
 * 物流报价表 Mapper 接口
 * </p>
 *
 * @author Jason
 * @since 2020-07-30
 */
public interface ExpressFeeMapper extends BaseMapper<ExpressFee> {
List<ExpressFeePageVO> page( @Param("page")  Page<ExpressFeePageVO> page, @Param("request") ExpressFeePageDTO request);
 }
