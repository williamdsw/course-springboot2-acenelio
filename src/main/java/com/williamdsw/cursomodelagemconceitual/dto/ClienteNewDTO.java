package com.williamdsw.cursomodelagemconceitual.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import com.williamdsw.cursomodelagemconceitual.services.validation.ClienteInsert;

@ClienteInsert
public class ClienteNewDTO implements Serializable
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS

    private static final long serialVersionUID = 1L;

    // CLIENTE
    @NotEmpty(message = "Preenchimento obrigatório!")
    @Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Email(message = "Email inválido")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private String cpfOuCnpj;

    private Integer tipoCliente;

    // ENDERECO
    @NotEmpty(message = "Preenchimento obrigatório!")
    private String logradouro;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private String numero;

    private String complemento;
    private String bairro;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private String cep;

    // TELEFONE
    private List<String> telefones = new ArrayList<> ();

    // CIDADE
    private Integer cidadeID;

    // ------------------------------------------------------------------------------------//
    // CONSTRUTORES
    
    public ClienteNewDTO ()
    {
    }

    // ------------------------------------------------------------------------------------//
    // GETTERS / SETTERS
    
    public String getNome ()
    {
        return nome;
    }

    public void setNome (String nome)
    {
        this.nome = nome;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getCpfOuCnpj ()
    {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj (String cpfOuCnpj)
    {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public Integer getTipoCliente ()
    {
        return tipoCliente;
    }

    public void setTipoCliente (Integer tipoCliente)
    {
        this.tipoCliente = tipoCliente;
    }

    public String getLogradouro ()
    {
        return logradouro;
    }

    public void setLogradouro (String logradouro)
    {
        this.logradouro = logradouro;
    }

    public String getNumero ()
    {
        return numero;
    }

    public void setNumero (String numero)
    {
        this.numero = numero;
    }

    public String getComplemento ()
    {
        return complemento;
    }

    public void setComplemento (String complemento)
    {
        this.complemento = complemento;
    }

    public String getBairro ()
    {
        return bairro;
    }

    public void setBairro (String bairro)
    {
        this.bairro = bairro;
    }

    public String getCep ()
    {
        return cep;
    }

    public void setCep (String cep)
    {
        this.cep = cep;
    }

    public List<String> getTelefones ()
    {
        return telefones;
    }

    public void setTelefones (List<String> telefones)
    {
        this.telefones = telefones;
    }

    public Integer getCidadeID ()
    {
        return cidadeID;
    }

    public void setCidadeID (Integer cidadeID)
    {
        this.cidadeID = cidadeID;
    }
}