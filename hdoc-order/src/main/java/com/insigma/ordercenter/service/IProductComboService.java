package com.insigma.ordercenter.service;

import com.insigma.ordercenter.entity.ProductCombo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.insigma.ordercenter.entity.dto.AddComboDTO;
import com.insigma.ordercenter.entity.vo.ProductComboVO;

import java.util.List;

/**
 * <p>
 * 组合信息表 服务类
 * </p>
 *
 * @author Jason
 * @since 2020-07-22
 */
public interface IProductComboService extends IService<ProductCombo> {

    /**
     * 获取商品组合列表
     * @param productId
     * @return
     */
    List<ProductComboVO> getComboList(Long productId);

    /**
     * 删除商品组合列表
     * @param productComboId
     * @return
     */
    boolean deleteCombo(Long productComboId);

    /**
     * 添加商品组合
     * @param productId
     * @param addComboDTOList
     * @return
     */
    boolean addCombo(Long productId, List<AddComboDTO> addComboDTOList);

    /**
     * 编辑商品组合数量
     * @param productComboId
     * @param quantity
     * @return
     */
    boolean editCombo(Long productComboId, Integer quantity);
}
