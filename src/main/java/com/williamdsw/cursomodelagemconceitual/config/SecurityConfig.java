package com.williamdsw.cursomodelagemconceitual.config;

import com.williamdsw.cursomodelagemconceitual.security.JWTAuthenticationFilter;
import com.williamdsw.cursomodelagemconceitual.security.JWTAuthorizationFilter;
import com.williamdsw.cursomodelagemconceitual.security.JWTUtil;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author William
 */

// 1) @EnableGlobalMethodSecurity = Habilita configuracao global de seguranca
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity (prePostEnabled = true)
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
        "/produtos/**", "/categorias/**", "/estados/**"
    };
    
    private static final String[] PUBLIC_MATCHERS_POST = 
    {
        "/clientes", "/auth/forgot/**"
    };
    
    @Autowired
    private Environment environment;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private JWTUtil jwtUtil;
    
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
            .antMatchers (HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll ()
            .antMatchers (HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll ()
            .antMatchers (PUBLIC_MATCHERS).permitAll ()
            .anyRequest ().authenticated ();
        
        // Adiciona filtro de autenticacao
        http.addFilter (new JWTAuthenticationFilter (authenticationManager (), jwtUtil));
        
        // Adiciona filtro de autorizacao
        http.addFilter (new JWTAuthorizationFilter (authenticationManager (), jwtUtil, userDetailsService));
        
        http.sessionManagement ().sessionCreationPolicy (SessionCreationPolicy.STATELESS);
    }
    
    @Override
    public void configure (AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService (userDetailsService).passwordEncoder (bCryptPasswordEncoder ());
    }
    
    @Bean
    protected CorsConfigurationSource corsConfigurationSource ()
    {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource ();
        CorsConfiguration configuration = new CorsConfiguration ().applyPermitDefaultValues ();
        configuration.setAllowedMethods (Arrays.asList ("POST", "GET", "PUT", "DELETE", "OPTIONS"));
        source.registerCorsConfiguration ("/**", configuration);
        return source;
    }
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder ()
    {
        return new BCryptPasswordEncoder ();
    }
}