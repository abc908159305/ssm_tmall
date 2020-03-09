package com.yangcheng.tmall.comparator;

import com.yangcheng.tmall.pojo.Product;

import java.util.Comparator;

/**
 * 价格比较器
 * */
public class ProductPriceComparator implements Comparator<Product>{
    @Override
    public int compare(Product p1, Product p2) {
        return (int) (p1.getPromotePrice() - p2.getPromotePrice());
    }
}
