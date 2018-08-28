package com.pjq.common.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.pjq.common.datasource.DatabaseType;
import com.pjq.common.datasource.DynamicDataSource;

@Configuration
@MapperScan(basePackages = "com.pjq.dao")
@PropertySource("dataSource_dev.properties")
@Profile("dev")
public class DataSourceConfigDev {

	@Autowired
	private Environment env;

	/**
	 * 创建数据源(数据源的名称：方法名可以取为XXXDataSource(),XXX为数据库名称,该名称也就是数据源的名称)
	 */
	@Bean
	public DataSource devDataSource() throws Exception {
		Properties props = new Properties();
		props.put("driverClassName", env.getProperty("jdbc.driverClassName"));
		props.put("url", env.getProperty("jdbc.url"));
		props.put("username", env.getProperty("jdbc.username"));
		props.put("password",ConfigTools.decrypt(env.getProperty("jdbc.publicKey"), env.getProperty("jdbc.password")) );
		this.setDataSourceProperties(props);
		return DruidDataSourceFactory.createDataSource(props);
	}

	public void setDataSourceProperties(Properties props) {
		props.put("initialSize", env.getProperty("druid.initialSize"));
		props.put("minIdle", env.getProperty("druid.minIdle"));
		props.put("maxActive", env.getProperty("druid.maxActive"));
		props.put("maxWait", env.getProperty("druid.maxWait"));
		props.put("timeBetweenEvictionRunsMillis", env.getProperty("druid.timeBetweenEvictionRunsMillis"));
		props.put("minEvictableIdleTimeMillis", env.getProperty("druid.minEvictableIdleTimeMillis"));
		props.put("validationQuery", env.getProperty("druid.validationQuery"));
		props.put("testWhileIdle", env.getProperty("druid.testWhileIdle"));
		props.put("testOnBorrow", env.getProperty("druid.testOnBorrow"));
		props.put("testOnReturn", env.getProperty("druid.testOnReturn"));
		props.put("poolPreparedStatements", env.getProperty("druid.poolPreparedStatements"));
		props.put("maxPoolPreparedStatementPerConnectionSize", env.getProperty("druid.maxPoolPreparedStatementPerConnectionSize"));
		props.put("filters", env.getProperty("druid.filters"));

	}

	/**
	 * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
	 * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
	 */
	@Bean
	@Primary
	public DynamicDataSource dataSource(@Qualifier("devDataSource") DataSource devDataSource) {
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(DatabaseType.dataSource, devDataSource);
		DynamicDataSource dataSource = new DynamicDataSource();
		dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
		dataSource.setDefaultTargetDataSource(devDataSource);// 默认的datasource设置为myTestDbDataSource
		return dataSource;
	}

	/**
	 * 根据数据源创建SqlSessionFactory
	 */
	@Bean("sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean(DynamicDataSource ds) throws Exception {
		SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
		fb.setDataSource(ds);
		try {
			ResourcePatternResolver rp = new PathMatchingResourcePatternResolver();
			fb.setMapperLocations(rp.getResources("classpath:com/pjq/dao/*.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fb.getObject();
	}

	/**
	 * 配置事务管理器
	 */
	@Bean
	public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
		return new DataSourceTransactionManager(dataSource);
	}

}
