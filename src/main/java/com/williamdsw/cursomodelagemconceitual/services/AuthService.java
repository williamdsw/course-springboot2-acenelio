package com.williamdsw.cursomodelagemconceitual.services;

import com.williamdsw.cursomodelagemconceitual.domain.Cliente;
import com.williamdsw.cursomodelagemconceitual.repositories.ClienteRepository;
import com.williamdsw.cursomodelagemconceitual.services.exceptions.ObjectNotFoundException;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author William
 */
@Service
public class AuthService
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private EmailService emailService;
    
    private final Random random = new Random ();
    private final int PASSWORD_LENGTH = 10;
    
    // ------------------------------------------------------------------------------------//
    // FUNCOES AUXILIARES
    
    public void sendNewPassword (String email)
    {
        // Verifica cliente
        Cliente cliente = clienteRepository.findByEmail (email);
        if (cliente == null)
        {
            throw new ObjectNotFoundException ("Email não encontrado!");
        }
        
        String newPassword = newPassword ();
        cliente.setSenha (passwordEncoder.encode (newPassword));
        clienteRepository.save (cliente);
        emailService.sendNewPasswordEmail (cliente, newPassword);
    }

    private String newPassword ()
    {
        char[] password = new char[PASSWORD_LENGTH];
        for (int index = 0; index < PASSWORD_LENGTH; index++)
        {
            password[index] = randomChar ();
        }
        
        return new String (password);
    }
    
    private char randomChar ()
    {
        int option = random.nextInt (3);
        if (option == 0)
        {
            // Digito
            return (char) (random.nextInt (10) + 48);
        }
        else if (option == 1)
        {
            // Letra maiuscula
            return (char) (random.nextInt (26) + 65);
        }
        else
        {
            // Letra minuscula
            return (char) (random.nextInt (26) + 97);
        }
    }
}