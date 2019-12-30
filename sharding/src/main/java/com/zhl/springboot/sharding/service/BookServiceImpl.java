package com.zhl.springboot.sharding.service;

/**
 * @author Lenovo
 * @title: BookService
 * @projectName spring-boot-emo
 * @description: TODO
 * @date 2019/12/27 16:19
 */

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhl.springboot.sharding.entity.Book;
import com.zhl.springboot.sharding.mapper.BookMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Macky
 * @Title class BookServiceImpl
 * @Description: TODO
 * @date 2019/7/12 20:47
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {


    @Override
    public List<Book> getBookList() {
        return baseMapper.selectList(Wrappers.<Book>lambdaQuery());
    }

    @Override
    public boolean save(Book book) {
        return super.save(book);
    }
}

