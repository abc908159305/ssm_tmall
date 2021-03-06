package com.yangcheng.tmall.service.impl;

import com.yangcheng.tmall.pojo.PropertyExample;
import com.yangcheng.tmall.service.PropertyService;
import com.yangcheng.tmall.mapper.PropertyMapper;
import com.yangcheng.tmall.pojo.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    PropertyMapper propertyMapper;
    @Override
    public void add(Property property) {
        propertyMapper.insert(property);
    }

    @Override
    public void delete(Integer id) {
        propertyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Property property) {
        propertyMapper.updateByPrimaryKeySelective(property);
    }

    @Override
    public Property get(Integer id) {
        return propertyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List list(Integer cid) {
        PropertyExample example = new PropertyExample();
        example.createCriteria().andCidEqualTo(cid);
        example.setOrderByClause("id desc");
        return propertyMapper.selectByExample(example);
    }
}
