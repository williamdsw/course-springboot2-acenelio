package com.williamdsw.cursomodelagemconceitual.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import javax.persistence.Entity;
import com.williamdsw.cursomodelagemconceitual.domain.enums.EstadoPagamento;

@Entity
@JsonTypeName (value = "pagamentoComCartao")
public class PagamentoComCartao extends Pagamento
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS

    private static final long serialVersionUID = 1L;
    private Integer numeroDeParcelas;

    // ------------------------------------------------------------------------------------//
    // CONSTRUTORES
    
    public PagamentoComCartao ()
    {
    }

    public PagamentoComCartao (Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroDeParcelas)
    {
        super (id, estadoPagamento, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    // ------------------------------------------------------------------------------------//
    // GETTERS / SETTERS
    
    public Integer getNumeroDeParcelas ()
    {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas (Integer numeroDeParcelas)
    {
        this.numeroDeParcelas = numeroDeParcelas;
    }
}