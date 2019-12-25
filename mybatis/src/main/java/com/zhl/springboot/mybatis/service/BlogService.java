package com.zhl.springboot.mybatis.service;

import com.zhl.springboot.mybatis.mapper.BlogMapper;
import com.zhl.springboot.mybatis.model.Blog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Lenovo
 * @date 2019/12/20 15:33
 */
@Service
public class BlogService {
    @Resource
    private BlogMapper blogMapper;

    public Blog getOne(){
        return blogMapper.select();
    }
}
