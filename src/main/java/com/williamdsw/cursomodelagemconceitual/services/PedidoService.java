package com.williamdsw.cursomodelagemconceitual.services;

import com.williamdsw.cursomodelagemconceitual.domain.Cliente;
import com.williamdsw.cursomodelagemconceitual.domain.ItemPedido;
import com.williamdsw.cursomodelagemconceitual.domain.PagamentoComBoleto;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.williamdsw.cursomodelagemconceitual.domain.Pedido;
import com.williamdsw.cursomodelagemconceitual.domain.Produto;
import com.williamdsw.cursomodelagemconceitual.domain.enums.EstadoPagamento;
import com.williamdsw.cursomodelagemconceitual.repositories.ItemPedidoRepository;
import com.williamdsw.cursomodelagemconceitual.repositories.PagamentoRepository;
import com.williamdsw.cursomodelagemconceitual.repositories.PedidoRepository;
import com.williamdsw.cursomodelagemconceitual.security.UserSS;
import com.williamdsw.cursomodelagemconceitual.services.exceptions.AuthorizationException;
import com.williamdsw.cursomodelagemconceitual.services.exceptions.ObjectNotFoundException;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS

    @Autowired
    private PedidoRepository repository;
    
    @Autowired
    private BoletoService boletoService;
    
    @Autowired 
    private PagamentoRepository pagamentoRepository;
    
    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private EmailService emailService;

    // ------------------------------------------------------------------------------------//
    // FUNCOES AUXILIARES
    
    public Pedido findByID (Integer id)
    {
        Optional<Pedido> pedido = repository.findById (id);
        return pedido.orElseThrow (() -> new ObjectNotFoundException (" Objeto não encontrado! " + " Id: " + id + " Tipo: " + Pedido.class.getName ()));
    }
    
    @Transactional
    public Pedido insert (Pedido pedido)
    {
        // Dados e salvamento do Pedido e Pagamento
        pedido.setId (null);
        pedido.setInstante (new Date ());
        
        // Busca cliente
        Cliente cliente = clienteService.findByID (pedido.getCliente ().getId ());
        pedido.setCliente (cliente);
        
        pedido.getPagamento ().setEstadoPagamento (EstadoPagamento.PENDENTE);
        pedido.getPagamento ().setPedido (pedido);
        
        if (pedido.getPagamento () instanceof PagamentoComBoleto)
        {
            PagamentoComBoleto boleto = (PagamentoComBoleto) pedido.getPagamento ();
            boletoService.preencherPagamentoComBoleto (boleto, pedido.getInstante ());
        }
        
        pedido = repository.save (pedido);
        pagamentoRepository.save (pedido.getPagamento ());
        
        // Dados e salvamento do Item Pedido
        for (ItemPedido itemPedido : pedido.getItens ())
        {
            itemPedido.setDesconto (0.00);
            Produto produto = produtoService.findByID (itemPedido.getProduto ().getId ());
            itemPedido.setProduto (produto);
            itemPedido.setPreco (produto.getPreco ());
            itemPedido.setPedido (pedido);
        }
        
        itemPedidoRepository.saveAll (pedido.getItens ());
        System.out.println (pedido);
        emailService.sendOrderConfirmationEmail (pedido);
        //emailService.sendOrderConfirmationHtmlEmail (pedido);
        
        return pedido;
    }
    
    public Page<Pedido> findPage (Integer pageNumber, Integer linesPerPage, String orderBy, String direction)
    {
        UserSS user = UserService.authenticated ();
        if (user == null)
        {
            throw new AuthorizationException ("Acesso negado");
        }
        
        PageRequest pageRequest = PageRequest.of (pageNumber, linesPerPage, Direction.valueOf (direction), orderBy);
        Cliente cliente = clienteService.findByID (user.getId ());
        return repository.findByCliente (cliente, pageRequest);
    }
}