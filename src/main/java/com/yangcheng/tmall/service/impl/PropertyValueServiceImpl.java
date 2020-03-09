package com.yangcheng.tmall.service.impl;

import com.yangcheng.tmall.mapper.PropertyValueMapper;
import com.yangcheng.tmall.pojo.Product;
import com.yangcheng.tmall.pojo.PropertyValueExample;
import com.yangcheng.tmall.service.PropertyService;
import com.yangcheng.tmall.service.PropertyValueService;
import com.yangcheng.tmall.pojo.Property;
import com.yangcheng.tmall.pojo.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PropertyValueServiceImpl implements PropertyValueService {
    @Autowired
    PropertyValueMapper propertyValueMapper;
    @Autowired
    PropertyService propertyService;
    @Override
    public void init(Product p) {
        List<Property> pts = propertyService.list(p.getCid());

        for (Property pt : pts){
            PropertyValue pv = get(pt.getId(),p.getId());
            if (null == pv){
                pv = new PropertyValue();
                pv.setPid(p.getId());
                pv.setPtid(pt.getId());
                propertyValueMapper.insert(pv);
            }
        }
    }

    @Override
    public void update(PropertyValue pv) {
        propertyValueMapper.updateByPrimaryKeySelective(pv);
    }

    @Override
    public PropertyValue get(int ptid, int pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPtidEqualTo(ptid).andPidEqualTo(pid);
        List<PropertyValue> pvs = propertyValueMapper.selectByExample(example);
        if (pvs.isEmpty()){
            return null;
        }
        return pvs.get(0);
    }

    @Override
    public List<PropertyValue> list(int pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPidEqualTo(pid);
        List<PropertyValue> pvs = propertyValueMapper.selectByExample(example);
        for (PropertyValue pv : pvs){
            Property property = propertyService.get(pv.getPtid());
            pv.setProperty(property);
        }
        return pvs;
    }
}
