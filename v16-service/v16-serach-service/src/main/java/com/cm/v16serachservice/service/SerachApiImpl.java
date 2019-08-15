package com.cm.v16serachservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.filter.tps.TPSLimiter;
import com.cm.v16.api.ISerachApi;
import com.cm.v16.common.pojo.PageResultBean;
import com.cm.v16.common.pojo.ResultBean;
import com.cm.v16.entity.TProduct;
import com.cm.v16.mapper.TProductMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CaoMeng
 * @Date 2019-08-08
 */
@Service
public class SerachApiImpl implements ISerachApi{

    @Autowired
    private TProductMapper productMapper;

    @Autowired
    private SolrClient client;

    @Override
    public ResultBean synAllData (){       //系统初始化同步信息
        List<TProduct> list=productMapper.listLess();
        for(TProduct product : list){
            SolrInputDocument document=new SolrInputDocument();
            document.setField("id",product.getId());
            document.setField("product_name",product.getName());
            document.setField("product_price",product.getPrice());
            document.setField("product_sale_point",product.getSalePoint());
            document.setField("product_images",product.getImage());
            try{
                client.add(document);
            }catch(SolrServerException | IOException e){
                e.printStackTrace();
                return  new ResultBean("500","同步索引失败");
            }
        }
        try{
            client.commit();
        }catch(SolrServerException |IOException e){
            e.printStackTrace();
            return  new ResultBean("500","同步索引提交失败");
        }
        return  new ResultBean("200","同步成功");
    }

    @Override
    public ResultBean addOrUpdateData (List<HashMap<String,Object>> arr){
        for(int i=0;i<arr.size();i++){
            SolrInputDocument document=new SolrInputDocument();
            for(Map.Entry<String,Object> entry:arr.get(i).entrySet()){
                document.setField(entry.getKey(), entry.getValue());
            }
            try{
                client.add(document);
            }catch(SolrServerException | IOException e){
                e.printStackTrace();
                return  new ResultBean("500","更新索引失败");
            }
        }
        try{
            client.commit();
        }catch(SolrServerException |IOException e){
            e.printStackTrace();
            return  new ResultBean("500","更新索引失败");
        }
        return  new ResultBean("200","更新索引成功");
    }

    @Override
    public ResultBean deletaData (String cause){    //通过ID是精确删除。
        try{
            client.deleteByQuery(cause);
        }catch(SolrServerException | IOException e){
            e.printStackTrace();
            return new ResultBean("500","执行删除索引失败");
        }
        try{
            client.commit();
        }catch(SolrServerException |IOException e){
            e.printStackTrace();
            return new ResultBean("500","提交删除索引失败");
        }
        return new ResultBean("200","删除索引成功");
    }
    
    @Override
    public ResultBean queryData (String keyWords){       //关键字查询
        SolrQuery condition=new SolrQuery();
        if(!StringUtils.isEmpty(keyWords)){
            condition.setQuery("product_name:"+keyWords);
        }else {
            condition.setQuery("product_name:*");
        }
        //开启高亮显示
        condition.setHighlight(true);
        //为谁添加高亮？
        SolrQuery query=condition.addHighlightField("product_name");
        SolrQuery pre=condition.setHighlightSimplePre("<font color='red'>");
        SolrQuery post=condition.setHighlightSimplePost("</font>");


        //返回查询结果(前段展示数据)
        List<TProduct> list=null;
        try{
            QueryResponse response=client.query(condition);
            SolrDocumentList results=response.getResults();
            Map<String,Map<String,List<String>>> highlighting=response.getHighlighting();  //高亮显示

            list=new ArrayList<>(results.size());
            for(int i=0;i<results.size();i++){
                TProduct product=new TProduct();
                product.setId(Long.parseLong( results.get(i).getFieldValue("id").toString()));
                /* product.setName(results.get(i).getFieldValue("product_name").toString());*/
                product.setPrice(Long.parseLong(results.get(i).getFieldValue("product_price").toString()));
                product.setImage(results.get(i).getFieldValue("product_images").toString());

                //设置高亮信息
                Map<String, List<String>> idHighlight = highlighting.get(results.get(i).getFieldValue("id").toString());
                //
                List<String> productNameHighLight = idHighlight.get("product_name");
                //
                if(productNameHighLight != null){
                    product.setName(productNameHighLight.get(0));
                }else{
                    product.setName(results.get(i).getFieldValue("product_name").toString());
                }
                list.add(product);
            }
        }catch(SolrServerException |IOException e){
            e.printStackTrace();
            return  new ResultBean("500","查询有误");
        }
        return new ResultBean("200",list);
    }

    @Override
    public ResultBean addOrUpdateByIdData (Long id){              //更新
        SolrInputDocument document=new SolrInputDocument();
        TProduct product=productMapper.selectByPrimaryKey(id);//拿到该ID数据
        document.setField("id",id);
        document.setField("product_name",product.getName());
        document.setField("product_price",product.getPrice());
        document.setField("product_sale_point",product.getSalePoint());
        document.setField("product_images",product.getImage());
        try{
            client.add(document);
        }catch(SolrServerException e){
            e.printStackTrace();
            return  new ResultBean("500","更新索引失败");
        }catch(IOException e){
            e.printStackTrace();
            return  new ResultBean("500","更新索引提交失败");
        }
        try{
            client.commit();
        }catch(SolrServerException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return  new ResultBean("200","更新索引成功");
    }

    @Override
    public ResultBean deleteByIdData (Long id){        //精确删除Solr库索引
        try{
           client.deleteById(id+"");
        }catch(SolrServerException |IOException e){
            e.printStackTrace();
            return  new ResultBean("500","更新索引失败");
        }
        try{
            client.commit();
        }catch(SolrServerException |IOException e){
            e.printStackTrace();
            return  new ResultBean("500","更新索引提交失败");
        }
        return  new ResultBean("200","更新索引成功");
    }

    @Override
    public PageResultBean<TProduct> getList (String keyWords,int pageNum, int pageSize){
        SolrQuery condition=new SolrQuery();
        SolrQuery start=condition.setStart((pageNum-1)*pageSize+1);
        SolrQuery rows=condition.setRows(pageSize);
        if(!StringUtils.isEmpty(keyWords)){
            condition.setQuery("product_name:"+keyWords);
        }else {
            condition.setQuery("product_name:*");
        }
        //开启高亮显示
        condition.setHighlight(true);
        //为谁添加高亮？
        SolrQuery query=condition.addHighlightField("product_name");
        SolrQuery pre=condition.setHighlightSimplePre("<font color='red'>");
        SolrQuery post=condition.setHighlightSimplePost("</font>");
        //返回查询结果(前段展示数据)
        List<TProduct> list=null;
        PageResultBean pageInfo=new PageResultBean();
        try{
            QueryResponse response=client.query(condition);
            SolrDocumentList results=response.getResults();
            Map<String,Map<String,List<String>>> highlighting=response.getHighlighting();  //高亮显示

            list=new ArrayList<>(results.size());
            for(int i=0;i<results.size();i++){
                TProduct product=new TProduct();
                product.setId(Long.parseLong( results.get(i).getFieldValue("id").toString()));
                /* product.setName(results.get(i).getFieldValue("product_name").toString());*/
                if(results.get(i).getFieldValue("product_price")!=null){
                    product.setPrice(Long.parseLong(results.get(i).getFieldValue("product_price").toString()));
                }
                if(results.get(i).getFieldValue("product_images")!=null){
                    product.setImage(results.get(i).getFieldValue("product_images").toString());
                }
                //设置高亮信息
                Map<String, List<String>> idHighlight = highlighting.get(results.get(i).getFieldValue("id").toString());
                //
                List<String> productNameHighLight = idHighlight.get("product_name");
                //
                if(productNameHighLight != null){
                    product.setName(productNameHighLight.get(0));
                }else{
                    product.setName(results.get(i).getFieldValue("product_name").toString());
                }
                list.add(product);
            }
            pageInfo.setList(list);
        }catch(SolrServerException |IOException e){
            e.printStackTrace();
//            return  new ResultBean("500","查询有误");
        }
//        return new ResultBean("200",pageInfo);
          return pageInfo;
    }
}
