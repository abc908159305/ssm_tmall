package com.yangcheng.tmall.service.impl;

import com.yangcheng.tmall.pojo.Product;
import com.yangcheng.tmall.mapper.OrderItemMapper;
import com.yangcheng.tmall.pojo.Order;
import com.yangcheng.tmall.pojo.OrderItem;
import com.yangcheng.tmall.pojo.OrderItemExample;
import com.yangcheng.tmall.service.OrderItemService;
import com.yangcheng.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderItemServiceImpl implements OrderItemService{
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    ProductService productService;
    private OrderItem product;

    @Override
    public void add(OrderItem oi) {
        orderItemMapper.insert(oi);
    }

    @Override
    public void delete(int id) {
        orderItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(OrderItem oi) {
        orderItemMapper.updateByPrimaryKeySelective(oi);
    }

    @Override
    public OrderItem get(int id) {
        OrderItem result = orderItemMapper.selectByPrimaryKey(id);
        setProduct(result);
        return result;
    }

    public List<OrderItem> list(){
        OrderItemExample example = new OrderItemExample();
        example.setOrderByClause("id desc");
        return orderItemMapper.selectByExample(example);
    }

    @Override
    public void fill(List<Order> os) {
        for (Order o : os){
            fill(o);
        }
    }

    @Override
    public void fill(Order o) {
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andOidEqualTo(o.getId());
        example.setOrderByClause("id desc");
        List<OrderItem> ois = orderItemMapper.selectByExample(example);
        setProduct(ois);

        float total = 0;
        int totalNumber = 0;
        for (OrderItem oi : ois){
            total += oi.getNumber() * oi.getProduct().getPromotePrice();
            totalNumber += oi.getNumber();
        }
        o.setTotal(total);
        o.setTotalNumber(totalNumber);
        o.setOrderItems(ois);
    }

    @Override
    public int getSaleCount(int pid) {
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andPidEqualTo(pid);
        List<OrderItem> ois = orderItemMapper.selectByExample(example);
        int result = 0;
        for (OrderItem oi : ois){
            result += oi.getNumber();
        }
        return result;
    }

    @Override
    public List<OrderItem> listByUser(int uid) {
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andUidEqualTo(uid).andOidIsNull();
        List<OrderItem> result = orderItemMapper.selectByExample(example);
        setProduct(result);
        return result;
    }

    public void setProduct(OrderItem oi) {
        Product p = productService.get(oi.getPid());
        oi.setProduct(p);
    }

    public void setProduct(List<OrderItem> ois) {
        for (OrderItem oi : ois){
            setProduct(oi);
        }
    }
}
