package com.williamdsw.cursomodelagemconceitual.services;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.williamdsw.cursomodelagemconceitual.domain.Cidade;
import com.williamdsw.cursomodelagemconceitual.domain.Cliente;
import com.williamdsw.cursomodelagemconceitual.domain.Endereco;
import com.williamdsw.cursomodelagemconceitual.domain.enums.Perfil;
import com.williamdsw.cursomodelagemconceitual.domain.enums.TipoCliente;
import com.williamdsw.cursomodelagemconceitual.dto.ClienteDTO;
import com.williamdsw.cursomodelagemconceitual.dto.ClienteNewDTO;
import com.williamdsw.cursomodelagemconceitual.repositories.ClienteRepository;
import com.williamdsw.cursomodelagemconceitual.repositories.EnderecoRepository;
import com.williamdsw.cursomodelagemconceitual.security.UserSS;
import com.williamdsw.cursomodelagemconceitual.services.exceptions.AuthorizationException;
import com.williamdsw.cursomodelagemconceitual.services.exceptions.DataIntegrityException;
import com.williamdsw.cursomodelagemconceitual.services.exceptions.ObjectNotFoundException;
import java.awt.image.BufferedImage;
import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ClienteService
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private S3Service s3service;
    
    @Autowired
    private ImageService imageService;
    
    @Value ("${img.prefix.client.profile}")
    private String prefix;
    
    @Value ("${img.profile.size}")
    private Integer imageSize;

    // ------------------------------------------------------------------------------------//
    // FUNCOES AUXILIARES
    
    // Busca todos
    public List<Cliente> findAll ()
    {
        return repository.findAll ();
    }

    // Busca por ID
    public Cliente findByID (Integer id)
    {
        UserSS user = UserService.authenticated ();
        if (user == null || !user.hasRole (Perfil.ADMIN) && !id.equals (user.getId ()))
        {
            throw new AuthorizationException ("Acesso negado");
        }
        
        Optional<Cliente> cliente = repository.findById (id);
        return cliente.orElseThrow (() -> new ObjectNotFoundException (" Objeto não encontrado! " + " Id: " + id + " Tipo: " + Cliente.class.getName ()));
    }
    
    // Busca por email
    public Cliente findByEmail (String email)
    {
        UserSS user = UserService.authenticated ();
        if (user == null || !user.hasRole (Perfil.ADMIN) && !email.equals (user.getUsername ()))
        {
            throw new AuthorizationException ("Acesso negado");
        }
        
        // Busca a e verificacao
        Cliente cliente = repository.findByEmail (email);
        if (cliente == null)
        {
            throw new ObjectNotFoundException (" Objeto não encontrado! Id: " + user.getId () + " Tipo: " + Cliente.class.getName ());
        }
        
        return cliente;
    }

    // Busca com paginacao
    public Page<Cliente> findPage (Integer pageNumber, Integer linesPerPage, String orderBy, String direction)
    {
        PageRequest pageRequest = PageRequest.of (pageNumber, linesPerPage, Direction.valueOf (direction), orderBy);
        return repository.findAll (pageRequest);
    }

    // Insere
    @Transactional
    public Cliente insert (Cliente cliente)
    {
        cliente.setId (null);
        cliente = repository.save (cliente);
        enderecoRepository.saveAll (cliente.getEnderecos ());
        return cliente;
    }

    // Atualiza
    public Cliente update (Cliente cliente)
    {
        Cliente novoCliente = findByID (cliente.getId ());
        updateData (novoCliente, cliente);
        return repository.save (novoCliente);
    }

    // Exclui por ID
    public void deleteByID (Integer id)
    {
        findByID (id);

        try
        {
            repository.deleteById (id);
        }
        catch (DataIntegrityViolationException exception)
        {
            throw new DataIntegrityException ("Não é possível excluir por há pedidos relacionados!");
        }
    }

    // Converte dados
    public Cliente fromDTO (ClienteDTO dto)
    {
        return new Cliente (dto.getId (), dto.getNome (), dto.getEmail (), null, null, null);
    }

    public Cliente fromDTO (ClienteNewDTO dto)
    {
        Cliente cliente = new Cliente (null, dto.getNome (), dto.getEmail (), dto.getCpfOuCnpj (), TipoCliente.toEnum (dto.getTipoCliente ()), passwordEncoder.encode (dto.getSenha ()));
        Cidade cidade = new Cidade (dto.getCidadeID (), null, null);
        Endereco endereco = new Endereco (null, dto.getLogradouro (), dto.getNumero (), dto.getComplemento (), dto.getBairro (), dto.getCep (), cliente, cidade);
        cliente.getEnderecos ().add (endereco);
        cliente.getTelefones ().add (dto.getTelefone1 ());
        
        if (dto.getTelefone2 () != null)
        {
            cliente.getTelefones ().add (dto.getTelefone2 ());
        }
        
        if (dto.getTelefone3 () != null)
        {
            cliente.getTelefones ().add (dto.getTelefone3 ());
        }

        return cliente;
    }

    // Atualiza dados
    private void updateData (Cliente novoCliente, Cliente cliente)
    {
        novoCliente.setNome (cliente.getNome ());
        novoCliente.setEmail (cliente.getEmail ());
    }
    
    // Grava um arquivo e retorna a URI do mesmo
    public URI uploadProfilePicture (MultipartFile multipartFile)
    {
        // Verificando usuario
        UserSS user = UserService.authenticated ();
        if (user == null)
        {
            throw new AuthorizationException ("Acesso negado!");
        }
        
        // Aplica tratamento na imagem e sobe para Amazon
        BufferedImage jpg = imageService.getJPGImageFromFile (multipartFile);
        jpg = imageService.cropSquare (jpg);
        jpg = imageService.resize (jpg, imageSize);
        String fileName = prefix + user.getId () + ".jpg";
        return s3service.uploadFile (imageService.getInputStream (jpg, "jpg"), fileName, "image");
    }
}