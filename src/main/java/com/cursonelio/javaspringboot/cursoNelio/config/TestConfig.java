package com.cursonelio.javaspringboot.cursoNelio.config;

import com.cursonelio.javaspringboot.cursoNelio.service.DBService;
import com.cursonelio.javaspringboot.cursoNelio.service.EmailService.EmailService;
import com.cursonelio.javaspringboot.cursoNelio.service.EmailService.MockEmailService;
import com.cursonelio.javaspringboot.cursoNelio.service.EmailService.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;


@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDataBase() throws ParseException {
        dbService.instantiateTestDataBase();
        return true;
    }

    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }

    @Bean
    public EmailService smtpEmailService(){
        return new SmtpEmailService();
    }
}
