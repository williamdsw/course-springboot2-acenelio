package com.williamdsw.cursomodelagemconceitual.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author William
 */
public class UrlUtils
{
    // ------------------------------------------------------------------------------------//
    // FUNCOES AUXILIARES
    
    // Quebra uma String (exemplo: "1,2,3,4,5") para uma lista de Integers
    public static List<Integer> decodeIntegerList (String content)
    {
        return Arrays.asList (content.split (",")).stream ().map (value -> Integer.parseInt (value)).collect (Collectors.toList ());
    }
    
    // Decodifica uma String com espacos vindo da URL
    public static String decodeParam (String param)
    {
        try
        {
            return URLDecoder.decode (param, "utf-8");
        }
        catch (UnsupportedEncodingException ex)
        {
            return "";
        }
    }
}
