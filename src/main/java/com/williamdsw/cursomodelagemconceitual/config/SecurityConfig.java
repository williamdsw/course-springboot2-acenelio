package com.williamdsw.cursomodelagemconceitual.config;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author William
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    // URL permitidas
    private static final String[] PUBLIC_MATCHERS = 
    {
        "/h2-console/**"
    };
    
    private static final String[] PUBLIC_MATCHERS_GET = 
    {
        "/produtos/**", "/categorias/**", "/clientes/**"
    };
    
    @Autowired
    private Environment environment;
    
    // ------------------------------------------------------------------------------------//
    // IMPLEMENTADOS

    @Override
    protected void configure (HttpSecurity http) throws Exception
    {
        // Verifica os profiles ativos do projeto
        if (Arrays.asList (environment.getActiveProfiles ()).contains ("test"))
        {
            http.headers ().frameOptions ().disable ();
        }
        
        http.cors ().and ().csrf ().disable ();
        http.authorizeRequests ()
            .antMatchers (HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll ()
            .antMatchers (PUBLIC_MATCHERS).permitAll ()
            .anyRequest ().authenticated ();
        http.sessionManagement ().sessionCreationPolicy (SessionCreationPolicy.STATELESS);
    }
    
    @Bean
    protected CorsConfigurationSource corsConfigurationSource ()
    {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource ();
        source.registerCorsConfiguration ("/**", new CorsConfiguration ().applyPermitDefaultValues ());
        return source;
    }
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder ()
    {
        return new BCryptPasswordEncoder ();
    }
}