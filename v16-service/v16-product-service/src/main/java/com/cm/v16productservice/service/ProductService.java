package com.cm.v16productservice.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.cm.v16.api.IProductService;
import com.cm.v16.api.ISerachApi;
import com.cm.v16.common.base.BaseServiceImpl;
import com.cm.v16.common.base.IBaseDao;
import com.cm.v16.entity.TProduct;
import com.cm.v16.entity.TProductDesc;
import com.cm.v16.mapper.TProductDescMapper;
import com.cm.v16.mapper.TProductMapper;
import com.cm.v16.vo.ProductVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author CaoMeng
 * @Date 2019-08-05
 */
@Service
@Transactional//事务控制
public class ProductService extends BaseServiceImpl<TProduct> implements IProductService{

    @Autowired
    private TProductMapper productMapper;

    @Autowired
    private TProductDescMapper productDescMapper;

    @Override
    public IBaseDao<TProduct> getDao (){
        return productMapper;
    }

    @Override
    public List<TProduct> list (){
        return productMapper.list();
    }

    @Override
    public PageInfo<TProduct> getPage (int startPage, int pageSize){
        PageHelper.startPage(startPage, pageSize);
        List<TProduct> productList=productMapper.list();
        //navigatePages显示的分页点击有几个，
        PageInfo<TProduct> pageInfo=new PageInfo<TProduct>(productList,4);
        return pageInfo;
    }

    @Override
    public Long add (ProductVO productVO){
        TProduct product=productVO.getProduct();
        Long id=Long.valueOf(productMapper.insertSelective(product));  //添加到数据库数据

        TProductDesc productDesc=new TProductDesc();          //添加描述到数据库
        productDesc.setProductDesc(productVO.getProductDesc());
        productDesc.setProductId(product.getId());
        productDescMapper.insertSelective(productDesc);

       /* //更新索引到Solr服务器
        serachApi.addOrUpdateByIdData(id);*/
        return product.getId();
    }

    @Override
    public int delByIds (Long[] ids){
        int result=productMapper.updateByIds(ids);
        return  result;
    }



    @Override
    public int deleteByPrimaryKey (Long id){
        TProduct product=new TProduct();
        product.setId(id);
        product.setFlag(false);
        product.setUpdateTime(new Date());
        return productMapper.updateByPrimaryKeySelective(product);
    }
}
