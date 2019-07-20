package com.example.spring.catalog.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class JpaPersistenceConfig {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Autowired
    private Environment environment;

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.example.spring.catalog");
        emf.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        emf.setJpaVendorAdapter(jpaVendorAdapter());
        emf.setJpaProperties(additionalProperties());
        return emf;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.setProperty("hibernate.format_sql", environment.getProperty("spring.jpa.properties.hibernate.format_sql"));
        properties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.setProperty("hibernate.show_sql", environment.getProperty("spring.jpa.show-sql"));
        return properties;
    }
}
