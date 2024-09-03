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
@ConditionalOnProperty(name = "usrmgt.enable", havingValue = "true")
@MapperScan(basePackages = "com.bob.ktssts.mapper.usrmgt", sqlSessionTemplateRef = "sqlSessionTemplateUsrMgt")
public class usrMgtMybatisConfig {

	@Bean
	public SqlSessionFactory sqlSessionFactoryUsrMgt(@Qualifier("usrDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource);
		factory.setMapperLocations( new PathMatchingResourcePatternResolver().getResources("classpath:mapper/usrmgt/*.xml"));
		return factory.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplateUsrMgt(@Qualifier("sqlSessionFactoryUsrMgt") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
