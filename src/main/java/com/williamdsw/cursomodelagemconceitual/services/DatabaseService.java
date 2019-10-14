package com.williamdsw.cursomodelagemconceitual.services;

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
import com.williamdsw.cursomodelagemconceitual.domain.enums.Perfil;
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
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author William
 */

@Service
public class DatabaseService
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
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    // ------------------------------------------------------------------------------------//
    // FUNCOES AUXILIARES
    
    public void instantiateTestDatabase () throws Exception
    {
        // ---------- CATEGORIA -- PRODUTO ---------- //
        // Instancias com dados
        Categoria informatica = new Categoria (null, "Informática");
        Categoria escritorio = new Categoria (null, "Escritório");
        Categoria camaMesaBanho = new Categoria (null, "Cama, Mesa e Banho");
        Categoria eletronicos = new Categoria (null, "Eletrônicos");
        Categoria jardinagem = new Categoria (null, "Jardinagem");
        Categoria decoracao = new Categoria (null, "Decoração");
        Categoria perfumaria = new Categoria (null, "Perfumaria");

        Produto computador = new Produto (null, "Computador", 2000.00);
        Produto impressora = new Produto (null, "Impressora", 800.00);
        Produto mouse = new Produto (null, "Mouse", 80.00);
        Produto mesaDeEscritorio = new Produto (null, "Mesa de Escritório", 300.00);
        Produto toalha = new Produto (null, "Toalha", 50.00);
        Produto colcha = new Produto (null, "Colcha", 200.00);
        Produto tvTrueColor = new Produto (null, "TV True Color", 1200.00);
        Produto rocadeira = new Produto (null, "Roçadeira", 800.00);
        Produto abajour = new Produto (null, "Abajour", 100.00);
        Produto pendente = new Produto (null, "Pendente", 180.00);
        Produto shampoo = new Produto (null, "Shampoo", 90.00);
        Produto p12 = new Produto(null, "Produto 12", 10.00);
        Produto p13 = new Produto (null, "Produto 13", 10.00);
        Produto p14 = new Produto (null, "Produto 14", 10.00);
        Produto p15 = new Produto (null, "Produto 15", 10.00);
        Produto p16 = new Produto (null, "Produto 16", 10.00);
        Produto p17 = new Produto (null, "Produto 17", 10.00);
        Produto p18 = new Produto (null, "Produto 18", 10.00);
        Produto p19 = new Produto (null, "Produto 19", 10.00);
        Produto p20 = new Produto (null, "Produto 20", 10.00);
        Produto p21 = new Produto (null, "Produto 21", 10.00);
        Produto p22 = new Produto (null, "Produto 22", 10.00);
        Produto p23 = new Produto (null, "Produto 23", 10.00);
        Produto p24 = new Produto (null, "Produto 24", 10.00);
        Produto p25 = new Produto (null, "Produto 25", 10.00);
        Produto p26 = new Produto (null, "Produto 26", 10.00);
        Produto p27 = new Produto (null, "Produto 27", 10.00);
        Produto p28 = new Produto (null, "Produto 28", 10.00);
        Produto p29 = new Produto (null, "Produto 29", 10.00);
        Produto p30 = new Produto (null, "Produto 30", 10.00);
        Produto p31 = new Produto (null, "Produto 31", 10.00);
        Produto p32 = new Produto (null, "Produto 32", 10.00);
        Produto p33 = new Produto (null, "Produto 33", 10.00);
        Produto p34 = new Produto (null, "Produto 34", 10.00);
        Produto p35 = new Produto (null, "Produto 35", 10.00);
        Produto p36 = new Produto (null, "Produto 36", 10.00);
        Produto p37 = new Produto (null, "Produto 37", 10.00);
        Produto p38 = new Produto (null, "Produto 38", 10.00);
        Produto p39 = new Produto (null, "Produto 39", 10.00);
        Produto p40 = new Produto (null, "Produto 40", 10.00);
        Produto p41 = new Produto (null, "Produto 41", 10.00);
        Produto p42 = new Produto (null, "Produto 42", 10.00);
        Produto p43 = new Produto (null, "Produto 43", 10.00);
        Produto p44 = new Produto (null, "Produto 44", 10.00);
        Produto p45 = new Produto (null, "Produto 45", 10.00);
        Produto p46 = new Produto (null, "Produto 46", 10.00);
        Produto p47 = new Produto (null, "Produto 47", 10.00);
        Produto p48 = new Produto (null, "Produto 48", 10.00);
        Produto p49 = new Produto (null, "Produto 49", 10.00);
        Produto p50 = new Produto (null, "Produto 50", 10.00);

        // Passando referencias
        informatica.getProdutos ().addAll (Arrays.asList (computador, impressora, mouse));
        informatica.getProdutos ().addAll (Arrays.asList (p12, p13, p14, p15, p16, p17, p18, p19, p20,
                p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
                p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

        escritorio.getProdutos ().addAll (Arrays.asList (impressora, mesaDeEscritorio));
        camaMesaBanho.getProdutos ().addAll (Arrays.asList (toalha, colcha));
        eletronicos.getProdutos ().addAll (Arrays.asList (computador, impressora, mouse, tvTrueColor));
        jardinagem.getProdutos ().addAll (Arrays.asList (rocadeira));
        decoracao.getProdutos ().addAll (Arrays.asList (abajour, pendente));
        perfumaria.getProdutos ().addAll (Arrays.asList (shampoo));
        
        computador.getCategorias ().addAll (Arrays.asList (informatica, eletronicos));
        impressora.getCategorias ().addAll (Arrays.asList (informatica, escritorio, eletronicos));
        mouse.getCategorias ().addAll (Arrays.asList (informatica, eletronicos));
        mesaDeEscritorio.getCategorias ().addAll (Arrays.asList (escritorio));
        toalha.getCategorias ().addAll (Arrays.asList (camaMesaBanho));
        colcha.getCategorias ().addAll (Arrays.asList (camaMesaBanho));
        tvTrueColor.getCategorias ().addAll (Arrays.asList (eletronicos));
        rocadeira.getCategorias ().addAll (Arrays.asList (jardinagem));
        abajour.getCategorias ().addAll (Arrays.asList (decoracao));
        pendente.getCategorias ().addAll (Arrays.asList (decoracao));
        shampoo.getCategorias ().addAll (Arrays.asList (perfumaria));
        p12.getCategorias ().addAll (Arrays.asList (informatica));
        p13.getCategorias ().addAll (Arrays.asList (informatica));
        p14.getCategorias ().addAll (Arrays.asList (informatica));
        p15.getCategorias ().addAll (Arrays.asList (informatica));
        p16.getCategorias ().addAll (Arrays.asList (informatica));
        p17.getCategorias ().addAll (Arrays.asList (informatica));
        p18.getCategorias ().addAll (Arrays.asList (informatica));
        p19.getCategorias ().addAll (Arrays.asList (informatica));
        p20.getCategorias ().addAll (Arrays.asList (informatica));
        p21.getCategorias ().addAll (Arrays.asList (informatica));
        p22.getCategorias ().addAll (Arrays.asList (informatica));
        p23.getCategorias ().addAll (Arrays.asList (informatica));
        p24.getCategorias ().addAll (Arrays.asList (informatica));
        p25.getCategorias ().addAll (Arrays.asList (informatica));
        p26.getCategorias ().addAll (Arrays.asList (informatica));
        p27.getCategorias ().addAll (Arrays.asList (informatica));
        p28.getCategorias ().addAll (Arrays.asList (informatica));
        p29.getCategorias ().addAll (Arrays.asList (informatica));
        p30.getCategorias ().addAll (Arrays.asList (informatica));
        p31.getCategorias ().addAll (Arrays.asList (informatica));
        p32.getCategorias ().addAll (Arrays.asList (informatica));
        p33.getCategorias ().addAll (Arrays.asList (informatica));
        p34.getCategorias ().addAll (Arrays.asList (informatica));
        p35.getCategorias ().addAll (Arrays.asList (informatica));
        p36.getCategorias ().addAll (Arrays.asList (informatica));
        p37.getCategorias ().addAll (Arrays.asList (informatica));
        p38.getCategorias ().addAll (Arrays.asList (informatica));
        p39.getCategorias ().addAll (Arrays.asList (informatica));
        p40.getCategorias ().addAll (Arrays.asList (informatica));
        p41.getCategorias ().addAll (Arrays.asList (informatica));
        p42.getCategorias ().addAll (Arrays.asList (informatica));
        p43.getCategorias ().addAll (Arrays.asList (informatica));
        p44.getCategorias ().addAll (Arrays.asList (informatica));
        p45.getCategorias ().addAll (Arrays.asList (informatica));
        p46.getCategorias ().addAll (Arrays.asList (informatica));
        p47.getCategorias ().addAll (Arrays.asList (informatica));
        p48.getCategorias ().addAll (Arrays.asList (informatica));
        p49.getCategorias ().addAll (Arrays.asList (informatica));
        p50.getCategorias ().addAll (Arrays.asList (informatica));

        // Salvando
        categoriaRepository.saveAll (Arrays.asList (informatica, escritorio, camaMesaBanho, eletronicos, jardinagem, decoracao, perfumaria));
        produtoRepository.saveAll (Arrays.asList (computador, impressora, mouse, mesaDeEscritorio, toalha, colcha, tvTrueColor, rocadeira, abajour, pendente, shampoo));
        produtoRepository.saveAll (Arrays.asList (p12, p13, p14, p15, p16, p17, p18, p19, p20,
                p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
                p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

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
        Cliente mariaSilva = new Cliente (null, "Maria Silva", "mariasilva@gmail.com", "92176342470", TipoCliente.PESSOA_FISICA, passwordEncoder.encode ("123"));
        Cliente anaCosta = new Cliente (null, "Ana Costa", "anacosta@gmail.com", "59252449663", TipoCliente.PESSOA_FISICA, passwordEncoder.encode ("123"));
        Endereco ruaFlores = new Endereco (null, "Rua Flores", "300", "Apto 303", "Jardim", "123456789", mariaSilva, uberlandia);
        Endereco avenidaMatos = new Endereco (null, "Avenida Matos", "105", "Sala 800", "Centro", "123456788", mariaSilva, saoPaulo);
        Endereco avenidaFloriano = new Endereco (null, "Avenida Floriano", "2106", null, "Centro", "123456789", anaCosta, saoPaulo);

        // Passando referencias
        mariaSilva.getTelefones ().addAll (Arrays.asList ("11111111", "22222222"));
        mariaSilva.getEnderecos ().addAll (Arrays.asList (ruaFlores, avenidaMatos));
        anaCosta.addPerfil (Perfil.ADMIN);
        anaCosta.getTelefones ().addAll (Arrays.asList ("11111111", "22222222"));
        anaCosta.getEnderecos ().addAll (Arrays.asList (avenidaFloriano));

        // Salvando
        clienteRepository.saveAll (Arrays.asList (mariaSilva, anaCosta));
        enderecoRepository.saveAll (Arrays.asList (ruaFlores, avenidaMatos, avenidaFloriano));

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
