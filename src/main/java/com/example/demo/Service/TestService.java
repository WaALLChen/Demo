package com.example.demo.Service;

import com.example.demo.Dao.TestDao;
import com.example.demo.Entity.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    private TestDao testDao;

    public List<TestEntity> getTestEntityList(){
        return testDao.query("select * from test",null);
    }
}
