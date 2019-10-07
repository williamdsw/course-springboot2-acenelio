package com.williamdsw.cursomodelagemconceitual.services;

import com.williamdsw.cursomodelagemconceitual.domain.Pedido;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author William
 */
public abstract class AbstractEmailService implements EmailService
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    @Value ("${default.sender}")
    private String sender;
    
    // ------------------------------------------------------------------------------------//
    // IMPLEMENTADOS
    
    @Override
    public void sendOrderConfirmationEmail (Pedido pedido)
    {
        SimpleMailMessage message = prepareSimpleMailMessageFromPedido (pedido);
        sendEmail (message);
    }
    
    protected SimpleMailMessage prepareSimpleMailMessageFromPedido (Pedido pedido)
    {
        SimpleMailMessage message = new SimpleMailMessage ();
        message.setTo (pedido.getCliente ().getEmail ());
        message.setFrom (sender);
        message.setSubject ("Pedido confirmado! Código: " + pedido.getId ());
        message.setSentDate (new Date (System.currentTimeMillis ()));
        message.setText (pedido.toString ());        
        return message;
    }
}