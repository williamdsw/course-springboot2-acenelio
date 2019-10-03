package com.williamdsw.cursomodelagemconceitual.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.williamdsw.cursomodelagemconceitual.domain.enums.EstadoPagamento;

// 1) @Inheritance = Indica heranca
// 2) @JsonTypeInfo = Define que a classe tera um campo adicional chamado "@type"
@Entity
@Inheritance (strategy = InheritanceType.JOINED)
@JsonTypeInfo (use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class Pagamento implements Serializable
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private Integer estadoPagamento;

    // @OneToOne = Indica relacionamento de 'um para um'
    // @MapsId = Indica que o id sera o mesmo do pedido
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId
    private Pedido pedido;

    // ------------------------------------------------------------------------------------//
    // CONSTRUTORES
    
    public Pagamento ()
    {
    }

    public Pagamento (Integer id, EstadoPagamento estadoPagamento, Pedido pedido)
    {
        super ();
        this.id = id;
        this.estadoPagamento = (estadoPagamento == null ? null : estadoPagamento.getCodigo ());
        this.pedido = pedido;
    }

    // ------------------------------------------------------------------------------------//
    // GETTERS / SETTERS
    
    public Integer getId ()
    {
        return id;
    }

    public void setId (Integer id)
    {
        this.id = id;
    }

    public EstadoPagamento getEstadoPagamento ()
    {
        return EstadoPagamento.toEnum (estadoPagamento);
    }

    public void setEstadoPagamento (EstadoPagamento estadoPagamento)
    {
        this.estadoPagamento = estadoPagamento.getCodigo ();
    }

    public Pedido getPedido ()
    {
        return pedido;
    }

    public void setPedido (Pedido pedido)
    {
        this.pedido = pedido;
    }

    // ------------------------------------------------------------------------------------//
    // FUNCOES IMPLEMENTADAS
    
    @Override
    public int hashCode ()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode ());
        return result;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass () != obj.getClass ())
        {
            return false;
        }
        Pagamento other = (Pagamento) obj;
        if (id == null)
        {
            if (other.id != null)
            {
                return false;
            }
        }
        else if (!id.equals (other.id))
        {
            return false;
        }
        return true;
    }
}