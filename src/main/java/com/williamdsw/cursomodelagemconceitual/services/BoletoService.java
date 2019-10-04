package com.williamdsw.cursomodelagemconceitual.services;

import com.williamdsw.cursomodelagemconceitual.domain.PagamentoComBoleto;
import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 * @author William
 */
@Service
public class BoletoService
{
    // ------------------------------------------------------------------------------------//
    // FUNCOES AUXILIARES
    
    // 1) Calendar = Classe para manipular datas
    public void preencherPagamentoComBoleto (PagamentoComBoleto boleto, Date instanteDoPedido)
    {
        Calendar calendar = Calendar.getInstance ();
        calendar.setTime (instanteDoPedido);
        calendar.add (Calendar.DAY_OF_MONTH, 7);
        boleto.setDataVencimento (calendar.getTime ());
    }
}