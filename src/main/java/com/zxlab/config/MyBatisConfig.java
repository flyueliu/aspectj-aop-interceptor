package com.zxlab.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Liu Yuefei
 * @created 2018-09-10 17:53
 */
@Configuration
@MapperScan(basePackages = "com.zxlab.dao", sqlSessionFactoryRef = "sqlSessionFactory")
public class MyBatisConfig {

}
