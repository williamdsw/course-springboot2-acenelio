package com.williamdsw.cursomodelagemconceitual.resources;

import com.williamdsw.cursomodelagemconceitual.dto.EmailDTO;
import com.williamdsw.cursomodelagemconceitual.security.JWTUtil;
import com.williamdsw.cursomodelagemconceitual.security.UserSS;
import com.williamdsw.cursomodelagemconceitual.services.AuthService;
import com.williamdsw.cursomodelagemconceitual.services.UserService;

import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @Autowired
    private AuthService authService;
    
    // ------------------------------------------------------------------------------------//
    // FUNCOES AUXILIARES
    
    // Gerar novo token
    @RequestMapping (value = "/refresh_token", method = RequestMethod.POST)
    @ApiOperation (value = "Gera novo token")
    public ResponseEntity<Void> refreshToken (HttpServletResponse response)
    {
        UserSS user = UserService.authenticated ();
        String token = jwtUtil.generateToken (user.getUsername ());
        response.addHeader ("Authorization", "Bearer ".concat (token));
        response.addHeader ("access-control-expose-headers", "Authorization");
        return ResponseEntity.noContent ().build ();
    }
    
    // Gerar nova senha
    @RequestMapping (value = "/forgot", method = RequestMethod.POST)
    @ApiOperation (value = "Gera nova senha")
    public ResponseEntity<Void> forgot (@Valid @RequestBody EmailDTO dto)
    {
        authService.sendNewPassword (dto.getEmail ());
        return ResponseEntity.noContent ().build ();
    }
}