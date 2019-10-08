package com.williamdsw.cursomodelagemconceitual.config;

import com.williamdsw.cursomodelagemconceitual.services.DatabaseService;
import com.williamdsw.cursomodelagemconceitual.services.EmailService;
import com.williamdsw.cursomodelagemconceitual.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author William
 */

// @Profile ("test") = Indica o profile a ser utilizado por essa classe
@Configuration
@Profile ("test")
public class TestConfig
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    @Autowired
    private DatabaseService databaseService;
    
    // ------------------------------------------------------------------------------------//
    // FUNCOES AUXILIARES
    
    @Bean
    public boolean instantiateDatabase () throws Exception
    { 
        databaseService.instantiateTestDatabase ();
        return true;
    }
    
    // @Bean permite disponibilizar um componente para ser usado
    @Bean
    public EmailService emailService ()
    {
        return new MockEmailService ();
    }
}