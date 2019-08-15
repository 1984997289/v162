package com.cm.v16.api;

import com.cm.v16.common.base.IBaseService;
import com.cm.v16.entity.TProduct;
import com.cm.v16.vo.ProductVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author CaoMeng
 * @Date 2019-08-05
 */
public interface IProductService extends IBaseService<TProduct>{

    PageInfo<TProduct> getPage (int startPage, int pageSize);

    Long add (ProductVO productVO);

    int delByIds (Long[] ids);
    //写一些额外的方法
}
