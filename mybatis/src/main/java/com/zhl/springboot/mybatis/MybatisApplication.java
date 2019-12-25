package com.zhl.springboot.mybatis;

import com.zhl.springboot.mybatis.mapper.BlogMapper;
import com.zhl.springboot.mybatis.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author 张雷
 * @date 2019/12/20 15:09
 */
@SpringBootApplication
public class MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

}
