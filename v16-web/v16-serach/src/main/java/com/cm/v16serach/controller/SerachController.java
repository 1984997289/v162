package com.cm.v16serach.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cm.v16.api.ISerachApi;
import com.cm.v16.common.pojo.PageResultBean;
import com.cm.v16.common.pojo.ResultBean;
import com.cm.v16.entity.TProduct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author CaoMeng
 * @Date 2019-08-09
 */
@Controller
@RequestMapping("serach")
public class SerachController{

    @Reference
    private ISerachApi serachApi;
    
    @RequestMapping("ByKeyWords")
    public String ByKeyWords(@RequestParam(value = "keyWords") String keyWords, Model model){
        ResultBean resultBean=serachApi.queryData(keyWords);
        List<TProduct> list=(List<TProduct>) resultBean.getData();
        model.addAttribute("list",list);
        return "serach";
    }

    @RequestMapping("SplitByKeyWords/{keyWords}/{pageNum}/{pageSize}")
    public String SplitPageByKeyWords(Model model,@PathVariable("keyWords") String keyWords,@PathVariable("pageNum") int pageNum,@PathVariable("pageSize") int pageSize){
        PageResultBean pageResultBean=serachApi.getList(keyWords,pageNum,pageSize);
        List<TProduct> list=pageResultBean.getList() ;
        pageResultBean.setPageNum(pageNum);
        pageResultBean.setPageSize(pageSize);
        model.addAttribute("keyWords",keyWords);
        model.addAttribute("pageInfo",pageResultBean);
        model.addAttribute("list",list);
        return "serach";
        
    }
}
