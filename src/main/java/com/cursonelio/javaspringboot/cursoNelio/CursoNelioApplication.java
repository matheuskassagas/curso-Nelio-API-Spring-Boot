package com.cursonelio.javaspringboot.cursoNelio;

import com.cursonelio.javaspringboot.cursoNelio.repository.entity.*;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.enuns.EstadoPagamento;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.enuns.TipoCliente;
import com.cursonelio.javaspringboot.cursoNelio.repository.*;
import com.cursonelio.javaspringboot.cursoNelio.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class CursoNelioApplication implements CommandLineRunner { //permiti implementar um metodo auxiliar

	@Autowired
	private S3Service service;

	public static void main(String[] args) {
		SpringApplication.run(CursoNelioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		service.uploadFile("C:\\temp\\Capturar.PNG");

	}
}
