package com.cm.v16item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cm.v16.api.IProductService;
import com.cm.v16.api.ISerachApi;
import com.cm.v16.common.pojo.ResultBean;
import com.cm.v16.entity.TProduct;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CaoMeng
 * @Date 2019-08-10
 */
@Controller
@RequestMapping("item")
public class ItemController{

    @Autowired
    private Configuration configuration;

    @Reference
    private IProductService productService;

    @Reference
    private ISerachApi serachApi;

    @RequestMapping("getById/{id}")
    @ResponseBody
    public ResultBean getById(@PathVariable("id") Long id) throws IOException, TemplateException{
        //获取模版对象
        Template template=configuration.getTemplate("item.ftl");
        
        TProduct product=productService.selectByPrimaryKey(id);
        List<TProduct> list=productService.list();
        Map<String,Object> data=new HashMap<>();
        data.put("product",product);
        data.put("list",list);
        //获取static路径
        String path=ResourceUtils.getURL("classpath:static").getPath();

        Writer out=new FileWriter(path+File.separator+id+".html");

        template.process(data,out);
        return new ResultBean("200","读取完成");
    }

    @RequestMapping("list")
    public String list(Model model){
      
        return "item";
    }
    
}
