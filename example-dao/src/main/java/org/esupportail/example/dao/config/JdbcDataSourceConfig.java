package org.esupportail.example.dao.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@Lazy
public class JdbcDataSourceConfig {
		
	@Value("${jdbc.connection.driver_class}")
	private String databaseDriverClass;
	
	@Value("${jdbc.connection.url}")
	private String databaseUrl;
	
	@Value("${jdbc.connection.username}")
	private String databaseUsername;
	
	@Value("${jdbc.connection.password}")
	private String databasePassword;

	@Bean(name="JDBCDataSource", destroyMethod="close")
	public DataSource jdbcDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(databaseDriverClass);
		dataSource.setUrl(databaseUrl);
		dataSource.setUsername(databaseUsername);
		dataSource.setPassword(databasePassword);
		dataSource.setMaxActive(100);
		dataSource.setMaxIdle(30);
		dataSource.setMaxWait(100);
		return dataSource;
	}
}
