package com.yangcheng.tmall.comparator;

import com.yangcheng.tmall.pojo.Product;

import java.util.Comparator;

/**
 * 综合比较器
 * */
public class ProductAllComparator implements Comparator<Product>{

    public int compare(Product p1,Product p2) {
        return p2.getReviewCount() * p2.getSaleCount() - p1.getReviewCount() * p1.getSaleCount();
    }

}
