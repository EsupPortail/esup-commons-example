package org.esupportail.example.dao.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

@Configuration
@Lazy
public class JndiDataSourceConfig {

	@Value("${jndi.datasource}")
	private String jndiDatasourceName;

	@Bean(name = "JNDIDataSource")
	public DataSource jndiDataSource() {
		JndiDataSourceLookup lookup = new JndiDataSourceLookup();
		return lookup.getDataSource(jndiDatasourceName);
	}
}
