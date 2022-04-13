package com.example.demo.Dao;

import com.example.demo.Entity.TestEntity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestDao implements TestDaoI{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int update(String sql, Object[] param) {
        return jdbcTemplate.update(sql, param);
    }

    @Override
    public List<TestEntity> query(String sql, Object[] param) {
        RowMapper<TestEntity> rowMapper=new BeanPropertyRowMapper<>(TestEntity.class);
        return jdbcTemplate.query(sql,rowMapper,param);
    }
}
