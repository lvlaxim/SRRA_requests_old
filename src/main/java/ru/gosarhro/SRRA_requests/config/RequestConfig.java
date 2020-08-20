package ru.gosarhro.SRRA_requests.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "requestEntityManagerFactory",
        transactionManagerRef = "requestTransactionManager",
        basePackages = "ru.gosarhro.SRRA_requests.repository.requests"
)
public class RequestConfig {

    @Primary
    @Bean(name = "requestDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource requestDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "requestEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean requestEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("requestDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("ru.gosarhro.SRRA_requests.entity.requests")
                .persistenceUnit("requests")
                .build();
    }

    @Primary
    @Bean(name = "requestTransactionManager")
    public PlatformTransactionManager requestTransactionManager(
            @Qualifier("requestEntityManagerFactory") EntityManagerFactory requestEntityManagerFactory) {
        return new JpaTransactionManager(requestEntityManagerFactory);
    }
}
