package com.cm.v16index.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cm.v16.api.IProductTypeService;
import com.cm.v16.entity.TProductType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author CaoMeng
 * @Date 2019-08-08
 */
@Controller
@RequestMapping("index")
public class IndexController{

    @Reference
    private IProductTypeService productTypeService;

    @RequestMapping("home")
    public String home(Model model){
        List<TProductType> productTypeList=productTypeService.list();
        model.addAttribute("list",productTypeList);
        return "index";
    }
}
        