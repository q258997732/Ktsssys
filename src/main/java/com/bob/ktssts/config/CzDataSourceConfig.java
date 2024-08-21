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
public class CzDataSourceConfig {

	@Value("${spring.datasource.cz.url}")
	private String url;
	@Value("${spring.datasource.cz.username}")
	private String username;
	@Value("${spring.datasource.cz.password}")
	private String password;
	@Value("${spring.datasource.cz.driver-class-name}")
	private String driver;

	@Bean(name="czDataSource")
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
