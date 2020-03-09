package com.yangcheng.tmall.service.impl;

import com.yangcheng.tmall.mapper.OrderMapper;
import com.yangcheng.tmall.pojo.Order;
import com.yangcheng.tmall.pojo.OrderExample;
import com.yangcheng.tmall.pojo.OrderItem;
import com.yangcheng.tmall.pojo.User;
import com.yangcheng.tmall.service.OrderItemService;
import com.yangcheng.tmall.service.OrderService;
import com.yangcheng.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserService userService;
    @Autowired
    OrderItemService orderItemService;

    @Override
    public void add(Order c) {
        orderMapper.insert(c);
    }

    @Override
    public float add(Order o, List<OrderItem> ois) {
        float total = 0;
        add(o);
        if (false){
            throw new RuntimeException();
        }
        for (OrderItem oi : ois) {
            oi.setOid(o.getId());
            orderItemService.update(oi);
            total += oi.getProduct().getPromotePrice() * oi.getNumber();
        }
        return total;
    }

    @Override
    public void delete(int id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Order o) {
        orderMapper.updateByPrimaryKeySelective(o);
    }

    @Override
    public Order get(int id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List list() {
        OrderExample example = new OrderExample();
        example.setOrderByClause("id desc");
        List<Order> result = orderMapper.selectByExample(example);
        setUser(result);
        return result;
    }

    @Override
    public List list(int uid, String excludedStatus) {
        OrderExample example = new OrderExample();
        example.createCriteria().andUidEqualTo(uid).andStatusNotEqualTo(excludedStatus);
        example.setOrderByClause("id desc");
        List<Order> result = orderMapper.selectByExample(example);
        return result;
    }

    public void setUser(List<Order> os) {
        for (Order o : os){
            setUser(o);
        }
    }
    public void setUser(Order o) {
        int uid = o.getUid();
        User u = userService.get(o.getUid());
        o.setUser(u);
    }
}
