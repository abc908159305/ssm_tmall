package com.yangcheng.tmall.comparator;

import com.yangcheng.tmall.pojo.Product;

import java.util.Comparator;
/**
 * 评价比较器
 * */
public class ProductReviewComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getReviewCount() - p1.getReviewCount();
    }
}
