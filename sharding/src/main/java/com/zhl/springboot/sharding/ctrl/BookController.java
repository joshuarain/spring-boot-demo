package com.zhl.springboot.sharding.ctrl;

/**
 * @author Lenovo
 * @title: BookController
 * @projectName spring-boot-emo
 * @description: TODO
 * @date 2019/12/27 16:18
 */

import com.zhl.springboot.sharding.entity.Book;
import com.zhl.springboot.sharding.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * @author Macky
 * @Title class BookController
 * @Description: TODO
 * @date 2019/7/12 20:53
 */
@RestController
public class BookController {

    @Autowired
    BookServiceImpl bookService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Book> getItems(){
        return bookService.getBookList();
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Boolean saveItem(){
        Book book = new Book();
        book.setName("红楼梦"+ Math.random());
        book.setCount(new Random().nextInt());
        return bookService.save(book);
    }
}
