package com.williamdsw.cursomodelagemconceitual;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.williamdsw.cursomodelagemconceitual.domain.Categoria;
import com.williamdsw.cursomodelagemconceitual.domain.Cidade;
import com.williamdsw.cursomodelagemconceitual.domain.Cliente;
import com.williamdsw.cursomodelagemconceitual.domain.Endereco;
import com.williamdsw.cursomodelagemconceitual.domain.Estado;
import com.williamdsw.cursomodelagemconceitual.domain.Produto;
import com.williamdsw.cursomodelagemconceitual.domain.enums.TipoCliente;
import com.williamdsw.cursomodelagemconceitual.repositories.CategoriaRepository;
import com.williamdsw.cursomodelagemconceitual.repositories.CidadeRepository;
import com.williamdsw.cursomodelagemconceitual.repositories.ClienteRepository;
import com.williamdsw.cursomodelagemconceitual.repositories.EnderecoRepository;
import com.williamdsw.cursomodelagemconceitual.repositories.EstadoRepository;
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
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	// ------------------------------------------------------------------------------------//
	// MAIN
	
	public static void main (String[] args) 
	{
		SpringApplication.run (CursoModelagemConceitualApplication.class, args);
	}

	@Override
	public void run (String... args) throws Exception
	{
		// ---------- CATEGORIA -- PRODUTO ---------- //
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
		mouse.getCategorias ().addAll (Arrays.asList (informatica));

		// Salvando
		categoriaRepository.saveAll (Arrays.asList (informatica, escritorio));
		produtoRepository.saveAll (Arrays.asList (computador, impressora, mouse));

		// ---------- ESTADO -- CIDADE ---------- //
		// Instancias com dados
		Estado mg = new Estado (null, "Minas Gerais");
		Estado sp = new Estado (null, "São Paulo");
		Cidade uberlandia = new Cidade (null, "Uberlândia", mg);
		Cidade saoPaulo = new Cidade (null, "São Paulo", sp);
		Cidade campinas = new Cidade (null, "Campinas", sp);

		// Passando referencias
		mg.getCidades ().addAll (Arrays.asList (uberlandia));
		sp.getCidades ().addAll (Arrays.asList (saoPaulo, campinas));

		// Salvando
		estadoRepository.saveAll (Arrays.asList (mg, sp));
		cidadeRepository.saveAll (Arrays.asList (uberlandia, saoPaulo, campinas));

		// ---------- CLIENTE -- ENDERECO ---------- //
		// Instancia com dados
		Cliente mariaSilva = new Cliente (null, "Maria Silva", "mariasilva@gmail.com", "11111111111111", TipoCliente.PESSOA_FISICA);
		Endereco ruaFlores = new Endereco (null, "Rua Flores", "300", "Apto 303", "Jardim", "123456789", mariaSilva, uberlandia);
		Endereco avenidaMatos = new Endereco (null, "Avenida Matos", "105", "Sala 800", "Centro", "123456788", mariaSilva, saoPaulo);

		// Passando referencias
		mariaSilva.getTelefones ().addAll (Arrays.asList ("11111111", "22222222"));
		mariaSilva.getEnderecos ().addAll (Arrays.asList (ruaFlores, avenidaMatos));

		// Salvando
		clienteRepository.saveAll (Arrays.asList (mariaSilva));
		enderecoRepository.saveAll (Arrays.asList (ruaFlores, avenidaMatos));
	}
}