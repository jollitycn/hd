package com.insigma.ordercenter.mapper;

import com.insigma.ordercenter.entity.SendReceiveInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.vo.SendReceiveInfoVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 收发人信息 Mapper 接口
 * </p>
 *
 * @author AH
 * @since 2020-07-22
 */
public interface SendReceiveInfoMapper extends BaseMapper<SendReceiveInfo> {

    SendReceiveInfoVO getSendReceiveInfo(@Param("orderId") Long orderId);

}
