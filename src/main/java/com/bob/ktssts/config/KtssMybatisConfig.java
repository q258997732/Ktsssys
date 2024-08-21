package com.bob.ktssts.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.nio.file.Paths;

@Configuration
@MapperScan(basePackages = "com.bob.ktssts.mapper.ktss", sqlSessionTemplateRef = "sqlSessionTemplateOne")
public class KtssMybatisConfig {

	@Bean
	public SqlSessionFactory sqlSessionFactoryOne(@Qualifier("ktssDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource);
		factory.setMapperLocations( new PathMatchingResourcePatternResolver().getResources("classpath:mapper/ktss/*.xml"));
		return factory.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplateOne(@Qualifier("sqlSessionFactoryOne") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
