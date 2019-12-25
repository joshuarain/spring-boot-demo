package com.zhl.springboot.mybatis.controller;

import com.zhl.springboot.mybatis.model.Blog;
import com.zhl.springboot.mybatis.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lenovo
 * @title: BlogController
 * @projectName spring-bootd-emo
 * @description: TODO
 * @date 2019/12/20 15:34
 */
@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("one")
    public Blog one(){
        return blogService.getOne();
    }
}
