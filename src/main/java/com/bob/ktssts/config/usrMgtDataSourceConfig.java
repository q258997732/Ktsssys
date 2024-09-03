package com.bob.ktssts.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Data
@Configuration
public class usrMgtDataSourceConfig {

	@Value("${spring.datasource.usrmgt.url}")
	private String url;
	@Value("${spring.datasource.usrmgt.username}")
	private String username;
	@Value("${spring.datasource.usrmgt.password}")
	private String password;
	@Value("${spring.datasource.usrmgt.driver-class-name}")
	private String driver;

	@Bean(name="usrDataSource")
	@Primary
	@ConditionalOnProperty(name = "usrmgt.enable", havingValue = "true")
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(url);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);
		hikariConfig.setDriverClassName(driver);
		return new HikariDataSource(hikariConfig);
	}

}
