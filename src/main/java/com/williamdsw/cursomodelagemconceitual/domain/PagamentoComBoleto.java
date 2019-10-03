package com.williamdsw.cursomodelagemconceitual.domain;

import java.util.Date;
import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.williamdsw.cursomodelagemconceitual.domain.enums.EstadoPagamento;

// 1) @JsonTypeName = Define o nome do tipo no JSON
@Entity
@JsonTypeName (value = "pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS

    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataVencimento;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataPagamento;

    // ------------------------------------------------------------------------------------//
    // CONSTRUTORES
    
    public PagamentoComBoleto ()
    {
    }

    public PagamentoComBoleto (Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Date dataVencimento, Date dataPagamento)
    {
        super (id, estadoPagamento, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }

    // ------------------------------------------------------------------------------------//
    // GETTERS / SETTERS
    
    public Date getDataVencimento ()
    {
        return dataVencimento;
    }

    public void setDataVencimento (Date dataVencimento)
    {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento ()
    {
        return dataPagamento;
    }

    public void setDataPagamento (Date dataPagamento)
    {
        this.dataPagamento = dataPagamento;
    }
}