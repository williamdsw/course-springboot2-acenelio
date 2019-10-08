package com.williamdsw.cursomodelagemconceitual.domain.enums;

public enum Perfil
{
    // ------------------------------------------------------------------------------------//
    // VALORES

    ADMIN (1, "ROLE_ADMIN"),
    CLIENTE (2, "ROLE_CLIENTE");

    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    private int codigo;
    private String descricao;

    // ------------------------------------------------------------------------------------//
    // CONSTRUTOR
    
    private Perfil (int codigo, String descricao)
    {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    // ------------------------------------------------------------------------------------//
    // GETTERS / SETTERS
    
    public int getCodigo ()
    {
        return this.codigo;
    }

    public String getDescricao ()
    {
        return this.descricao;
    }

    // ------------------------------------------------------------------------------------//
    // FUNCOES AUXILIARES
    
    public static Perfil toEnum (Integer codigo)
    {
        if (codigo == null)
        {
            return null;
        }

        for (Perfil tipo : Perfil.values ())
        {
            if (codigo.equals (tipo.getCodigo ()))
            {
                return tipo;
            }
        }

        throw new IllegalArgumentException ("Id inválido : " + codigo);
    }
}