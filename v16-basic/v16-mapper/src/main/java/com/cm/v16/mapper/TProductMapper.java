package com.cm.v16.mapper;


import com.cm.v16.common.base.IBaseDao;
import com.cm.v16.entity.TProduct;

import java.util.List;

public interface TProductMapper extends IBaseDao<TProduct>  {

    int updateByIds (Long[] ids);

    List<TProduct> listLess ();
}