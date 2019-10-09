package com.williamdsw.cursomodelagemconceitual.security;

import com.williamdsw.cursomodelagemconceitual.domain.enums.Perfil;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author William
 */
public class UserSS implements UserDetails
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String email;
    private String senha;
    private Collection<? extends GrantedAuthority> authorities;
    
    // ------------------------------------------------------------------------------------//
    // CONSTRUTORES
    
    public UserSS () {}
    public UserSS (Integer id, String email, String senha, Set<Perfil> perfis)
    {
        super ();
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.authorities = perfis.stream ().map (perfil -> new SimpleGrantedAuthority (perfil.getDescricao ())).collect (Collectors.toList ());
    }
    
    // ------------------------------------------------------------------------------------//
    // GETTERS / SETTERS
    
    public Integer getId ()
    {
        return id;
    }
    
    // ------------------------------------------------------------------------------------//
    // IMPLEMENTADOS
    
    // Retorna os perfis do usuario
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities ()
    {
        return authorities;
    }

    @Override
    public String getPassword ()
    {
        return senha;
    }

    @Override
    public String getUsername ()
    {
        return email;
    }

    // Indica se a conta nao esta expirada
    @Override
    public boolean isAccountNonExpired ()
    {
        return true;
    }

    // Indica se a conta nao esta bloqueada
    @Override
    public boolean isAccountNonLocked ()
    {
        return true;
    }

    // Indica se as credenciais nao estao expiradas
    @Override
    public boolean isCredentialsNonExpired ()
    {
        return true;
    }

    // Indica se o usuario esta ativo
    @Override
    public boolean isEnabled ()
    {
        return true;
    }
}