package com.williamdsw.cursomodelagemconceitual.dto;

import java.io.Serializable;
import com.williamdsw.cursomodelagemconceitual.domain.Categoria;

public class CategoriaDTO implements Serializable
{
	// ------------------------------------------------------------------------------------//
	// CAMPOS
	
	private static final long serialVersionUID = 1L;
	private Integer id;
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