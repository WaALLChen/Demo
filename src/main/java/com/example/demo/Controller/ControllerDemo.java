package com.example.demo.Controller;

import com.example.demo.Entity.TestEntity;
import com.example.demo.Service.TestMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class ControllerDemo {
    @Autowired
    private TestMybatis testMybatis;

    @GetMapping("getDBTestListByMybatis")
    public List<TestEntity> getDBTestListByMybatis(){
        return testMybatis.getTestEntityList();
    }
}
