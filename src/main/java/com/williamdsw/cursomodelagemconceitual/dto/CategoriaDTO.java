package com.williamdsw.cursomodelagemconceitual.dto;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import com.williamdsw.cursomodelagemconceitual.domain.Categoria;

public class CategoriaDTO implements Serializable
{
	// ------------------------------------------------------------------------------------//
	// CAMPOS
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	
	// 1) @NotEmpty = Indica que o valor do campo nao pode ser vazio
	// 2) @Length = Indica tamanho minimo e maximo do campo
	@NotEmpty (message = "Preenchimento Obrigatório")
	@Length (min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	// ------------------------------------------------------------------------------------//
	// CONSTRUTORES
	
	public CategoriaDTO () {}
	public CategoriaDTO (Categoria categoria)
	{
		id = categoria.getId ();
		nome = categoria.getNome ();
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
}