package com.yangcheng.tmall.mapper;

import com.yangcheng.tmall.pojo.PropertyValue;
import com.yangcheng.tmall.pojo.PropertyValueExample;

import java.util.List;

public interface PropertyValueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PropertyValue record);

    int insertSelective(PropertyValue record);

    List<PropertyValue> selectByExample(PropertyValueExample example);

    PropertyValue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PropertyValue record);

    int updateByPrimaryKey(PropertyValue record);
}