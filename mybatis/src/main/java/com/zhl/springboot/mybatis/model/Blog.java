package com.zhl.springboot.mybatis.model;

import lombok.Data;

/**
 * @author 张雷
 * @date 2019/12/20 15:14
 */
@Data
public class Blog {
    private Long id;
    private String title;
    private String content;
}
