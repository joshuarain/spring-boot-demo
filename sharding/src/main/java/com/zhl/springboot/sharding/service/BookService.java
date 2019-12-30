package com.zhl.springboot.sharding.service;

import com.zhl.springboot.sharding.entity.Book;

import java.util.List;

/**
 * @author Lenovo
 * @title: BookService
 * @projectName spring-boot-emo
 * @description: TODO
 * @date 2019/12/27 16:27
 */
public interface BookService {
    List<Book> getBookList();
}
