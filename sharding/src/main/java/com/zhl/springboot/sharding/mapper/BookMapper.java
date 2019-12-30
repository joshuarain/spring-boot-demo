package com.zhl.springboot.sharding.mapper;

/**
 * @author Lenovo
 * @title: BookMapper
 * @projectName spring-boot-emo
 * @description: TODO
 * @date 2019/12/27 16:25
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhl.springboot.sharding.entity.Book;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author Macky
 * @Title class BookMapper
 * @Description: TODO
 * @date 2019/7/12 20:46
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {
}
