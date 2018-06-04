package com.coderme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created By Administrator
 * Date:2018/6/1
 * Time:10:27
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "hello";
    }
}