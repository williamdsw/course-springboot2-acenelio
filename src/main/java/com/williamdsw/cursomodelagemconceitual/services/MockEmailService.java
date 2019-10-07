package com.williamdsw.cursomodelagemconceitual.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author William
 */
public class MockEmailService extends AbstractEmailService
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    private static final Logger LOG = LoggerFactory.getLogger (MockEmailService.class);
    
    // ------------------------------------------------------------------------------------//
    // IMPLEMENTADOS
    
    @Override
    public void sendEmail (SimpleMailMessage message)
    {
        LOG.info ("Simulando envio de email...");
        LOG.info (message.toString ());
        LOG.info ("Email enviado!");
    }
}