package com.williamdsw.cursomodelagemconceitual.domain.enums;

public enum TipoCliente
{
    // ------------------------------------------------------------------------------------//
    // VALORES

    PESSOA_FISICA (1, "Pessoa Física"),
    PESSOA_JURIDICA (2, "Pessoa Jurídica");

    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    private int codigo;
    private String descricao;

    // ------------------------------------------------------------------------------------//
    // CONSTRUTOR
    
    private TipoCliente (int codigo, String descricao)
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
    
    public static TipoCliente toEnum (Integer codigo)
    {
        if (codigo == null)
        {
            return null;
        }

        for (TipoCliente tipo : TipoCliente.values ())
        {
            if (codigo.equals (tipo.getCodigo ()))
            {
                return tipo;
            }
        }

        throw new IllegalArgumentException ("Id inválido : " + codigo);
    }
}