package com.trofino.demo.config;


import com.trofino.demo.domain.User;
import com.trofino.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;

    @Bean
    public void startDB() {
        User u1 = new User(1, "caio", "caios.trofino@gmail.com", "dasdaswqeq");
        User u2 = new User(1, "vic", "vic.trofino@gmail.com", "dada");

        repository.saveAll(List.of(u1, u2));
    }
}
