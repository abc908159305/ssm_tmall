package com.yangcheng.tmall.comparator;

import com.yangcheng.tmall.pojo.Product;

import java.util.Comparator;
/**
 * 日期比较器
 * */
public class ProductDateComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getCreateDate().compareTo(p1.getCreateDate());
    }
}
