package com.yangcheng.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangcheng.tmall.service.PropertyService;
import com.yangcheng.tmall.pojo.Category;
import com.yangcheng.tmall.pojo.Property;
import com.yangcheng.tmall.service.CategoryService;
import com.yangcheng.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class PropertyController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    PropertyService propertyService;

    @RequestMapping("admin_property_list")
    public String list(Integer cid, Model model, Page page){
        Category category = categoryService.get(cid);

        List<Property> ps = propertyService.list(cid);
        PageHelper.offsetPage(page.getStart(),page.getCount());

        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        page.setParam("$cid=" + category.getId());

        model.addAttribute("ps",ps);
        model.addAttribute("c",category);
        model.addAttribute("page",page);
        return "admin/listProperty";
    }

    @RequestMapping("admin_property_add")
    public String add(Model model,Property property){
        propertyService.add(property);
        return "redirect:admin_property_list?cid=" + property.getCid();
    }

    @RequestMapping("admin_property_edit")
    public String edit(Model model,Integer id){
        Property property = propertyService.get(id);
        Category c = categoryService.get(property.getCid());
        property.setCategory(c);
        model.addAttribute("p",property);
        return "admin/editProperty";
    }

    @RequestMapping("admin_property_update")
    public String update(Property p){
        propertyService.update(p);
        return "redirect:admin_property_list?cid=" + p.getCid();
    }

    @RequestMapping("admin_property_delete")
    public String delete(Integer id){
        Property p = propertyService.get(id);
        propertyService.delete(id);
        return "redirect:admin_property_list?cid=" + p.getCid();
    }
}
