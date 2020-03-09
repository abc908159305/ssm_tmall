package com.yangcheng.tmall.service;

import com.yangcheng.tmall.pojo.Order;
import com.yangcheng.tmall.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {
    void add(OrderItem oi);
    void delete(int id);
    void update(OrderItem oi);
    OrderItem get(int id);

    void fill(List<Order> os);
    void fill(Order o);

    int getSaleCount(int pid);

    List<OrderItem> listByUser(int uid);
}
