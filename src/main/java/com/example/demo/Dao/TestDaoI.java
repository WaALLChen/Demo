package com.example.demo.Dao;

import com.example.demo.Entity.TestEntity;

import java.util.List;

public interface TestDaoI {
    int update(String sql, Object[] param);
    List<TestEntity> query(String sql, Object[] param);
}

