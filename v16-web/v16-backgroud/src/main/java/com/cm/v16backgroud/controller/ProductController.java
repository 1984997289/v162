package com.cm.v16backgroud.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cm.v16.api.IProductService;
import com.cm.v16.common.pojo.ResultBean;
import com.cm.v16.entity.TProduct;
import com.cm.v16.vo.ProductVO;
import com.github.pagehelper.PageInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.cm.v16.common.constant.RabbitTopicExchange.BACKGROUD_TOPICEXCHANGE;

/**
 * @author CaoMeng
 * @Date 2019-08-05
 */
@Controller
@RequestMapping("product")
public class ProductController{

    @Reference
    private IProductService productService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("list")
    public String getList(Model model){
        List<TProduct> productList=productService.list();
        model.addAttribute("productList",productList);
        return "product/product";
    }

    @RequestMapping("page/{startPage}/{pageSize}")
    public String getPage(@PathVariable(name = "startPage") int startPage, @PathVariable(name = "pageSize") int pageSize,Model model){
        PageInfo<TProduct> pageInfo=productService.getPage(startPage,pageSize);
        model.addAttribute("pageInfo",pageInfo);
        return "product/product";
    }

    @RequestMapping("add")
    public String add(ProductVO productVO){
        Long newId= productService.add(productVO);
        //后续作为通知其他系统做相关操作
        rabbitTemplate.convertAndSend(BACKGROUD_TOPICEXCHANGE,"product.add",newId);
        String msg="快船夺冠了";
        rabbitTemplate.convertAndSend(BACKGROUD_TOPICEXCHANGE,"product.nba",msg);
        return "redirect:/product/page/1/3";
    }

//    @RequestMapping("toUpdate/{id}")
    

    //返回JSON,适合做异步请求
    @RequestMapping("del/{id}")
    @ResponseBody
    public ResultBean del(@PathVariable(name = "id") Long id){
        int count=productService.deleteByPrimaryKey(id);
        if(count>0){
            return new ResultBean("200","删除成功");
        }else {
            return new ResultBean("500","操作有误");
        }
    }

    @RequestMapping("delByIds/{ids}")
    @ResponseBody
    public ResultBean delByIds(@PathVariable(name="ids") Long [] ids){
       int result=productService.delByIds(ids);
       if(result>0){
           return new ResultBean("200","删除成功！");
       }
       return new ResultBean("500","删除失败");
    }
}
