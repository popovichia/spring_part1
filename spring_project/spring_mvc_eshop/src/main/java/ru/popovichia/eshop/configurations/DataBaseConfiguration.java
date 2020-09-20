package ru.popovichia.eshop.configurations;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Igor Popovich, email: iapopo17@mts.ru, phone: +7 913 902 36 36,
 * company: mts.ru
 */
@Configuration
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("ru.popovichia.eshop.repositories")
@EnableTransactionManagement
@ComponentScan("ru.popovichia.eshop.services")
public class DataBaseConfiguration {
    
    @Value("${database.driver.class}")
    private String dataBaseDriverClass;
    
    @Value("${database.url}")
    private String dataBaseUrl;

    @Value("${database.username}")
    private String dataBaseUserName;

    @Value("${database.password}")
    private String dataBasePassword;

    private DataSource getDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(dataBaseDriverClass);
        driverManagerDataSource.setUrl(dataBaseUrl);
        driverManagerDataSource.setUsername(dataBaseUserName);
        driverManagerDataSource.setPassword(dataBasePassword);
        return driverManagerDataSource; 
    }
    
    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put("connection.pool_size", 1);
        properties.put("current_session_context_class", "thread");
        properties.put("hibernate.hbm2ddl.auto","update");
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.dialect", "org.hibernate.dialect.SQLiteDialect");
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.jdbc.batch_size", 10);
        return properties;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getLocalContainerEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean
                = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(this.getDataSource());
        localContainerEntityManagerFactoryBean
                .setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        localContainerEntityManagerFactoryBean
                .setPackagesToScan("ru.popovichia.eshop.entities");
        localContainerEntityManagerFactoryBean.setJpaProperties(this.getProperties());
        return localContainerEntityManagerFactoryBean;
    }
        
    @Bean(name = "transactionManager")
    public JpaTransactionManager getJpaTransactionManager(
            EntityManagerFactory entityManagerFactory
    ) {        
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager
                .setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }
  
}
