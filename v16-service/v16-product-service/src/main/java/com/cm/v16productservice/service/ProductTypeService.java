package com.cm.v16productservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.cm.v16.api.IProductTypeService;
import com.cm.v16.common.base.BaseServiceImpl;
import com.cm.v16.common.base.IBaseDao;
import com.cm.v16.entity.TProductType;
import com.cm.v16.mapper.TProductTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CaoMeng
 * @Date 2019-08-08
 */
@Service
public class ProductTypeService extends BaseServiceImpl<TProductType> implements IProductTypeService{

    @Autowired
    private TProductTypeMapper productTypeMapper;

    @Resource(name="stringRsdisModuel")
    private RedisTemplate<String,Object> redisTemplate;
    
    @Override
    public IBaseDao<TProductType> getDao(){
        return productTypeMapper;
    }

    @Override
    public List<TProductType> list (){
        String key="productType:list";
        List<TProductType> cachelist=(List<TProductType>) redisTemplate.opsForValue().get(key);
        if(cachelist!=null){
            return cachelist;
        }
        System.out.println("从数据库提取数据....");
        List<TProductType> list=super.list();
        if(list!=null){
            redisTemplate.opsForValue().set(key,list);
        }
        return list;
    }
}
