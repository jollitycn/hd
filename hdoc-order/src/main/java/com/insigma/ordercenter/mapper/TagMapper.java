package com.insigma.ordercenter.mapper;

import com.insigma.ordercenter.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.insigma.ordercenter.entity.vo.TagVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品标签表 Mapper 接口
 * </p>
 *
 * @author Jason
 * @since 2020-07-24
 */
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 查询商品标签列表
     * @param productId
     * @return
     */
    List<TagVO> getTagList(@Param("productId") Long productId);
}
