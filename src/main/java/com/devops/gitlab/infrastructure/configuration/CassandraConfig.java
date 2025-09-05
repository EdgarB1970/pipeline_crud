package com.devops.gitlab.infrastructure.configuration;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;

@Configuration
public class CassandraConfig {

    @Value("${spring.data.cassandra.contact-points}")
    private String contactPoints;

    @Value("${spring.data.cassandra.port}")
    private int port;

    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspaceName;

    @Value("${spring.data.cassandra.username}")
    private String username;

    @Value("${spring.data.cassandra.password}")
    private String password;


    @Bean
    @Primary
    public CqlSession session(){
        CqlSessionFactoryBean sessionFactory = new CqlSessionFactoryBean();
        sessionFactory.setContactPoints(contactPoints);
        sessionFactory.setPort(port);
        sessionFactory.setKeyspaceName(keyspaceName);
        sessionFactory.setLocalDatacenter("datacenter1");

        sessionFactory.setUsername(username);
        sessionFactory.setPassword(password);

        try {
            sessionFactory.afterPropertiesSet();
            return sessionFactory.getObject();
        } catch (Exception e) {
            throw new RuntimeException("Error creando CqlSession: " + e.getMessage(), e);
        }
    }
}
