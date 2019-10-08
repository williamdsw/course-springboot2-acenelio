package com.williamdsw.cursomodelagemconceitual.services;

import com.williamdsw.cursomodelagemconceitual.domain.Pedido;
import java.util.Date;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author William
 */
public abstract class AbstractEmailService implements EmailService
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    @Value ("${default.sender}")
    private String sender;
    
    @Autowired
    private TemplateEngine templateEngine;
    
    @Autowired
    private JavaMailSender javaMailSender;
    
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
    
    @Override
    public void sendOrderConfirmationHtmlEmail (Pedido pedido)
    {
        try
        {
            MimeMessage message = prepareMimeMessageFromPedido (pedido);
            sendHtmlEmail (message);
        }
        catch (MessagingException exception)
        {
            sendOrderConfirmationEmail (pedido);
        }
    }
    
    protected MimeMessage prepareMimeMessageFromPedido (Pedido pedido) throws MessagingException
    {
        MimeMessage message = javaMailSender.createMimeMessage ();
        MimeMessageHelper helper = new MimeMessageHelper (message, true);
        helper.setTo (pedido.getCliente ().getEmail ());
        helper.setFrom (sender);
        helper.setSubject ("Pedido confirmado! Código: " + pedido.getId ());
        helper.setSentDate (new Date (System.currentTimeMillis ()));
        helper.setText (htmlFromTemplatePedido (pedido), true);
        return message;
    }
    
    protected String htmlFromTemplatePedido (Pedido pedido)
    {
        Context context = new Context ();
        context.setVariable ("pedido", pedido);
        return templateEngine.process ("email/ConfirmacaoPedido", context);
    }
}