package com.yangcheng.tmall.service;

import com.yangcheng.tmall.pojo.User;

import java.util.List;

public interface UserService {
    void add(User user);
    void delete(int id);
    void update(User user);
    User get(int id);
    List list();
    boolean isExist(String user);
    User get(String name,String password);
}
