package com.cursonelio.javaspringboot.cursoNelio;

import com.cursonelio.javaspringboot.cursoNelio.repository.entity.*;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.enuns.EstadoPagamento;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.enuns.TipoCliente;
import com.cursonelio.javaspringboot.cursoNelio.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class CursoNelioApplication implements CommandLineRunner { //permiti implementar um metodo auxiliar

	public static void main(String[] args) {
		SpringApplication.run(CursoNelioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


	}
}
