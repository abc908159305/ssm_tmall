package com.yangcheng.tmall.service;

import com.yangcheng.tmall.pojo.Property;

import java.util.List;

public interface PropertyService {
    void add(Property property);
    void delete(Integer id);
    void update(Property property);
    Property get(Integer id);
    List list(Integer id);
}
