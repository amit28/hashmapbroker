package com.dbs.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EntityScan("com.dbs.model")
@EnableJpaRepositories(basePackages={"com.dbs.repository"})
@ComponentScan(basePackages={"com.dbs"})
@PropertySource("application.properties")
@SpringBootApplication
public class Application {

    @Bean
    public Cloud cloud() {
        return new CloudFactory().getCloud();
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
