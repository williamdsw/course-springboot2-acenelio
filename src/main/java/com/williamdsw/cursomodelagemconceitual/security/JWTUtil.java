package com.williamdsw.cursomodelagemconceitual.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
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
    
    // Verifica se o token e valido
    public boolean tokenValido (String token)
    {
        Claims claims = getClaims (token);
        if (claims != null)
        {
            String username = claims.getSubject ();
            Date expirationDate = claims.getExpiration ();
            Date now = new Date (System.currentTimeMillis ());
            
            if (username != null && expirationDate != null && now.before (expirationDate))
            {
                return true;
            }
        }
        
        return false;
    }
    
    // Verifica token e extrai username
    public String getUsername (String token)
    {
        Claims claims = getClaims (token);
        if (claims != null)
        {
            return claims.getSubject ();
        }
        
        return null;
    }
    
    private Claims getClaims (String token)
    {
        try
        {
            return Jwts.parser ().setSigningKey (secret.getBytes ()).parseClaimsJws (token).getBody ();
        }
        catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException e)
        {
            return null;
        }
    }
}