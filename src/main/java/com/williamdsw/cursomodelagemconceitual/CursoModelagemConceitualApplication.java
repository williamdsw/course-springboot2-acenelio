package com.williamdsw.cursomodelagemconceitual;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.williamdsw.cursomodelagemconceitual.domain.Categoria;
import com.williamdsw.cursomodelagemconceitual.repositories.CategoriaRepository;

@SpringBootApplication
public class CursoModelagemConceitualApplication implements CommandLineRunner
{
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main (String[] args) 
	{
		SpringApplication.run (CursoModelagemConceitualApplication.class, args);
	}

	@Override
	public void run (String... args) throws Exception
	{ 
		Categoria informatica = new Categoria (null, "Informática");
		Categoria escritorio = new Categoria (null, "Escritório");
		
		categoriaRepository.saveAll(Arrays.asList(informatica, escritorio));
	}
}