package com.example.demo.Service;

import com.example.demo.Dao.TestEntityMapper;
import com.example.demo.Entity.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestMybatis {
    @Autowired
    private TestEntityMapper mapper;

    public List<TestEntity> getTestEntityList(){
        return mapper.queryTestEntityList();
    }
}
