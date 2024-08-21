package com.bob.ktssts.config;



import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Data
@Configuration
public class KtssDataSourceConfig {

	@Value("${spring.datasource.ktss.url}")
	private String url;
	@Value("${spring.datasource.ktss.username}")
	private String username;
	@Value("${spring.datasource.ktss.password}")
	private String password;
	@Value("${spring.datasource.ktss.driver-class-name}")
	private String driver;

	@Bean(name="ktssDataSource")
	@Primary
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(url);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);
		hikariConfig.setDriverClassName(driver);
		return new HikariDataSource(hikariConfig);
	}

}
