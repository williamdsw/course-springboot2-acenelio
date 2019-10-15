package com.williamdsw.cursomodelagemconceitual.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.williamdsw.cursomodelagemconceitual.dto.CredenciaisDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author William
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;

    // ------------------------------------------------------------------------------------//
    // CONSTRUTOR
    
    public JWTAuthenticationFilter (AuthenticationManager authenticationManager, JWTUtil jwtUtil)
    {
        setAuthenticationFailureHandler (new JWTAuthenticationFailureHandler ());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
    
    // ------------------------------------------------------------------------------------//
    // IMPLEMENTADOS
    
    // Verifica credenciais e tenta autenticar
    @Override
    public Authentication attemptAuthentication (HttpServletRequest request, HttpServletResponse response) throws AuthenticationException
    {
        try
        {
            CredenciaisDTO dto = new ObjectMapper ().readValue (request.getInputStream (), CredenciaisDTO.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken (dto.getEmail (), dto.getSenha (), new ArrayList<>());
            Authentication authentication = authenticationManager.authenticate (authToken);
            return authentication;
        }
        catch (IOException exception)
        {
            throw new RuntimeException (exception);
        }
    }

    @Override
    protected void successfulAuthentication (HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException
    {
        String username = ((UserSS) authResult.getPrincipal ()).getUsername ();
        String token = jwtUtil.generateToken (username);
        response.addHeader ("Authorization", "Bearer ".concat (token));
        response.addHeader ("access-control-expose-headers", "Authorization");
    }
    
    private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler
    {

        @Override
        public void onAuthenticationFailure (HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException
        {
            System.out.println ("está no error");
            response.setStatus (401);
            response.setContentType ("application/json");
            response.getWriter ().append (json ());
        }
        
        private String json ()
        {
            long date = new Date ().getTime ();
            StringBuilder builder = new StringBuilder ();
            builder.append ("{\"timestamp\":");
            builder.append (date);
            builder.append (", \"status\": 401, ");
            builder.append ("\"error\": \"Não autorizado\", ");
            builder.append ("\"message\": \"Email ou senha inválidos\", ");
            builder.append ("\"path\": \"/login\"} ");
            return builder.toString ();
        }
    }
}