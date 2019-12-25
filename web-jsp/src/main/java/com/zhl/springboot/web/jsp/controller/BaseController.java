package com.zhl.springboot.web.jsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 张雷
 * @date 2019/12/20 14:15
 */
@Controller
public class BaseController {

    @GetMapping("/home")
    public String home(){
        return "home";
    }
}
