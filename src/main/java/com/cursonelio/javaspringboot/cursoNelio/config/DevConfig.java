package com.cursonelio.javaspringboot.cursoNelio.config;

import com.cursonelio.javaspringboot.cursoNelio.service.DBService;
import com.cursonelio.javaspringboot.cursoNelio.service.EmailService.EmailService;

import com.cursonelio.javaspringboot.cursoNelio.service.EmailService.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;


@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDataBase() throws ParseException {
    if (!"create".equals(strategy)){
        return false;
    }
        dbService.instantiateTestDataBase();
        return true;
    }

    @Bean
    public EmailService smtpEmailService(){
        return new SmtpEmailService();
    }
}
