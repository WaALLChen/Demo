package com.example.demo.Dao;

import com.example.demo.Entity.TestEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TestEntityMapper {
    List<TestEntity> queryTestEntityList();

    TestEntity queryTestEntityById(long id);

    int addTestEntity(TestEntity testEntity);

    int updateTestEntity(TestEntity testEntity);

    int deleteTestEntity(long id);
}
