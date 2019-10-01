package com.williamdsw.cursomodelagemconceitual.dto;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import com.williamdsw.cursomodelagemconceitual.domain.Cliente;

public class ClienteDTO implements Serializable
{
	// ------------------------------------------------------------------------------------//
	// CAMPOS
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	
	@NotEmpty (message = "Preenchiemento Obrigatório")
	@Length (min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty (message = "Preenchiemento Obrigatório")
	@Email (message = "Email inválido")
	private String email;
	
	// ------------------------------------------------------------------------------------//
	// CONSTRUTORES
	
	public ClienteDTO () {}
	public ClienteDTO (Cliente cliente)
	{
		this.id = cliente.getId ();
		this.nome = cliente.getNome ();
		this.email = cliente.getEmail ();
	}
	
	// ------------------------------------------------------------------------------------//
	// GETTERS / SETTERS
	
	public Integer getId ()
	{ return id; }
	
	public void setId (Integer id)
	{ this.id = id; }
	
	public String getNome ()
	{ return nome; }
	
	public void setNome (String nome)
	{ this.nome = nome; }
	
	public String getEmail ()
	{ return email; }
	
	public void setEmail (String email)
	{ this.email = email; }
}