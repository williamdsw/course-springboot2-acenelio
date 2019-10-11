package com.williamdsw.cursomodelagemconceitual.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    
    public void uploadFile (String localFilePath)
    {
        try
        {
            File file = new File (localFilePath);
            LOG.info ("Iniciando Upload...");
            PutObjectRequest objectRequest = new PutObjectRequest (bucketName, "teste.jpg", file);
            s3client.putObject (objectRequest);
            LOG.info ("Upload finalizado");
        }
        catch (AmazonServiceException exception)
        {
            LOG.info ("AmazonServiceException: " + exception.getErrorMessage ());
            LOG.info ("Status Code: " + exception.getErrorCode ());
        }
        catch (AmazonClientException exception)
        {
            LOG.info ("AmazonServiceException: " + exception.getMessage ());
        }
    }
}