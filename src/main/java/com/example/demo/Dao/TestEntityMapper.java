package com.example.demo.Dao;

import com.example.demo.Entity.TestEntity;

import java.util.List;

public interface TestEntityMapper {
    List<TestEntity> queryTestEntityList();

    TestEntity queryTestEntityById(long id);

    int addTestEntity(TestEntity testEntity);

    int updateTestEntity(TestEntity testEntity);

    int deleteTestEntity(long id);
}
