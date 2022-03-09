package com.cursonelio.javaspringboot.cursoNelio.service.EmailService;

import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Pedido;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


public interface EmailService {

    void sendOrderConfimationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);
}
