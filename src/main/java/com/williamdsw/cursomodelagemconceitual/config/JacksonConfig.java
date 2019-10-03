package com.williamdsw.cursomodelagemconceitual.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.williamdsw.cursomodelagemconceitual.domain.PagamentoComBoleto;
import com.williamdsw.cursomodelagemconceitual.domain.PagamentoComCartao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author William
 */

// @Configuration = Define uma classe de configuracao
@Configuration
public class JacksonConfig
{
    // https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-of-interfaceclass-without-hinting-the-pare
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder ()
    {
       Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder ()
       {
           @Override
           public void configure (ObjectMapper objectMapper)
           {
               objectMapper.registerSubtypes (PagamentoComBoleto.class);
               objectMapper.registerSubtypes (PagamentoComCartao.class);
               super.configure (objectMapper);
           }
       };
       
       return builder;
    }
}