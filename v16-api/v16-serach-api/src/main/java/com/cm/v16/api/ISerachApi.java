package com.cm.v16.api;

import com.cm.v16.common.pojo.PageResultBean;
import com.cm.v16.common.pojo.ResultBean;
import com.cm.v16.entity.TProduct;

import java.util.HashMap;
import java.util.List;

/**
 * @author CaoMeng
 * @Date 2019-08-08
 */
public interface ISerachApi{
    ResultBean synAllData();
    ResultBean addOrUpdateData(List<HashMap<String,Object>> arr);
    ResultBean deletaData(String cuase);
    ResultBean queryData(String keyWords);          //实现高亮展示关键字
    ResultBean addOrUpdateByIdData(Long id);
    ResultBean deleteByIdData(Long id);


    PageResultBean getList (String keyWords, int pageNum, int pageSize);
}
