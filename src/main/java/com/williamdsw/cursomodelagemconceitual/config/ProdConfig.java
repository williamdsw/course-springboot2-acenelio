package com.williamdsw.cursomodelagemconceitual.config;

import com.williamdsw.cursomodelagemconceitual.services.DatabaseService;
import com.williamdsw.cursomodelagemconceitual.services.EmailService;
import com.williamdsw.cursomodelagemconceitual.services.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author William
 */

// application-dev.properties
@Configuration
@Profile ("prod")
public class ProdConfig
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    @Autowired
    private DatabaseService databaseService;
    
    @Value ("${spring.jpa.hibernate.dll-auto}")
    private String strategy;
    
    // ------------------------------------------------------------------------------------//
    // FUNCOES AUXILIARES
    
    @Bean
    public boolean instantiateDatabase () throws Exception
    {
        if (!"create".equals (strategy))
        {
            return false;
        }
        
        databaseService.instantiateTestDatabase ();
        return true;
    }
    
    @Bean
    public EmailService emailService ()
    {
        return new SmtpEmailService ();
    }
}
