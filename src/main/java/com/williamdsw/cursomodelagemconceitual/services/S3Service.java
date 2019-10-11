package com.williamdsw.cursomodelagemconceitual.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.williamdsw.cursomodelagemconceitual.services.exceptions.FileException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author William
 */

@Service
public class S3Service
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    @Autowired
    private AmazonS3 s3client;
    
    @Value ("${s3.bucket}")
    private String bucketName;
    
    private final Logger LOG = LoggerFactory.getLogger (S3Service.class);
    
    // ------------------------------------------------------------------------------------//
    // FUNCOES AUXILIARES
    
    public URI uploadFile (MultipartFile multipartFile)
    {
        try
        {
            // Informacoes iniciais do arquivo
            String fileName = multipartFile.getOriginalFilename ();
            InputStream inputStream;
            inputStream = multipartFile.getInputStream ();
            String contentType = multipartFile.getContentType ();
            return uploadFile (inputStream, fileName, contentType);
        }
        catch (IOException ex)
        {
            throw new FileException ("Erro de IO: ".concat (ex.getMessage ()));
        }
    }
    
    public URI uploadFile (InputStream inputStream, String fileName, String contentType)
    {
        try
        {
            ObjectMetadata metadata = new ObjectMetadata ();
            metadata.setContentType (contentType);
            LOG.info ("Iniciando upload...");
            s3client.putObject (bucketName, fileName, inputStream, metadata);
            LOG.info ("Upload finalizado!");
            return s3client.getUrl (bucketName, fileName).toURI ();
        }
        catch (URISyntaxException ex)
        {
            throw new FileException ("Erro ao converter URL para URI");
        }
    }
}