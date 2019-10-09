package com.williamdsw.cursomodelagemconceitual.services;

import com.williamdsw.cursomodelagemconceitual.domain.Cliente;
import com.williamdsw.cursomodelagemconceitual.domain.Pedido;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author William
 */
public interface EmailService
{
    void sendOrderConfirmationEmail (Pedido pedido);
    void sendEmail (SimpleMailMessage message);
    void sendOrderConfirmationHtmlEmail (Pedido pedido);
    void sendHtmlEmail (MimeMessage message);
    void sendNewPasswordEmail (Cliente cliente, String newPassword);
}