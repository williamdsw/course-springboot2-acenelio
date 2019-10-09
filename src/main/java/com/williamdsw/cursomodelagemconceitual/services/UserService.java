package com.williamdsw.cursomodelagemconceitual.services;

import com.williamdsw.cursomodelagemconceitual.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author William
 */
@Service
public class UserService
{
    public static UserSS authenticated ()
    {
        try
        {
            return (UserSS) SecurityContextHolder.getContext ().getAuthentication ().getPrincipal ();
        }
        catch (Exception e)
        {
            return null;
        }
    }
}