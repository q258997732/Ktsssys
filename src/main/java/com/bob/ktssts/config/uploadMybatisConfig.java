package com.bob.ktssts.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@ConditionalOnProperty(name = "cz.enable", havingValue = "true")
@MapperScan(basePackages = "com.bob.ktssts.mapper.upload", sqlSessionTemplateRef = "sqlSessionTemplateUpload")
public class uploadMybatisConfig {

	@Bean
	public SqlSessionFactory sqlSessionFactoryUpload(@Qualifier("uploadDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource);
		factory.setMapperLocations( new PathMatchingResourcePatternResolver().getResources("classpath:mapper/upload/*.xml"));
		return factory.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplateUpload(@Qualifier("sqlSessionFactoryUpload") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
