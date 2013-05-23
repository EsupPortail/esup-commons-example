package org.esupportail.example.dao.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Lazy
@EnableTransactionManagement
@EnableJpaRepositories("org.esupportail.cookingapp.dao.repositories")
@Import({ JdbcDataSourceConfig.class, JndiDataSourceConfig.class })
public class DaoConfig {

	private final String[] ENTITIES_PACKAGES = new String[] { "org.esupportail.example.domain.beans" };

	@Value("${jpa.database.type}")
	private String databaseType;

	@Value("#{systemProperties[generateDdl]?:false}")
	private boolean generateDdl;

	@Value("${hibernate.show_sql}")
	private boolean showSql;

	@Value("${hibernate.format_sql}")
	private boolean formatSql;

	@Value("${hibernate.use_sql_comments}")
	private boolean useSqlComments;

	@Resource(name = "${datasource.bean}")
	private DataSource dataSource;

	@Bean
	public BeanPostProcessor beanPostProcessor() {
		return new PersistenceAnnotationBeanPostProcessor();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory());
		return transactionManager;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaProperties(jpaProperties());
		factory.setJpaVendorAdapter(vendorAdapter());
		factory.setDataSource(dataSource);
		factory.setPackagesToScan(ENTITIES_PACKAGES);
		factory.afterPropertiesSet();
		return factory.getObject();
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}

	private Properties jpaProperties() {
		Properties props = new Properties();
		props.put("hibernate.cache.provider_class",
				"org.hibernate.cache.NoCacheProvider");
		props.put("hibernate.cache.use_query_cache", false);
		props.put("hibernate.cache.use_second_level_cache", false);
		props.put("hibernate.show_sql", showSql);
		props.put("hibernate.format_sql", formatSql);
		props.put("hibernate.use_sql_comments", useSqlComments);
		return props;
	}

	private JpaVendorAdapter vendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setGenerateDdl(generateDdl);
		adapter.setDatabase(Database.valueOf(databaseType));
		return adapter;
	}
}
