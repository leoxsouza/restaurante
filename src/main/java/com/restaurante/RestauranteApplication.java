package com.restaurante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EntityScan(basePackages = {"com.restaurante.domain"})
@EnableJpaRepositories(basePackages = {"com.restaurante.repository"})
@EnableTransactionManagement
@EnableWebMvc
public class RestauranteApplication {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123"));
        SpringApplication.run(RestauranteApplication.class, args);
    }

}
