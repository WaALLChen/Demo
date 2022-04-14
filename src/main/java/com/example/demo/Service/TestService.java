package com.example.demo.Service;

import com.example.demo.Dao.TestDaoImpl;
import com.example.demo.Entity.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    private TestDaoImpl testDaoImpl;

    public List<TestEntity> getTestEntityList(){
        return testDaoImpl.query("select * from test",null);
    }
}
