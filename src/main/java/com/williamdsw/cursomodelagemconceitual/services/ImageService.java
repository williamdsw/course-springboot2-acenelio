package com.williamdsw.cursomodelagemconceitual.services;

import com.williamdsw.cursomodelagemconceitual.services.exceptions.FileException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author William
 */

@Service
public class ImageService
{
    // ------------------------------------------------------------------------------------//
    // FUNCOES AUXILIARES
    
    public BufferedImage getJPGImageFromFile (MultipartFile multipartFile)
    {
        // Verifica extensao
        String extension = FilenameUtils.getExtension (multipartFile.getOriginalFilename ());
        if (!"png".equals (extension) && !"jpg".equals (extension))
        {
            throw new FileException ("Somente imagens PNG e JPG são permitidas");
        }
        
        try
        {
            BufferedImage image =ImageIO.read (multipartFile.getInputStream ());
            if ("png".equals (extension))
            {
                image = convertPNGtoJPG (image);
            }
            
            return image;
        }
        catch (IOException e)
        {
            throw new FileException ("Erro ao ler arquivo");
        }
    }

    // Cria novo JPG apartir de um PNG
    private BufferedImage convertPNGtoJPG (BufferedImage png)
    {
        BufferedImage jpg = new BufferedImage (png.getWidth (), png.getHeight (), BufferedImage.TYPE_INT_RGB);
        jpg.createGraphics ().drawImage (png, 0, 0, Color.WHITE, null);
        return jpg;
    }
    
    // Retorna stream de uma imagem
    public InputStream getInputStream (BufferedImage image, String extension)
    {
        try
        {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream ();
            ImageIO.write (image, extension, outputStream);
            return new ByteArrayInputStream (outputStream.toByteArray ());
        }
        catch (IOException e)
        {
            throw new FileException ("Erro ao ler arquivo");
        }
    }
    
    public BufferedImage cropSquare (BufferedImage image)
    {
        int minSize = (image.getHeight () <= image.getWidth ()) ? image.getHeight () : image.getWidth ();
        int x = (image.getWidth () / 2) - (minSize / 2);
        int y = (image.getHeight () / 2) - (minSize / 2);
        return Scalr.crop (image, x, y, minSize, minSize);
    }
    
    public BufferedImage resize (BufferedImage image, int size)
    {
        return Scalr.resize (image, Scalr.Method.ULTRA_QUALITY, size);
    }
}