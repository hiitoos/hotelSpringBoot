package com.hotel.java.configuration;
import com.hotel.java.application.repositories.MasterRepository;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {

    @Bean(name = "entityManagerFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean ();
        sessionFactory.setDataSource (dataSource ());
        sessionFactory.setPackagesToScan ("com.hotel.java.application.domain.entities");
        sessionFactory.setHibernateProperties (hibernateProperties ());

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create ();
        dataSourceBuilder.driverClassName ("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url ("jdbc:mysql://localhost:3306/hotel?serverTimezone=UTC");
        dataSourceBuilder.username ("root");
        dataSourceBuilder.password ("secret1234");  //Esto hay que ponerlo en el properties!!!!!!!!

        return dataSourceBuilder.build ();
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager ();
        transactionManager.setSessionFactory (sessionFactory ().getObject ());
        return transactionManager;
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties ();
        hibernateProperties.setProperty (
                "hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty (
                "hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");

        return hibernateProperties;
    }
}