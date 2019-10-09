package com.williamdsw.cursomodelagemconceitual.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author William
 */

// @Component = Indica que a classe podera ser injetada como componente em outras classes
@Component
public class JWTUtil
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    @Value ("${jwt.secret}")
    private String secret;
    
    @Value ("${jwt.expiration}")
    private Long expiration;
    
    // ------------------------------------------------------------------------------------//
    // FUNCOES AUXILIARES
    
    public String generateToken (String username)
    {
        return Jwts.builder ()
               .setSubject (username)
               .setExpiration (new Date (System.currentTimeMillis () + expiration))
               .signWith (SignatureAlgorithm.HS512, secret.getBytes ())
               .compact ();
    }
}