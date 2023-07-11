package com.leon.biz.sys.service;

import com.leon.biz.sys.entity.TDict;
import com.leon.biz.sys.model.DictVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 字典数据表 服务类
 * </p>
 *
 * @author minglei.chen
 * @since 2021-03-17
 */
public interface ITDictService extends IService<TDict> {

    IPage<TDict> getPageList(DictVO dictVO);
}
