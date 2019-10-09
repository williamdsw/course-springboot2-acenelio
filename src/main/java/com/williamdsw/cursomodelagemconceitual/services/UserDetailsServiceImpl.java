package com.williamdsw.cursomodelagemconceitual.services;

import com.williamdsw.cursomodelagemconceitual.domain.Cliente;
import com.williamdsw.cursomodelagemconceitual.repositories.ClienteRepository;
import com.williamdsw.cursomodelagemconceitual.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author William
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    // ------------------------------------------------------------------------------------//
    // IMPLEMENTADOS
    
    @Override
    public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException
    {
        Cliente cliente = clienteRepository.findByEmail (email);
        
        if (cliente == null)
        {
            throw new UsernameNotFoundException (email);
        }
        
        return new UserSS (cliente.getId (), cliente.getEmail (), cliente.getSenha (), cliente.getPerfis ());
    }
}