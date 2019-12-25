package com.zhl.springboot.mybatis.mapper;

import com.zhl.springboot.mybatis.model.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author 张雷
 * @date 2019/12/20 15:14
 */
@Mapper
public interface BlogMapper {
    @Select("select * from blog limit 1")
    Blog select();
}
