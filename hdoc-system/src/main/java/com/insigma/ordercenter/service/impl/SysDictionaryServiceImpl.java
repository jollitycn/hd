package com.insigma.ordercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.insigma.ordercenter.base.CodeMsg;
import com.insigma.ordercenter.base.Result;
import com.insigma.ordercenter.constant.Constant;
import com.insigma.ordercenter.entity.SysDictionary;
import com.insigma.ordercenter.entity.vo.SysDictVO;
import com.insigma.ordercenter.mapper.SysDictionaryMapper;
import com.insigma.ordercenter.service.ISysDictionaryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 系统数字字典 服务实现类
 * </p>
 *
 * @author Jason
 * @since 2020-01-09
 */
@Service
public class SysDictionaryServiceImpl extends ServiceImpl<SysDictionaryMapper, SysDictionary> implements ISysDictionaryService {


    @Override
    public Result<?> deleteDictionary(Long dictionaryId) {

        QueryWrapper<SysDictionary> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", dictionaryId);
        wrapper.eq("is_deleted", Constant.SYS_ZERO);
        int count = count(wrapper);
        if (count > 0) {
            return Result.error(CodeMsg.DIC_DELETE_ERROR);
        } else {
            wrapper = new QueryWrapper<>();
            wrapper.eq("dictionary_id", dictionaryId);
            SysDictionary sysDictionary = getOne(wrapper);
            // 判断商品中是否有使用
//            long countProduct = productMapper.countProductByDictionary(sysDictionary.getDataCode());
//            if (countProduct != 0) {
//                return Result.error(CodeMsg.DICTIONARY_IN_USING);
//            }
            sysDictionary.setIsDeleted(Constant.SYS_ONE);
            boolean status = updateById(sysDictionary);
            if (status) {
                return Result.success();
            } else {
                return Result.error(CodeMsg.DATA_DELETE_ERROR);
            }
        }
    }

    @Override
    public List<SysDictVO> getAllDict() {

        //获取一级菜单信息
        QueryWrapper<SysDictionary> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", 0);
        wrapper.eq("is_deleted", Constant.SYS_ZERO);
        List<SysDictionary> sysDictionaries = list(wrapper);

        List<SysDictVO> result = new ArrayList<>();

        for (SysDictionary sysDictionary : sysDictionaries) {
            SysDictVO sysDictVO = new SysDictVO();
            BeanUtils.copyProperties(sysDictionary, sysDictVO);
            result.add(sysDictVO);
        }

        result.forEach(levelOneMenu -> levelOneMenu.setSubs(listChildDict(levelOneMenu.getDictionaryId())));
        return result;
    }

    private List<SysDictVO> listChildDict(Long parentId) {

        QueryWrapper<SysDictionary> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        wrapper.eq("is_deleted", Constant.SYS_ZERO);
        List<SysDictionary> sysDictionaries = list(wrapper);

        List<SysDictVO> child = new ArrayList<>();
        for (SysDictionary sysDictionary : sysDictionaries) {
            SysDictVO sysDictVO = new SysDictVO();
            BeanUtils.copyProperties(sysDictionary, sysDictVO);
            child.add(sysDictVO);
        }

        child.forEach(menu -> menu.setSubs(listChildDict(menu.getDictionaryId())));
        return child;
    }
}
