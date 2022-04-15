package com.example.demo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.example.demo.Dao")
public class MybatisConfig {
    @Value("${db.datasource.url}")
    private String url;

    @Value("${db.datasource.username}")
    private String username;

    @Value("${db.datasource.password}")
    private String password;

    @Bean(destroyMethod = "close")
    public HikariDataSource hikariDataSource() {
        return this.dataSource(url, "db-testPool");
    }

    @Bean
    public DataSourceTransactionManager transactionManagerVisit( DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    private HikariDataSource dataSource(String url, String poolName) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setUsername(username);
        config.setPassword(password);
        config.setConnectionInitSql("SET names 'utf8mb4'");
        config.setJdbcUrl(url);
        config.setPoolName(poolName);
        return new HikariDataSource(config);
    }
}
