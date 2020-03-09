package com.yangcheng.tmall.comparator;

import com.yangcheng.tmall.pojo.Product;

import java.util.Comparator;
/**
 * 销量比较器
 * */
public class ProductSaleCountComparator implements Comparator<Product>{

    @Override
    public int compare(Product p1, Product p2) {
        return p2.getSaleCount() - p1.getSaleCount();
    }
}
