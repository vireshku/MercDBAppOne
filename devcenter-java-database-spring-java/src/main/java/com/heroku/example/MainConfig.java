package com.heroku.example;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@ComponentScan(basePackages = "com.heroku.example")
public class MainConfig {

    @Bean
    public BasicDataSource dataSource() throws URISyntaxException {
    	String dbUrl = System.getenv("JDBC_DATABASE_URL");
    	 String username = System.getenv("JDBC_DATABASE_USERNAME");
    	 String password = System.getenv("JDBC_DATABASE_PASSWORD");

//        String username = dbUri.getUserInfo().split(":")[0];
//        String password = dbUri.getUserInfo().split(":")[1];
//        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }

}