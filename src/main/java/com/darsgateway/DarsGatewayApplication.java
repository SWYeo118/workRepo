package com.darsgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class DarsGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DarsGatewayApplication.class, args);
    }

}
