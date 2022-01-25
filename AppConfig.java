package crudHibernate.mvcHibernate.config;
import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mchange.v2.c3p0.ComboPooledDataSource;
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.spring.project")
@PropertySource({ "classpath:application.properties" })
public class AppConfig implements WebMvcConfigurer {
	@Autowired
	private Environment env;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// define a bean for ViewResolver
//   @Bean
// public ViewResolver internalResourceViewResolver() {
//     InternalResourceViewResolver bean = new InternalResourceViewResolver();
//     bean.setPrefix("/webapp/index/");
//     bean.setSuffix(".jsp");
//     return bean;
// }
	@Bean
	public DataSource myDataSource() {
		
		// create connection pool
		ComboPooledDataSource myDataSource = new ComboPooledDataSource();

		// set the jdbc driver
		try {
			myDataSource.setDriverClass("org.h2.Driver");		
		}
		catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}
	
		
		// set database connection props
		myDataSource.setJdbcUrl(env.getRequiredProperty("jdbc.url"));
		myDataSource.setUser(env.getRequiredProperty("jdbc.user"));
		myDataSource.setPassword(env.getRequiredProperty("jdbc.password"));
		
		// set connection pool props
		myDataSource.setInitialPoolSize(Integer.parseInt(env.getRequiredProperty("connection.pool.initialPoolSize")));
		myDataSource.setMinPoolSize(Integer.parseInt(env.getRequiredProperty("connection.pool.minPoolSize")));
		myDataSource.setMaxPoolSize(Integer.parseInt(env.getRequiredProperty("connection.pool.maxPoolSize")));
		myDataSource.setMaxIdleTime(Integer.parseInt(env.getRequiredProperty("connection.pool.maxIdleTime")));

		return myDataSource;
	}
	
	private Properties getHibernateProperties() {
		// set hibernate properties
		Properties props = new Properties();
		props.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		props.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		props.put("hibernate.packagesToScan",env.getRequiredProperty("hibernate.packagesToScan"));
		props.put("hibernate.hbm2ddl.auto",env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		
		
		return props;				
	}

	
	// need a helper method 
	// read environment property and convert to int
	
	
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		
		// create session factorys
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		//set the properties
	sessionFactory.setDataSource(myDataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.spring.project" });
        sessionFactory.setHibernateProperties(getHibernateProperties());
		
		return sessionFactory;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		
		// setup transaction manager based on session factory
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory().getObject());

		return txManager;
	}	
	
}

