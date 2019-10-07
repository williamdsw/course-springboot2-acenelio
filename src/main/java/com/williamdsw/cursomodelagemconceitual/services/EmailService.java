package com.williamdsw.cursomodelagemconceitual.services;

import com.williamdsw.cursomodelagemconceitual.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author William
 */
public interface EmailService
{
    void sendOrderConfirmationEmail (Pedido pedido);
    void sendEmail (SimpleMailMessage message);
}