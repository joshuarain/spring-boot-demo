package com.zhl.springboot.activity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Lenovo
 * @title: UserController
 * @projectName spring-bootd-emo
 * @description: TODO
 * @date 2019/12/24 16:16
 */
@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping("personal")
    public String personal(String userId) {
        return "user/personal";
    }

}
