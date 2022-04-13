package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class ControllerDemo {
    @GetMapping("getString")
    public String getString(){
        return "系咪甘就得啦？";
    }
}
