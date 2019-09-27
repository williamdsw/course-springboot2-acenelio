package com.williamdsw.cursomodelagemconceitual;

import java.text.SimpleDateFormat;
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
import com.williamdsw.cursomodelagemconceitual.domain.ItemPedido;
import com.williamdsw.cursomodelagemconceitual.domain.Pagamento;
import com.williamdsw.cursomodelagemconceitual.domain.PagamentoComBoleto;
import com.williamdsw.cursomodelagemconceitual.domain.PagamentoComCartao;
import com.williamdsw.cursomodelagemconceitual.domain.Pedido;
import com.williamdsw.cursomodelagemconceitual.domain.Produto;
import com.williamdsw.cursomodelagemconceitual.domain.enums.EstadoPagamento;
import com.williamdsw.cursomodelagemconceitual.domain.enums.TipoCliente;
import com.williamdsw.cursomodelagemconceitual.repositories.CategoriaRepository;
import com.williamdsw.cursomodelagemconceitual.repositories.CidadeRepository;
import com.williamdsw.cursomodelagemconceitual.repositories.ClienteRepository;
import com.williamdsw.cursomodelagemconceitual.repositories.EnderecoRepository;
import com.williamdsw.cursomodelagemconceitual.repositories.EstadoRepository;
import com.williamdsw.cursomodelagemconceitual.repositories.ItemPedidoRepository;
import com.williamdsw.cursomodelagemconceitual.repositories.PagamentoRepository;
import com.williamdsw.cursomodelagemconceitual.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
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
		
		// ---------- PEDIDO -- PAGAMENTO ---------- //
		// Instancia com dados
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("dd/MM/yyyy HH:mm");
		Pedido pedido1 = new Pedido (null, simpleDateFormat.parse ("27/09/2019 13:32"), mariaSilva, ruaFlores);
		Pedido pedido2 = new Pedido (null, simpleDateFormat.parse ("27/10/2019 13:00"), mariaSilva, avenidaMatos);
		
		// Passando referencias
		Pagamento pagamento1 = new PagamentoComCartao (null, EstadoPagamento.QUITADO, pedido1, 6);
		pedido1.setPagamento (pagamento1);
		
		Pagamento pagamento2 = new PagamentoComBoleto (null, EstadoPagamento.PENDENTE, pedido2, simpleDateFormat.parse ("20/10/2019 00:00"), null);
		pedido2.setPagamento (pagamento2);
		
		mariaSilva.getPedidos ().addAll (Arrays.asList (pedido1, pedido2));
		
		// Salvando
		pedidoRepository.saveAll (Arrays.asList (pedido1, pedido2));
		pagamentoRepository.saveAll (Arrays.asList (pagamento1, pagamento2));
		
		// ---------- ITEM PEDIDO ---------- //
		// Instancia com dados
		ItemPedido itemPedido1 = new ItemPedido (pedido1, computador, 0.00, 1, 2000.00);
		ItemPedido itemPedido2 = new ItemPedido (pedido1, mouse, 0.00, 2, 80.00);
		ItemPedido itemPedido3 = new ItemPedido (pedido2, impressora, 100.00, 1, 800.00);
		
		// Passando referencias
		pedido1.getItens ().addAll (Arrays.asList (itemPedido1, itemPedido2));
		pedido2.getItens ().addAll (Arrays.asList (itemPedido3));
		computador.getItens ().addAll (Arrays.asList (itemPedido1));
		impressora.getItens ().addAll (Arrays.asList (itemPedido3));
		mouse.getItens ().addAll (Arrays.asList (itemPedido2));
		
		// Salvando
		itemPedidoRepository.saveAll (Arrays.asList (itemPedido1, itemPedido2, itemPedido3));
		
		
	}
}