package com.leon.biz.sys.service.impl;

import com.leon.biz.sys.entity.TDict;
import com.leon.biz.sys.mapper.TDictMapper;
import com.leon.biz.sys.service.ITDictService;
import com.leon.biz.sys.model.DictVO;
import com.leon.common.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典数据表 服务实现类
 * </p>
 *
 * @author minglei.chen
 * @since 2021-03-17
 */
@Service
public class TDictServiceImpl extends ServiceImpl<TDictMapper, TDict> implements ITDictService {

    @Override
    public IPage<TDict> getPageList(DictVO dictVO) {
        Page<TDict> page=new Page<>(dictVO.getCurPage(),dictVO.getPageSize());
        QueryWrapper queryWrapper = new QueryWrapper();
        if(StringUtil.isNotEmpty(dictVO.getId())){
            queryWrapper.eq("id",dictVO.getId());
        }
        queryWrapper.orderByDesc("id");

        IPage<TDict> result = baseMapper.selectPage(page,queryWrapper);
        return result;
    }
}
