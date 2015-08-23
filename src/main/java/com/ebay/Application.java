package com.ebay;

import javax.annotation.PreDestroy;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.ebay.Conf.MysqlDataSourceProperties;



@Configuration
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@EnableConfigurationProperties(MysqlDataSourceProperties.class)
@ComponentScan
@MapperScan("com.ebay.Mapper")
public class Application {
	
	@Autowired
	private MysqlDataSourceProperties mysqlDataSourceProperties;
	private DataSource pool;
	
	@Bean(destroyMethod="close")
	public DataSource dataSource(){
		MysqlDataSourceProperties config = mysqlDataSourceProperties;
		
		this.pool = new DataSource();
		this.pool.setDriverClassName(config.getDriverClassName());
		this.pool.setUrl(config.getUrl());
		if (config.getUsername() != null) {
			this.pool.setUsername(config.getUsername());
		}
		if (config.getPassword() != null) {
			this.pool.setPassword(config.getPassword());
		}
		
		return this.pool;
	}
	
	@PreDestroy
	public void close() {
		if (this.pool != null) {
			this.pool.close();
		}
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/Mapper/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(new Object[] { Application.class }, args);

	}

}
