package com.apress.prospring5.ch4.sec15.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @RequestMapping("/")
    public String sayHi(){
        return "==========Hello World!";
    }
}
