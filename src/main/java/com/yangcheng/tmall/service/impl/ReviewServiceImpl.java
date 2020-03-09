package com.yangcheng.tmall.service.impl;

import com.yangcheng.tmall.service.ReviewService;
import com.yangcheng.tmall.mapper.ReviewMapper;
import com.yangcheng.tmall.pojo.Review;
import com.yangcheng.tmall.pojo.ReviewExample;
import com.yangcheng.tmall.pojo.User;
import com.yangcheng.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    UserService userService;
    @Override
    public void add(Review review) {
        reviewMapper.insert(review);
    }

    @Override
    public void delete(int id) {
        reviewMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Review review) {
        reviewMapper.updateByPrimaryKeySelective(review);
    }

    @Override
    public Review get(int id) {
        return reviewMapper.selectByPrimaryKey(id);
    }

    @Override
    public List list(int pid) {
        ReviewExample example = new ReviewExample();
        example.createCriteria().andPidEqualTo(pid);
        example.setOrderByClause("id desc");

        List<Review> result = reviewMapper.selectByExample(example);
        setUser(result);
        return result;
    }

    public void setUser(List<Review> reviews){
        for (Review review : reviews){
            setUser(review);
        }
    }

    public void setUser(Review review){
        User user = userService.get(review.getUid());
        review.setUser(user);
    }

    @Override
    public int getCount(int pid) {
        return list(pid).size();
    }
}
