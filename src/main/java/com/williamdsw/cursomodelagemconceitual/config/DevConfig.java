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

// Arquivo de configuracao para profile "dev" (application-dev.properties)
@Configuration
@Profile ("dev")
public class DevConfig
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    @Autowired
    private DatabaseService databaseService;
    
    // 1) @Value = Permite buscar um valor do arquivo de configuracao
    @Value ("${spring.jpa.hibernate.ddl-auto}")
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