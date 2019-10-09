package com.williamdsw.cursomodelagemconceitual.resources;

import com.williamdsw.cursomodelagemconceitual.security.JWTUtil;
import com.williamdsw.cursomodelagemconceitual.security.UserSS;
import com.williamdsw.cursomodelagemconceitual.services.UserService;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author William
 */

@RestController
@RequestMapping (value = "/auth")
public class AuthResource
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    @Autowired
    private JWTUtil jwtUtil;
    
    // ------------------------------------------------------------------------------------//
    // FUNCOES AUXILIARES
    
    @RequestMapping (value = "/refresh_token", method = RequestMethod.POST)
    public ResponseEntity<Void> refreshToken (HttpServletResponse response)
    {
        UserSS user = UserService.authenticated ();
        String token = jwtUtil.generateToken (user.getUsername ());
        response.addHeader ("Authorization", "Bearer ".concat (token));
        return ResponseEntity.noContent ().build ();
    }
}