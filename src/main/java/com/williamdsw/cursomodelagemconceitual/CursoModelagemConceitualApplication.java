package com.williamdsw.cursomodelagemconceitual;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.williamdsw.cursomodelagemconceitual.domain.Categoria;
import com.williamdsw.cursomodelagemconceitual.domain.Produto;
import com.williamdsw.cursomodelagemconceitual.repositories.CategoriaRepository;
import com.williamdsw.cursomodelagemconceitual.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoModelagemConceitualApplication implements CommandLineRunner
{
	// ------------------------------------------------------------------------------------//
	// CAMPOS
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	// ------------------------------------------------------------------------------------//
	// MAIN
	
	public static void main (String[] args) 
	{
		SpringApplication.run (CursoModelagemConceitualApplication.class, args);
	}

	@Override
	public void run (String... args) throws Exception
	{
		// Instancias com dados
		Categoria informatica = new Categoria (null, "Informática");
		Categoria escritorio = new Categoria (null, "Escritório");
		Produto computador = new Produto (null, "Computador", 2000.00);
		Produto impressora = new Produto (null, "Impressora", 800.00);
		Produto mouse = new Produto (null, "Mouse", 80.00);
		
		// Passando referencias
		informatica.getProdutos ().addAll (Arrays.asList (computador, impressora, mouse));
		escritorio.getProdutos ().addAll (Arrays.asList (impressora));
		computador.getCategorias ().addAll (Arrays.asList (informatica));
		impressora.getCategorias ().addAll (Arrays.asList (informatica, escritorio));
		mouse.getCategorias().addAll (Arrays.asList (informatica));
		
		// Salvando
		categoriaRepository.saveAll (Arrays.asList(informatica, escritorio));
		produtoRepository.saveAll(Arrays.asList (computador, impressora, mouse));
	}
}