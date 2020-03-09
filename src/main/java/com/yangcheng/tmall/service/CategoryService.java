package com.yangcheng.tmall.service;

import com.yangcheng.tmall.pojo.Category;

import java.util.List;
public interface CategoryService {
    List<Category> list();
//    List<Category> list(Page page);
//    int total();
    void add(Category category);
    void delete(Integer id);
    Category get(Integer id);
    void update(Category category);
}
