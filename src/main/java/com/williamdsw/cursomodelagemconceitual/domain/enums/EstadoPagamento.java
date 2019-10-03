package com.williamdsw.cursomodelagemconceitual.domain.enums;

public enum EstadoPagamento
{
    // ------------------------------------------------------------------------------------//
    // VALORES

    PENDENTE (1, "Pendente"),
    QUITADO (2, "Quitado"),
    CANCELADO (3, "Cancelado");

    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    private int codigo;
    private String descricao;

    // ------------------------------------------------------------------------------------//
    // CONSTRUTOR
    
    private EstadoPagamento (int codigo, String descricao)
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
    
    public static EstadoPagamento toEnum (Integer codigo)
    {
        if (codigo == null)
        {
            return null;
        }

        for (EstadoPagamento estadoPagamento : EstadoPagamento.values ())
        {
            if (codigo.equals (estadoPagamento.getCodigo ()))
            {
                return estadoPagamento;
            }
        }

        throw new IllegalArgumentException ("Código inválido: " + codigo);
    }
}