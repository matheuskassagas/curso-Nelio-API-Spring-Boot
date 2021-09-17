package com.cursonelio.javaspringboot.cursoNelio;

import domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import repository.CategoriaRepository;

import java.util.Arrays;

@SpringBootApplication
public class CursoNelioApplication implements CommandLineRunner { //permiti implementar um metodo auxiliar

	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursoNelioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));//salvando no banco

	}
}
