package com.zhl.springboot.web.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 张雷
 * @date 2019/12/20 9:17
 */
@Controller
public class BaseController {

    @GetMapping("toLogin")
    public String toLogin(){
        return "login";
    }

    @GetMapping("login")
    public String index(){
        return "index";
    }

    @GetMapping("logout")
    public String logout(){
        return "login";
    }
}
